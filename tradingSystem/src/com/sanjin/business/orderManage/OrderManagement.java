package com.sanjin.business.orderManage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.protobuf.TextFormat;
import com.sanjin.bean.StockPoolClientProtos.CLIENTMSGTYPE;
import com.sanjin.bean.StockPoolClientProtos.ClientAccount;
import com.sanjin.bean.StockPoolClientProtos.ClientOrder;
import com.sanjin.bean.StockPoolClientProtos.ClientPosition;
import com.sanjin.bean.StockPoolClientProtos.Direction;
import com.sanjin.bean.StockPoolClientProtos.Exchange;
import com.sanjin.bean.StockPoolClientProtos.MSGUSETYPE;
import com.sanjin.bean.StockPoolClientProtos.OrderStatus;
import com.sanjin.bean.StockPoolClientProtos.SanjinClientMessage;
import com.sanjin.bean.StockPoolGatewayProtos;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerOrder;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerPosition;
import com.sanjin.bean.StockPoolGatewayProtos.GTMSGTYPE;
import com.sanjin.bean.StockPoolGatewayProtos.SanjinGTMessage;
import com.sanjin.business.client.communication.GuiSendMessageManager;
import com.sanjin.business.gateway.FixClient;
import com.sanjin.business.gateway.FixMessageManager;
import com.sanjin.business.gateway.GatewayManager;
import com.sanjin.cache.AccountCache;
import com.sanjin.cache.BrokerCache;
import com.sanjin.cache.BrokerOrderCache;
import com.sanjin.cache.BrokerPositionCache;
import com.sanjin.cache.ClientOrderCache;
import com.sanjin.cache.ClientPositionCache;

public class OrderManagement implements Runnable {
	private static Logger logger = LogManager.getLogger("trading");
	public static DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

	@Override
	public void run() {
		logger.info("Order Management已经开启");
		while (true) {

			ClientOrder popOrder = ClientOrderCache.getInstance().pop();
			if (popOrder != null) {
				ClientOrder order = ClientOrderCache.getInstance().getOrder(popOrder.getClientOrderId());
				logger.info(TextFormat.shortDebugString(order));
				if (null == order)
					continue;
				Object orderLock = OrderLock.getLock(order.getClientId());
				synchronized (orderLock) {
					try {

						if (order.getOrderStatus() == OrderStatus.STATUS_CANCELLING
								|| order.getOrderStatus() == OrderStatus.STATUS_REJECTED)
							continue;
						Map<String, Long> brokerPosMap = BrokerPositionCache.getInstance().getAvailableMap(order);
						if (brokerPosMap == null || brokerPosMap.isEmpty()) {
							ClientOrder rejOrder = order.toBuilder().setOrderStatus(OrderStatus.STATUS_REJECTED)
									.setMessage("无法正确获取broker position").build();
							SanjinClientMessage.Builder cmsgBuilder = SanjinClientMessage.newBuilder();
							logger.error("无法正确获取broker position" + TextFormat.shortDebugString(order));
							ClientOrderCache.getInstance().update(rejOrder);
							GuiSendMessageManager.getInstance().sendMessage(order.getClientId(),
									cmsgBuilder.setMsgType(CLIENTMSGTYPE.Client_Order)
											.setMsgUseType(MSGUSETYPE.Notification)
											.setMsgUseType(MSGUSETYPE.Notification).addClientOrder(rejOrder).build());
							continue;
						}
						for (String brokerId : brokerPosMap.keySet()) {
							FixClient client = GatewayManager.getInstance().getClient(brokerId);
							BrokerOrder brokerOrder = null;
							BrokerOrder.Builder orderBuilder = BrokerOrder.newBuilder();
							orderBuilder.setBrokerId(brokerId).setBrokerOrderId(FixClient.generateID(brokerId))
									.setClientOrderId(order.getClientOrderId()).setDirection(order.getDirection())
									.setExchange(order.getExchange()).setPrice(order.getPrice())
									.setSymbol(order.getSymbol()).setTotalVolume(brokerPosMap.get(brokerId))
									.setTradedVolume(0).setTradedPrice(0.0).setOrderStatus(OrderStatus.STATUS_UNKNOWN)
									.setOrderTime(Long.parseLong(df.format(new Date())));
							if (BrokerCache.getInstance().isHkTrade(brokerId)) {
								if (order.getExchange().equals(Exchange.SH)
										|| order.getExchange().equals(Exchange.HKSH))
									orderBuilder.setExchange(Exchange.HKSH);
								else
									orderBuilder.setExchange(Exchange.HKSZ);
							}
							if (client == null || !client.isConnected()) {
								orderBuilder.setCancelTime(Long.parseLong(df.format(new Date())))
										.setOrderStatus(OrderStatus.STATUS_REJECTED)
										.setMessage(brokerId + "不存在或者无法连接," + brokerPosMap.get(brokerId) + "股无法下单");

								brokerOrder = orderBuilder.build();
								BrokerOrderCache.getInstance().addBrokerOrder(brokerOrder);
								FixMessageManager.getInstance().addMessage(brokerOrder);
								logger.warn("client 不存在或者没有正常连接上");
								logger.warn(TextFormat.shortDebugString(brokerOrder));
							} else {
								brokerOrder = orderBuilder.build();
								StockPoolGatewayProtos.SanjinGTMessage.Builder messageBuilder = StockPoolGatewayProtos.SanjinGTMessage
										.newBuilder();
								SanjinGTMessage gtmessage = messageBuilder.setMsgType(GTMSGTYPE.Broker_Order)
										.setBrokerOrder(brokerOrder).build();
								BrokerOrderCache.getInstance().addBrokerOrder(brokerOrder);
								client.getSession().write(gtmessage);

							}
							if (brokerOrder != null) {
								processPosition(order.getClientId(), brokerOrder);
								if (brokerOrder.getDirection() == Direction.BUY)
									processAccount(order.getClientId(), brokerOrder);
							}
							// TODO zhanml 想一下如果出异常如何处理
						}
					} catch (Throwable e) {
						e.printStackTrace();
						logger.error("client order to broker order error", e);
					}
				}

			}
		}

	}

