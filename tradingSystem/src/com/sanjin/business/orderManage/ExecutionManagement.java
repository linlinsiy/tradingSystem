package com.sanjin.business.orderManage;

import java.util.Date;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.protobuf.TextFormat;
import com.sanjin.bean.StockPoolClientProtos.CLIENTMSGTYPE;
import com.sanjin.bean.StockPoolClientProtos.ClientAccount;
import com.sanjin.bean.StockPoolClientProtos.ClientOrder;
import com.sanjin.bean.StockPoolClientProtos.ClientPosition;
import com.sanjin.bean.StockPoolClientProtos.Direction;
import com.sanjin.bean.StockPoolClientProtos.MSGUSETYPE;
import com.sanjin.bean.StockPoolClientProtos.OrderStatus;
import com.sanjin.bean.StockPoolClientProtos.SanjinClientMessage;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerOrder;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerPosition;
import com.sanjin.business.client.communication.GuiSendMessageManager;
import com.sanjin.business.gateway.FixMessageManager;
import com.sanjin.cache.AccountCache;
import com.sanjin.cache.BrokerOrderCache;
import com.sanjin.cache.BrokerPositionCache;
import com.sanjin.cache.ClientOrderCache;
import com.sanjin.cache.ClientPositionCache;

public class ExecutionManagement implements Runnable {
	private static Logger logger = LogManager.getLogger("trading");

	@Override
	public void run() {
		logger.info("Execution Management已经开启");
		while (true) {
			BrokerOrder brokerOrder = FixMessageManager.getInstance().popOrder();
			if (brokerOrder == null || brokerOrder.getSymbol().equals("000000"))
				continue;
			ClientOrder clientOrder = ClientOrderCache.getInstance().getOrder(brokerOrder.getClientOrderId());
			if (clientOrder == null) {
				logger.error("Broker Order找不到原client Order");
				logger.error(TextFormat.shortDebugString(brokerOrder));
				continue;
			}
			Object orderLock = OrderLock.getLock(clientOrder.getClientId());
			synchronized (orderLock) {
				try {
					logger.info("---------------------------------------");
					BrokerOrder preBrokerOrder = BrokerOrderCache.getInstance()
							.getBrokerOrder(brokerOrder.getBrokerOrderId());
					BrokerOrderCache.getInstance().addBrokerOrder(brokerOrder);
					ClientOrder newOrder = updateClientOrder(clientOrder, preBrokerOrder, brokerOrder);
					updateBrokerPosition(preBrokerOrder, brokerOrder);
					logger.info("preClientOrder" + TextFormat.shortDebugString(clientOrder));
					if (null != newOrder) {
						updateClientPosition(newOrder.getClientId(), preBrokerOrder, brokerOrder);
						updateClientAccount(newOrder.getClientId(), preBrokerOrder, brokerOrder);
						logger.info("newClientOrder" + TextFormat.shortDebugString(newOrder));
					}
					logger.info("preBrokerOrder" + TextFormat.shortDebugString(preBrokerOrder));
					logger.info("newBrokerOrder" + TextFormat.shortDebugString(brokerOrder));
				} catch (Throwable e) {
					e.printStackTrace();
					logger.error("client order to broker order error", e);
					logger.error(TextFormat.shortDebugString(clientOrder));
					logger.error(TextFormat.shortDebugString(brokerOrder));
				}
			}
		}
	}

