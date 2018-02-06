package com.sanjin.business.orderManage;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.protobuf.TextFormat;
import com.sanjin.bean.StockPoolClientProtos.CLIENTMSGTYPE;
import com.sanjin.bean.StockPoolClientProtos.ClientCancelOrder;
import com.sanjin.bean.StockPoolClientProtos.ClientOrder;
import com.sanjin.bean.StockPoolClientProtos.MSGUSETYPE;
import com.sanjin.bean.StockPoolClientProtos.OrderStatus;
import com.sanjin.bean.StockPoolClientProtos.SanjinClientMessage;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerCancelOrder;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerOrder;
import com.sanjin.bean.StockPoolGatewayProtos.GTMSGTYPE;
import com.sanjin.bean.StockPoolGatewayProtos.SanjinGTMessage;
import com.sanjin.business.client.communication.GuiSendMessageManager;
import com.sanjin.business.gateway.FixClient;
import com.sanjin.business.gateway.GatewayManager;
import com.sanjin.cache.BrokerOrderCache;
import com.sanjin.cache.ClientOrderCache;
import com.sanjin.comm.OrderUtils;

public class CancelOrderManagement implements Runnable {
	private static Logger logger = LogManager.getLogger("trading");

	@Override
	public void run() {
		logger.info("CancelOrder Management已经开启");
		while (true) {
			ClientCancelOrder cancelOrder = ClientOrderCache.getInstance().popCancelOrder();
			
			if (cancelOrder != null) {
				logger.info(TextFormat.shortDebugString(cancelOrder));
				ClientOrder origOrder = ClientOrderCache.getInstance().getOrder(cancelOrder.getOrigOrderid());
				if(origOrder==null) {
					logger.error("无法找到原委托:origid:"+cancelOrder.getOrigOrderid());
					continue;
				}
				logger.info(TextFormat.shortDebugString(origOrder));
				if (origOrder.getOrderStatus() == OrderStatus.STATUS_CANCELLING
						|| OrderUtils.isOrderFinished(origOrder))
					continue;
				Object orderLock = OrderLock.getLock(origOrder.getClientId());
				synchronized (orderLock) {
					try {
						ClientOrder.Builder clientOrderBuilder = origOrder.toBuilder();
						clientOrderBuilder.setOrderStatus(OrderStatus.STATUS_CANCELLING);
						Set<String> brokerOrderIds = BrokerOrderCache.getInstance()
								.getClientBrokerIdSet(origOrder.getClientOrderId());
						if(brokerOrderIds == null)
							logger.warn("没有找到brokerSet");
						if (brokerOrderIds != null && brokerOrderIds.size() > 0) {
							logger.info("需要撤单的数量： "+brokerOrderIds.size());
							for (String brokerId : brokerOrderIds) {
								BrokerOrder brokerOrder = BrokerOrderCache.getInstance().getBrokerOrder(brokerId);
								logger.info("需要撤单的委托"+TextFormat.shortDebugString(brokerOrder));
								if (!OrderUtils.isOrderFinished(brokerOrder)) {
									FixClient client = GatewayManager.getInstance()
											.getClient(brokerOrder.getBrokerId());
									BrokerOrder.Builder brokerOrderBuilder = brokerOrder.toBuilder();
									brokerOrderBuilder.setOrderStatus(OrderStatus.STATUS_CANCELLING);
									BrokerOrderCache.getInstance().addBrokerOrder(brokerOrderBuilder.build());
									BrokerCancelOrder.Builder brokerCancelOrder = BrokerCancelOrder.newBuilder();
									brokerCancelOrder.setCancelOrderid(FixClient.generateID(brokerOrder.getBrokerId()))
											.setDirection(brokerOrder.getDirection())
											.setOrigOrderid(brokerOrder.getBrokerOrderId())
											.setSymbol(brokerOrder.getSymbol());
									SanjinGTMessage.Builder gtBuilder = SanjinGTMessage.newBuilder();
									SanjinGTMessage gtMsg = gtBuilder.setMsgType(GTMSGTYPE.Broker_Cancel_Order).setCancelOrder(brokerCancelOrder.build()).build();
									if (client != null && client.isConnected()) {
										client.sendMessage(gtMsg);
									} else {
										logger.error("找不到对应broker gateway："+brokerOrder.getBrokerId());
									}
								}
							}
						}
						ClientOrder newClientOrder = clientOrderBuilder.build();
						ClientOrderCache.getInstance().update(newClientOrder);
						SanjinClientMessage.Builder clientMsgBuilder = SanjinClientMessage.newBuilder();
						clientMsgBuilder.setMsgType(CLIENTMSGTYPE.Client_Order).setMsgUseType(MSGUSETYPE.Notification).addClientOrder(newClientOrder);
						GuiSendMessageManager.getInstance().sendMessage(origOrder.getClientId(), clientMsgBuilder.build());
					} catch (Throwable e) {
						e.printStackTrace();
						logger.error("撤单出现错误", e);
					}
				}

			}
		}

	}
}
