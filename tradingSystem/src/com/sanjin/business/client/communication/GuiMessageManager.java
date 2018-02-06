package com.sanjin.business.client.communication;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.sanjin.bean.StockPoolClientProtos.CLIENTMSGTYPE;
import com.sanjin.bean.StockPoolClientProtos.ClientAccount;
import com.sanjin.bean.StockPoolClientProtos.ClientCancelOrder;
import com.sanjin.bean.StockPoolClientProtos.ClientOrder;
import com.sanjin.bean.StockPoolClientProtos.ClientPosition;
import com.sanjin.bean.StockPoolClientProtos.ClientRelatedSymbol;
import com.sanjin.bean.StockPoolClientProtos.ClientUserInfo;
import com.sanjin.bean.StockPoolClientProtos.Direction;
import com.sanjin.bean.StockPoolClientProtos.MSGUSETYPE;
import com.sanjin.bean.StockPoolClientProtos.OrderStatus;
import com.sanjin.bean.StockPoolClientProtos.QueryRequest;
import com.sanjin.bean.StockPoolClientProtos.SanjinClientMessage;
import com.sanjin.bean.StockPoolClientProtos.StockInfo;
import com.sanjin.bean.StockPoolClientProtos.UsablePosition;
import com.sanjin.cache.AccountCache;
import com.sanjin.cache.ClientOrderCache;
import com.sanjin.cache.ClientPositionCache;
import com.sanjin.cache.StockCache;
import com.sanjin.cache.UserCache;
import com.sanjin.cache.bean.DbUserInfo;
import com.sanjin.comm.OrderUtils;

public class GuiMessageManager implements Runnable {
	private static Logger logger = LogManager.getLogger("client");
	private static GuiMessageManager cache;
	private ConcurrentLinkedQueue<GuiMsgStructor> messageQueue = new ConcurrentLinkedQueue<GuiMsgStructor>();

	public static synchronized GuiMessageManager getInstance() {
		if (cache != null) {
			return cache;
		}
		synchronized (GuiMessageManager.class) {
			if (cache == null) {
				cache = new GuiMessageManager();
			}
			return cache;
		}
	}

	private GuiMessageManager() {
	}

	public void addMessage(IoSession session, SanjinClientMessage message) {
		messageQueue.add(new GuiMsgStructor(session, message));
	}

	public void run() {
		while (true) {
			try {
				if (!messageQueue.isEmpty()) {
					GuiMsgStructor guiMsg = messageQueue.poll();
					SanjinClientMessage clientMsg = guiMsg.getMsg();
					IoSession session = guiMsg.getSession();

					switch (clientMsg.getMsgType()) {
					case Client_Cancel_Order:
						for (ClientCancelOrder cancelOrder : clientMsg.getClientCancelOrderList()) {
							processMessage(session, cancelOrder.toBuilder()
									.setCancelOrderid(ClientOrderCache.getInstance().generateClientId()).build());
						}
						break;
					case Client_Login:
						break;
					case Client_Order:
						long ordertime = Long.parseLong(ClientOrderCache.df.format(new Date()));
						for (ClientOrder order : clientMsg.getClientOrderList()) {
							processMessage(session, order.toBuilder()
									.setClientOrderId(ClientOrderCache.getInstance().generateClientId()).setOrderTime(ordertime).build());
						}
						break;
					case Query_Request:
						processMessage(session, clientMsg.getQueryRequest());
						break;
					default:
						break;
					}
				}
			} catch (Throwable e) {
				e.printStackTrace();
				logger.error("client message process error", e);
			}
		}
	}

	private void processMessage(IoSession session, ClientCancelOrder clientCancelOrder) {
		ClientOrderCache.getInstance().push(clientCancelOrder);
	}

	private void processMessage(IoSession session, ClientOrder clientOrder) {
		String validVal = OrderUtils.isOrderValid(clientOrder);
		SanjinClientMessage.Builder cmsgBuilder = SanjinClientMessage.newBuilder();
		Set<IoSession> sessionList = GuiServer.getInstance().getSessionList(clientOrder.getClientId());
		if (sessionList == null || !sessionList.contains(session))
			GuiServer.getInstance().addSession(clientOrder.getClientId(), session);
		
		if (validVal == null) {
			ClientOrderCache.getInstance().push(clientOrder);
			
			GuiSendMessageManager.getInstance().sendMessage(clientOrder.getClientId(),
					cmsgBuilder.setMsgType(CLIENTMSGTYPE.Client_Order).setMsgUseType(MSGUSETYPE.Notification)
							.clearOrderPlaceResponse().addClientOrder(clientOrder).build());
		} else {
			GuiSendMessageManager.getInstance().sendMessage(clientOrder.getClientId(), cmsgBuilder
					.setMsgType(CLIENTMSGTYPE.Client_Order).setMsgUseType(MSGUSETYPE.Notification)
					.addClientOrder(
							clientOrder.toBuilder().setOrderStatus(OrderStatus.STATUS_REJECTED).setMessage(validVal))
					.build());
		}

	}