	public ClientOrder updateClientOrder(ClientOrder clientOrder, BrokerOrder preBrokerOrder, BrokerOrder brokerOrder) {
		ClientOrder.Builder clientOrderBuilder = clientOrder.toBuilder();
		boolean changed = false;
		if (clientOrder != null) {
			long tradevol = 0;
			Set<String> brokerOrderIds = BrokerOrderCache.getInstance()
					.getClientBrokerIdSet(clientOrder.getClientOrderId());
			int totalNum = brokerOrderIds.size();
			int cancelledNum = 0;
			int rejectedNum = 0;
			int tradedNum = 0;
			if (brokerOrderIds != null) {
				for (String brokerOrderId : brokerOrderIds) {
					if (brokerOrderId.equals(brokerOrder.getBrokerOrderId()))
						continue;
					OrderStatus status = BrokerOrderCache.getInstance().getBrokerOrder(brokerOrderId).getOrderStatus();
					if (status == OrderStatus.STATUS_ALLTRADED)
						tradedNum++;
					else if (status == OrderStatus.STATUS_CANCELLED)
						cancelledNum++;
					else if (status == OrderStatus.STATUS_REJECTED)
						rejectedNum++;
				}
			}
			switch (brokerOrder.getOrderStatus()) {
			case STATUS_ALLTRADED:
				if (preBrokerOrder != null) {
					if (brokerOrder.getTradedVolume() > preBrokerOrder.getTradedVolume()) {
						tradevol = clientOrder.getTradedVolume() - preBrokerOrder.getTradedVolume()
								+ brokerOrder.getTradedVolume();
						clientOrderBuilder.setTradedVolume(tradevol);
						clientOrderBuilder.setTradedPrice((clientOrder.getTradedVolume() * clientOrder.getTradedPrice()
								- preBrokerOrder.getTradedVolume() * preBrokerOrder.getTradedPrice()
								+ brokerOrder.getTradedVolume() * brokerOrder.getTradedPrice()) / tradevol);
					}
				} else {
					tradevol = clientOrder.getTradedVolume() + brokerOrder.getTradedVolume();
					clientOrderBuilder.setTradedVolume(tradevol);
					clientOrderBuilder.setTradedPrice((clientOrder.getTradedVolume() * clientOrder.getTradedPrice()
							+ brokerOrder.getTradedVolume() * brokerOrder.getTradedPrice()) / tradevol);
				}
				if (tradevol == clientOrder.getTotalVolume())
					clientOrderBuilder.setOrderStatus(OrderStatus.STATUS_ALLTRADED);
				else if (tradevol > 0)
					clientOrderBuilder.setOrderStatus(OrderStatus.STATUS_PARTTRADED);
				if (tradedNum + cancelledNum + rejectedNum + 1 == totalNum) {
					if (cancelledNum > 0) {
						clientOrderBuilder.setOrderStatus(OrderStatus.STATUS_CANCELLED);
						clientOrderBuilder.setCancelTime(Long.parseLong(OrderManagement.df.format(new Date())));
					} else if (clientOrderBuilder.getTradedVolume() > 0 && rejectedNum > 0)
						clientOrderBuilder.setOrderStatus(OrderStatus.STATUS_PARTREJECTED);
					else if (rejectedNum > 0)
						clientOrderBuilder.setOrderStatus(OrderStatus.STATUS_REJECTED);

				}
				changed = true;
				break;
			case STATUS_CANCELLING:
				clientOrderBuilder.setOrderStatus(OrderStatus.STATUS_CANCELLING);
				changed = true;
				break;
			case STATUS_CANCELLED:
				if (preBrokerOrder != null) {
					if (brokerOrder.getTradedVolume() > preBrokerOrder.getTradedVolume()) {
						tradevol = clientOrder.getTradedVolume() - preBrokerOrder.getTradedVolume()
								+ brokerOrder.getTradedVolume();
						clientOrderBuilder.setTradedVolume(tradevol);
						clientOrderBuilder.setTradedPrice((clientOrder.getTradedVolume() * clientOrder.getTradedPrice()
								- preBrokerOrder.getTradedVolume() * preBrokerOrder.getTradedPrice()
								+ brokerOrder.getTradedVolume() * brokerOrder.getTradedPrice()) / tradevol);
					}
				} else {
					tradevol = clientOrder.getTradedVolume() + brokerOrder.getTradedVolume();
					clientOrderBuilder.setTradedVolume(tradevol);
					clientOrderBuilder.setTradedPrice((clientOrder.getTradedVolume() * clientOrder.getTradedPrice()
							+ brokerOrder.getTradedVolume() * brokerOrder.getTradedPrice()) / tradevol);
				}
				if (tradevol == clientOrder.getTotalVolume())
					clientOrderBuilder.setOrderStatus(OrderStatus.STATUS_ALLTRADED);
				else if (tradevol > 0)
					clientOrderBuilder.setOrderStatus(OrderStatus.STATUS_PARTTRADED);
				if (tradedNum + cancelledNum + rejectedNum + 1 == totalNum) {
					clientOrderBuilder.setOrderStatus(OrderStatus.STATUS_CANCELLED);
					clientOrderBuilder.setCancelTime(Long.parseLong(OrderManagement.df.format(new Date())));
				}
				changed = true;
				break;
			case STATUS_NOTTRADED:
				if (!clientOrder.hasOrderTime()) {
					clientOrderBuilder.setOrderTime(brokerOrder.getOrderTime());
					changed = true;
				}
				break;
			case STATUS_PARTTRADED:
				if (preBrokerOrder != null) {
					if (brokerOrder.getTradedVolume() > preBrokerOrder.getTradedVolume()) {
						tradevol = clientOrder.getTradedVolume() - preBrokerOrder.getTradedVolume()
								+ brokerOrder.getTradedVolume();
						clientOrderBuilder.setTradedVolume(tradevol);
						clientOrderBuilder.setTradedPrice((clientOrder.getTradedVolume() * clientOrder.getTradedPrice()
								- preBrokerOrder.getTradedVolume() * preBrokerOrder.getTradedPrice()
								+ brokerOrder.getTradedVolume() * brokerOrder.getTradedPrice()) / tradevol);
					}
				} else {
					tradevol = clientOrder.getTradedVolume() + brokerOrder.getTradedVolume();
					clientOrderBuilder.setTradedVolume(tradevol);
					clientOrderBuilder.setTradedPrice((clientOrder.getTradedVolume() * clientOrder.getTradedPrice()
							+ brokerOrder.getTradedVolume() * brokerOrder.getTradedPrice()) / tradevol);
				}
				if (clientOrder.getOrderStatus() != OrderStatus.STATUS_CANCELLING)
					clientOrderBuilder.setOrderStatus(OrderStatus.STATUS_PARTTRADED);
				changed = true;
				break;
			case STATUS_REJECTED:
				String rejMsg = clientOrder.hasMessage() ? clientOrder.getMessage() + ";" : "";
				clientOrderBuilder.setMessage(rejMsg + brokerOrder.getMessage());
				if (tradedNum + cancelledNum + rejectedNum + 1 == totalNum) {
					if (cancelledNum > 0) {
						clientOrderBuilder.setOrderStatus(OrderStatus.STATUS_CANCELLED);
						clientOrderBuilder.setCancelTime(Long.parseLong(OrderManagement.df.format(new Date())));
					} else if (clientOrderBuilder.getTradedVolume() > 0)
						clientOrderBuilder.setOrderStatus(OrderStatus.STATUS_PARTREJECTED);
					else
						clientOrderBuilder.setOrderStatus(OrderStatus.STATUS_REJECTED);
				}
				changed = true;
				break;
			case STATUS_UNKNOWN:
				break;
			}
		}
		if (changed) {
			ClientOrder newOrder = clientOrderBuilder.build();
			ClientOrderCache.getInstance().update(newOrder);
			SanjinClientMessage.Builder clientMsgBuilder = SanjinClientMessage.newBuilder();
			GuiSendMessageManager.getInstance().sendMessage(newOrder.getClientId(),
					clientMsgBuilder.setMsgType(CLIENTMSGTYPE.Client_Order).setMsgUseType(MSGUSETYPE.Notification)
							.addClientOrder(newOrder).build());
			return newOrder;
		}
		return null;
	}