	public void processPosition(String clientId, BrokerOrder order) {
		ClientPosition cpos = ClientPositionCache.getInstance().getPosition(clientId, order.getSymbol(),
				order.getExchange());
		if (cpos != null) {
			ClientPosition.Builder builder = cpos.toBuilder();
			if (order.getDirection().equals(Direction.BUY))
				builder.setPosLongPending(order.getTotalVolume() + cpos.getPosLongPending());
			else
				builder.setPosShortPending(order.getTotalVolume() + cpos.getPosShortPending());
			ClientPosition newPos = builder.build();
			ClientPositionCache.getInstance().updatePosition(clientId, order.getSymbol(), order.getExchange(), newPos);
			SanjinClientMessage.Builder cmsgBuilder = SanjinClientMessage.newBuilder();
			GuiSendMessageManager.getInstance().sendMessage(clientId,
					cmsgBuilder.setMsgType(CLIENTMSGTYPE.Client_Position).setMsgUseType(MSGUSETYPE.Notification)
							.addClientPosition(newPos).build());
		} else {
			logger.error("找不到对应的ClientPosition" + TextFormat.shortDebugString(order));
		}

		BrokerPosition bpos = BrokerPositionCache.getInstance().getPosition(order.getBrokerId(), order.getSymbol());
		if (bpos != null) {
			BrokerPosition.Builder builder = bpos.toBuilder();
			if (order.getDirection().equals(Direction.BUY))
				builder.setPosLongPending(order.getTotalVolume() + bpos.getPosLongPending());
			else
				builder.setPosShortPending(order.getTotalVolume() + bpos.getPosShortPending());
			BrokerPositionCache.getInstance().putPosition(builder.build());
		} else {
			logger.error("找不到对应的BrokerPosition" + TextFormat.shortDebugString(order));
		}
	}

	public void processAccount(String clientId, BrokerOrder order) {
		ClientAccount account = AccountCache.getInstance().getUserById(clientId);
		if (account == null) {
			logger.error("找不到对应的ClientAccount:" + clientId + ":" + TextFormat.shortDebugString(order));
			return;
		}
		ClientAccount.Builder accountBuilder = account.toBuilder();
		double orderAmt = order.getPrice() * order.getTotalVolume();
		accountBuilder.setFrozenBalance(accountBuilder.getFrozenBalance() + orderAmt);
		ClientAccount newAcc = accountBuilder.build();
		AccountCache.getInstance().updateAccount(clientId, newAcc);
		SanjinClientMessage.Builder cmsgBuilder = SanjinClientMessage.newBuilder();
		GuiSendMessageManager.getInstance().sendMessage(clientId,
				cmsgBuilder.setMsgType(CLIENTMSGTYPE.Client_Account).setMsgUseType(MSGUSETYPE.Notification)
						.setClientAccount(newAcc).build());
	}
}