	private void processMessage(IoSession session, QueryRequest queryRequest) {
		if (queryRequest != null) {
			Set<IoSession> sessionList = GuiServer.getInstance().getSessionList(queryRequest.getClientId());
			if (sessionList == null || !sessionList.contains(session))
				GuiServer.getInstance().addSession(queryRequest.getClientId(), session);
			switch (queryRequest.getQueryType()) {
			case QUERY_STOCK:
				queryStockInfo(session,queryRequest.getClientId());
				break;
			case QUERY_POSITION:
				queryPosition(session,queryRequest.getClientId());
				break;
			case QUERY_ORDER:
				queryOrder(session,queryRequest.getClientId());
				break;
			case QUERY_ACCOUNT:
				queryAccount(session,queryRequest.getClientId());
				break;
			case QUERY_USERINFO:
				queryUserInfo(session,queryRequest.getClientId());
				break;
			case QUERY_POS_BUY:
				queryPosBuy(session,queryRequest);
				break;
			case QUERY_POS_SELL:
				queryPosSell(session,queryRequest);
				break;
			case QUERY_RELATED_SYMBOL:
				queryRelatedSymbol(session,queryRequest.getClientId());
				break;
			default:
				break;
			}
		}
	}


	public void queryRelatedSymbol(IoSession session, String clientId) {
		List<String> childList = UserCache.getInstance().getChildList(clientId);
		if(childList == null || childList.size()==0)
			return;
		HashSet<String> symbolSet = new HashSet<String>();
		for(String child:childList) {
			Set<String> childSymbolSet = ClientPositionCache.getInstance().getPositionSymbol(child);
			if(childSymbolSet!=null) {
				for(String str:childSymbolSet)
					symbolSet.add(str);
			}
		}
		SanjinClientMessage.Builder msgBuilder = SanjinClientMessage.newBuilder();
		msgBuilder.setMsgType(CLIENTMSGTYPE.Client_Related_Symbol).setMsgUseType(MSGUSETYPE.Response);
		ClientRelatedSymbol.Builder symbolMsg = ClientRelatedSymbol.newBuilder();
		symbolMsg.setClientId(clientId);
		for(String str:symbolSet)
			symbolMsg.addSymbol(str);
		msgBuilder.setClientRelatedSymbol(symbolMsg);
		session.write(msgBuilder.build());
	}

	private void queryPosSell(IoSession session, QueryRequest queryRequest) {
		if(!queryRequest.hasClientId() || !queryRequest.hasStock()) {
			logger.warn("收到的消息未包含clientId或者stock",queryRequest);
			return;
		}
		ClientPosition clientPos = ClientPositionCache.getInstance().getSummaryPosition(queryRequest.getClientId(), queryRequest.getStock());
		if(clientPos == null) {
			logger.warn("找不到对应的position",queryRequest);
			return;
		}
		UsablePosition.Builder usableMsg = UsablePosition.newBuilder();
		usableMsg.setClientId(queryRequest.getClientId());
		usableMsg.setSymbol(queryRequest.getStock());
		usableMsg.setDirection(Direction.SELL);
		usableMsg.setUsablePos(clientPos.getPosition()-clientPos.getPosShort()-clientPos.getPosShortPending());
		SanjinClientMessage.Builder msgBuilder = SanjinClientMessage.newBuilder();
		msgBuilder.setMsgType(CLIENTMSGTYPE.Usable_Position).setMsgUseType(MSGUSETYPE.Response).setUsablePosition(usableMsg);
		session.write(msgBuilder.build());
		
	}

	private void queryPosBuy(IoSession session, QueryRequest queryRequest) {
		if(!queryRequest.hasClientId() || !queryRequest.hasStock() || !queryRequest.hasPrice()) {
			logger.warn("收到的消息未包含clientId或者stock或者价格",queryRequest);
			return;
		}
		ClientPosition clientPos = ClientPositionCache.getInstance().getSummaryPosition(queryRequest.getClientId(), queryRequest.getStock());
		if(clientPos == null) {
			logger.warn("找不到对应的position",queryRequest);
			return;
		}
		ClientAccount account = AccountCache.getInstance().getUserById(queryRequest.getClientId());
		if(account == null) {
			logger.warn("找不到对应的account",queryRequest);
			return;
		}
		long canbuyByAccount = (long)((account.getUsableBalance()-account.getFrozenBalance())/queryRequest.getPrice()/100);
		long canbuyByPos = clientPos.getPosition()-clientPos.getPosLong()-clientPos.getPosLongPending();
		UsablePosition.Builder usableMsg = UsablePosition.newBuilder();
		usableMsg.setClientId(queryRequest.getClientId());
		usableMsg.setSymbol(queryRequest.getStock());
		usableMsg.setDirection(Direction.BUY);
		usableMsg.setUsablePos(canbuyByAccount<canbuyByPos?canbuyByAccount:canbuyByPos);
		SanjinClientMessage.Builder msgBuilder = SanjinClientMessage.newBuilder();
		msgBuilder.setMsgType(CLIENTMSGTYPE.Usable_Position).setMsgUseType(MSGUSETYPE.Response).setUsablePosition(usableMsg);
		session.write(msgBuilder.build());
		
	}