	public void updateBrokerPosition(BrokerOrder preBrokerOrder, BrokerOrder brokerOrder) {
		BrokerPosition oldPos = BrokerPositionCache.getInstance().getPosition(brokerOrder.getBrokerId(),
				brokerOrder.getSymbol());
		if (oldPos != null) {
			BrokerPosition.Builder posBuilder = oldPos.toBuilder();
			if (preBrokerOrder != null) {
				long newTradeVol = brokerOrder.getTradedVolume() - preBrokerOrder.getTradedVolume();
				if (newTradeVol > 0) {
					if (brokerOrder.getDirection() == Direction.BUY)
						posBuilder.setPosLong(oldPos.getPosLong() + newTradeVol)
								.setPosLongPending(oldPos.getPosLongPending() - newTradeVol);
					else
						posBuilder.setPosShort(oldPos.getPosShort() + newTradeVol)
								.setPosShortPending(oldPos.getPosShortPending() - newTradeVol);
				}
			} else {
				long newTradeVol = brokerOrder.getTradedVolume();
				if (newTradeVol > 0) {
					if (brokerOrder.getDirection() == Direction.BUY)
						posBuilder.setPosLong(oldPos.getPosLong() + newTradeVol)
								.setPosLongPending(oldPos.getPosLongPending() - newTradeVol);
					else
						posBuilder.setPosShort(oldPos.getPosShort() + newTradeVol)
								.setPosShortPending(oldPos.getPosShortPending() - newTradeVol);
				}
			}
			if (brokerOrder.getOrderStatus() == OrderStatus.STATUS_CANCELLED
					|| brokerOrder.getOrderStatus() == OrderStatus.STATUS_REJECTED) {
				if (brokerOrder.getDirection() == Direction.BUY)
					posBuilder.setPosLongPending(posBuilder.getPosLongPending()
							- (brokerOrder.getTotalVolume() - brokerOrder.getTradedVolume()));
				else
					posBuilder.setPosShortPending(posBuilder.getPosShortPending()
							- (brokerOrder.getTotalVolume() - brokerOrder.getTradedVolume()));
			}
			BrokerPosition newPos = posBuilder.build();
			BrokerPositionCache.getInstance().putPosition(newPos);
			logger.info("preBrokerPosition" + TextFormat.shortDebugString(oldPos));
			logger.info("newBrokerPosition" + TextFormat.shortDebugString(newPos));
		}
	}

	public void updateClientPosition(String clientId, BrokerOrder preBrokerOrder, BrokerOrder brokerOrder) {
		ClientPosition oldPos = ClientPositionCache.getInstance().getPosition(clientId, brokerOrder.getSymbol(),
				brokerOrder.getExchange());
		if (oldPos != null) {
			ClientPosition.Builder posBuilder = oldPos.toBuilder();
			if (preBrokerOrder != null) {
				long newTradeVol = brokerOrder.getTradedVolume() - preBrokerOrder.getTradedVolume();
				if (newTradeVol > 0) {// 交易量为正
					if (brokerOrder.getDirection() == Direction.BUY)// 交易为买入，则更新Long
						posBuilder.setPosLong(oldPos.getPosLong() + newTradeVol)
								.setLongMarket(oldPos.getLongMarket()
										- preBrokerOrder.getTradedPrice() * preBrokerOrder.getTradedVolume()
										+ brokerOrder.getTotalVolume() * brokerOrder.getTradedPrice())
								.setPosLongPending(oldPos.getPosLongPending() - newTradeVol);
					else
						posBuilder.setPosShort(oldPos.getPosShort() + newTradeVol)
								.setShortMarket(oldPos.getShortMarket()
										- preBrokerOrder.getTradedPrice() * preBrokerOrder.getTradedVolume()
										+ brokerOrder.getTotalVolume() * brokerOrder.getTradedPrice())
								.setPosShortPending(oldPos.getPosShortPending() - newTradeVol);
				}
			} else {
				long newTradeVol = brokerOrder.getTradedVolume();
				if (newTradeVol > 0) {
					if (brokerOrder.getDirection() == Direction.BUY)
						posBuilder.setPosLong(oldPos.getPosLong() + newTradeVol)
								.setLongMarket(oldPos.getLongMarket()
										+ brokerOrder.getTotalVolume() * brokerOrder.getTradedPrice())
								.setPosLongPending(oldPos.getPosLongPending() - newTradeVol);
					else
						posBuilder.setPosShort(oldPos.getPosShort() + newTradeVol)
								.setShortMarket(oldPos.getShortMarket()
										+ brokerOrder.getTotalVolume() * brokerOrder.getTradedPrice())
								.setPosShortPending(oldPos.getPosShortPending() - newTradeVol);
				}
			}
			if (brokerOrder.getOrderStatus() == OrderStatus.STATUS_CANCELLED
					|| brokerOrder.getOrderStatus() == OrderStatus.STATUS_REJECTED
					|| brokerOrder.getOrderStatus() == OrderStatus.STATUS_PARTREJECTED) {
				if (brokerOrder.getDirection() == Direction.BUY)
					posBuilder.setPosLongPending(posBuilder.getPosLongPending()
							- (brokerOrder.getTotalVolume() - brokerOrder.getTradedVolume()));
				else
					posBuilder.setPosShortPending(posBuilder.getPosShortPending()
							- (brokerOrder.getTotalVolume() - brokerOrder.getTradedVolume()));
			}
			ClientPosition newPos = posBuilder.build();
			ClientPositionCache.getInstance().updatePosition(clientId, brokerOrder.getSymbol(),
					brokerOrder.getExchange(), newPos);
			ClientPosition posSum = ClientPositionCache.getInstance().getPositionSum(clientId, newPos.getSymbol());
			if (posSum != null) {
				SanjinClientMessage.Builder messageBuilder = SanjinClientMessage.newBuilder();
				SanjinClientMessage clmessage = messageBuilder.setMsgType(CLIENTMSGTYPE.Client_Position)
						.setMsgUseType(MSGUSETYPE.Notification).addClientPosition(posSum).build();
				GuiSendMessageManager.getInstance().sendMessage(clientId, clmessage);
			}
			logger.info("preClientPosition:" + TextFormat.shortDebugString(oldPos));
			logger.info("newClientPosition:" + TextFormat.shortDebugString(newPos));
			logger.info("clientPosSum:" + TextFormat.shortDebugString(posSum));
		}
	}