	public void queryStockInfo(IoSession session, String clientId) {
		SanjinClientMessage.Builder msgBuilder = SanjinClientMessage.newBuilder();
		msgBuilder.setMsgType(CLIENTMSGTYPE.Stock_Info).setMsgUseType(MSGUSETYPE.Response);

		Collection<StockInfo> stockInfos = StockCache.getInstance().getAllStock();

		for (StockInfo stockInfo : stockInfos) {
			msgBuilder.addStockInfo(stockInfo);
		}

		SanjinClientMessage clientMsg = msgBuilder.build();
		session.write(clientMsg);
	}

	public void queryPosition(IoSession session, String clientId) {
		SanjinClientMessage.Builder msgBuilder = SanjinClientMessage.newBuilder();
		msgBuilder.setMsgType(CLIENTMSGTYPE.Client_Position).setMsgUseType(MSGUSETYPE.Response);

		Set<String> symbolSet = ClientPositionCache.getInstance().getPositionSymbol(clientId);

		if (symbolSet == null || symbolSet.isEmpty()) {
			logger.error("无法正确获取客户持仓:" + clientId);
		} else {
			for (String symbol : symbolSet) {
				System.out.println(symbol);
				msgBuilder.addClientPosition(ClientPositionCache.getInstance().getPositionSum(clientId, symbol));
			}
			SanjinClientMessage clientMsg = msgBuilder.build();
			session.write(clientMsg);
		}

	}

	public void queryOrder(IoSession session, String clientId) {
		SanjinClientMessage.Builder msgBuilder = SanjinClientMessage.newBuilder();
		msgBuilder.setMsgType(CLIENTMSGTYPE.Client_Order).setMsgUseType(MSGUSETYPE.Response);

		Set<String> orderIdSet = ClientOrderCache.getInstance().getClientOrders(clientId);

		if (orderIdSet == null || orderIdSet.isEmpty()) {
			SanjinClientMessage clientMsg = msgBuilder.build();
			session.write(clientMsg);
			logger.error("无法正确获取客户订单:" + clientId);
		} else {
			for (String orderId : orderIdSet)
				msgBuilder.addClientOrder(ClientOrderCache.getInstance().getOrder(orderId));

			SanjinClientMessage clientMsg = msgBuilder.build();
			session.write(clientMsg);
		}

	}
	

	public void queryAccount(IoSession session, String clientId) {
		SanjinClientMessage.Builder msgBuilder = SanjinClientMessage.newBuilder();
		msgBuilder.setMsgType(CLIENTMSGTYPE.Client_Account).setMsgUseType(MSGUSETYPE.Response);
		
		ClientAccount clientAccount = AccountCache.getInstance().getUserAccount(clientId);
		ClientAccount.Builder builder = ClientAccount.newBuilder();
		builder.setClientId(clientAccount.getClientId());
		builder.setFrozenBalance(clientAccount.getFrozenBalance());
		builder.setInitBalance(clientAccount.getInitBalance());
		builder.setUsableBalance(clientAccount.getUsableBalance());
		msgBuilder.setClientAccount(clientAccount);

		SanjinClientMessage clientMsg = msgBuilder.build();
		session.write(clientMsg);
	}

	public void queryUserInfo(IoSession session, String clientId) {
		SanjinClientMessage.Builder msgBuilder = SanjinClientMessage.newBuilder();
		msgBuilder.setMsgType(CLIENTMSGTYPE.Client_User_Info).setMsgUseType(MSGUSETYPE.Response);

		DbUserInfo dbUserInfo = UserCache.getInstance().getUserById(clientId);

		ClientUserInfo.Builder builder = ClientUserInfo.newBuilder();
		builder.setClientId(dbUserInfo.getUserId());
		builder.setClientName(dbUserInfo.getUserName());
		msgBuilder.addUserInfos(builder.build());

		List<String> childIds = UserCache.getInstance().getChildList(clientId);

		if (childIds == null || childIds.isEmpty()) {
			logger.error("无法正确获取用户子账户:" + clientId);
		} else {
			for (String childId : childIds) {
				dbUserInfo = UserCache.getInstance().getUserById(childId);

				builder = ClientUserInfo.newBuilder();
				builder.setClientId(dbUserInfo.getUserId());
				builder.setClientName(dbUserInfo.getUserName());
				msgBuilder.addUserInfos(builder.build());
			}
		}

		SanjinClientMessage clientMsg = msgBuilder.build();
		session.write(clientMsg);
	}

}