	public void updateClientAccount(String clientId, BrokerOrder preBrokerOrder, BrokerOrder brokerOrder) {
		ClientAccount account = AccountCache.getInstance().getUserById(clientId);
		if (account == null) {
			logger.error("找不到对应的ClientAccount:" + clientId + ":" + TextFormat.shortDebugString(brokerOrder));
			return;
		}
		
		ClientAccount.Builder accountBuilder = account.toBuilder();
		if (preBrokerOrder != null) {
			long newTradeVol = brokerOrder.getTradedVolume() - preBrokerOrder.getTradedVolume();
			if (newTradeVol > 0) {//交易量为正
				if (brokerOrder.getDirection() == Direction.BUY){
					double deltaUsableBalance = brokerOrder.getTotalVolume()*brokerOrder.getTradedPrice() - preBrokerOrder.getTradedPrice()*preBrokerOrder.getTradedVolume();
					double deltaFrozenBalance = newTradeVol*brokerOrder.getPrice();
					accountBuilder.setUsableBalance(accountBuilder.getUsableBalance()-deltaUsableBalance).setFrozenBalance(accountBuilder.getFrozenBalance()-deltaFrozenBalance);
				}
				else {
					double deltaBalance = brokerOrder.getTotalVolume()*brokerOrder.getTradedPrice() - preBrokerOrder.getTradedPrice()*preBrokerOrder.getTradedVolume();
					accountBuilder.setUsableBalance(accountBuilder.getUsableBalance()+deltaBalance);
				}
			}
		} else {
			long newTradeVol = brokerOrder.getTradedVolume();
			if (newTradeVol > 0) {
				if (brokerOrder.getDirection() == Direction.BUY) {
					double deltaBalance = newTradeVol*brokerOrder.getTradedPrice();
					double deltaFrozenBalance = newTradeVol*brokerOrder.getPrice();
					accountBuilder.setUsableBalance(accountBuilder.getUsableBalance()-deltaBalance).setFrozenBalance(accountBuilder.getFrozenBalance()-deltaFrozenBalance);
				}
				else {
					double deltaBalance = brokerOrder.getTotalVolume()*brokerOrder.getTradedPrice();
					accountBuilder.setUsableBalance(accountBuilder.getUsableBalance()+deltaBalance);
				}
			}
		}
		if(brokerOrder.getOrderStatus() == OrderStatus.STATUS_CANCELLED || brokerOrder.getOrderStatus() == OrderStatus.STATUS_REJECTED || brokerOrder.getOrderStatus() == OrderStatus.STATUS_PARTREJECTED) {
			if (brokerOrder.getDirection() == Direction.BUY) {
				double deltaFrozenBalance = (brokerOrder.getTotalVolume()-brokerOrder.getTradedVolume())*brokerOrder.getPrice();
				accountBuilder.setFrozenBalance(accountBuilder.getFrozenBalance()-deltaFrozenBalance);
			}
		}
		ClientAccount newAccount = accountBuilder.build();
		AccountCache.getInstance().updateAccount(clientId, newAccount);
		
		SanjinClientMessage.Builder messageBuilder = SanjinClientMessage.newBuilder();
		SanjinClientMessage clmessage = messageBuilder.setMsgType(CLIENTMSGTYPE.Client_Account).setMsgUseType(MSGUSETYPE.Notification)
				.setClientAccount(newAccount).build();
		GuiSendMessageManager.getInstance().sendMessage(clientId, clmessage);
		
		logger.info("preClientPosition:"+TextFormat.shortDebugString(account));
		logger.info("newClientPosition:"+TextFormat.shortDebugString(newAccount));
	}

}
