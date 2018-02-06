package com.sanjin.business.client.communication;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.sanjin.bean.StockPoolClientProtos.CLIENTMSGTYPE;
import com.sanjin.bean.StockPoolClientProtos.CancelOrderPlaceResponse;
import com.sanjin.bean.StockPoolClientProtos.ClientLogin;
import com.sanjin.bean.StockPoolClientProtos.ClientLoginResponse;
import com.sanjin.bean.StockPoolClientProtos.LoginStatus;
import com.sanjin.bean.StockPoolClientProtos.MSGUSETYPE;
import com.sanjin.bean.StockPoolClientProtos.OrderPlaceResponse;
import com.sanjin.bean.StockPoolClientProtos.SanjinClientMessage;
import com.sanjin.cache.UserCache;
import com.sanjin.cache.bean.DbUserInfo;

public class GuiServerHandler extends IoHandlerAdapter {
	private static Logger logger = LogManager.getLogger("client");

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {

		SanjinClientMessage sanjinGUIMessage = (SanjinClientMessage) message;
		logger.info(sanjinGUIMessage);
		if (sanjinGUIMessage.getMsgType() == CLIENTMSGTYPE.Client_Login) {
			processMessage(sanjinGUIMessage.getClientLogin(), session);
		} else {
			if(!GuiServer.getInstance().isSessionValid(session)) {
				logger.error("session不合法");
				session.closeNow();
				return;
			}
			GuiMessageManager.getInstance().addMessage(session, sanjinGUIMessage);
			if(sanjinGUIMessage.getMsgType() == CLIENTMSGTYPE.Client_Cancel_Order)
				session.write(SanjinClientMessage.newBuilder().setMsgType(CLIENTMSGTYPE.Cancel_Order_Place_Response).setMsgUseType(MSGUSETYPE.Response)
						.setCancelOrderPlaceResponse(CancelOrderPlaceResponse.newBuilder().setStatus(1)).setSequence(sanjinGUIMessage.getSequence()).build());
			else if(sanjinGUIMessage.getMsgType() == CLIENTMSGTYPE.Client_Order)
				session.write(SanjinClientMessage.newBuilder().setMsgType(CLIENTMSGTYPE.Order_Place_Response).setMsgUseType(MSGUSETYPE.Response)
						.setOrderPlaceResponse(OrderPlaceResponse.newBuilder().setStatus(1)).setSequence(sanjinGUIMessage.getSequence()).build());
			else if(sanjinGUIMessage.getMsgType() == CLIENTMSGTYPE.Query_Request)
				session.write(SanjinClientMessage.newBuilder().setMsgType(CLIENTMSGTYPE.Query_Place_Response).setMsgUseType(MSGUSETYPE.Response)
						.setOrderPlaceResponse(OrderPlaceResponse.newBuilder().setStatus(1)).setSequence(sanjinGUIMessage.getSequence()).build());
		}
	}

	private void processMessage(ClientLogin clientLogin, IoSession session) {
		SanjinClientMessage.Builder msgBuilder = SanjinClientMessage.newBuilder();
		msgBuilder.setMsgType(CLIENTMSGTYPE.Client_Login_Response).setMsgUseType(MSGUSETYPE.Response);
		
		// 查找用户ID
		DbUserInfo userInfo = UserCache.getInstance().getUserById(clientLogin.getClientId());
		
		// 查找用户名
		if (userInfo == null) {
			userInfo = UserCache.getInstance().getUserByName(clientLogin.getClientId());
		}

		if (userInfo != null) {
			ClientLoginResponse.Builder clrBuilder = ClientLoginResponse.newBuilder();
			clrBuilder.setClientId(userInfo.getUserId());
			System.out.println(userInfo.getPassword()+":"+clientLogin.getPassword());
			if (userInfo.getPassword().equals(clientLogin.getPassword()))
				clrBuilder.setLoginStatus(LoginStatus.SUCCESS);
			else {
				clrBuilder.setLoginStatus(LoginStatus.FAILED);
				clrBuilder.setLoginFailMsg("用户名密码不匹配");
			}
			ClientLoginResponse clientLoginRes = clrBuilder.build();

			msgBuilder.setClientLoginResponse(clientLoginRes);
			SanjinClientMessage clientMsg = msgBuilder.build();
			logger.info(clientMsg);
			session.write(clientMsg);
			if (clientLoginRes.getLoginStatus() == LoginStatus.SUCCESS) {
				IoSession logonSession = GuiServer.getInstance().getLogonSession(userInfo.getUserId());
				if(logonSession !=null ) {
					ClientLoginResponse.Builder logoutBuilder = ClientLoginResponse.newBuilder();
					logoutBuilder.setClientId(userInfo.getUserId());
					logoutBuilder.setLoginStatus(LoginStatus.FAILED);
					logoutBuilder.setLoginFailMsg("用户以新实例登录");
					logoutBuilder.setClientName("");
					msgBuilder.setClientLoginResponse(logoutBuilder.build());
					logonSession.write(msgBuilder.build());
					GuiServer.getInstance().removeSession(userInfo.getUserId(), logonSession);
					logonSession.closeOnFlush();
				}
				GuiServer.getInstance().addValidSession(userInfo.getUserId(), session);
				GuiServer.getInstance().addSession(userInfo.getUserId(), session);
				GuiMessageManager.getInstance().queryUserInfo(session,userInfo.getUserId());
				GuiMessageManager.getInstance().queryRelatedSymbol(session,userInfo.getUserId());
			}
		} else {
			ClientLoginResponse.Builder clrBuilder = ClientLoginResponse.newBuilder();
			clrBuilder.setClientId("");
			clrBuilder.setLoginStatus(LoginStatus.FAILED);
			clrBuilder.setLoginFailMsg("没有此userId或者userName");
			ClientLoginResponse clientLoginRes = clrBuilder.build();

			msgBuilder.setClientLoginResponse(clientLoginRes);
			SanjinClientMessage clientMsg = msgBuilder.build();
			logger.info(clientMsg);
			session.write(clientMsg);
		}
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionClosed(session);
		logger.info("session关闭");
		removeSession(session);
	}

	@Override
	public void exceptionCaught(org.apache.mina.core.session.IoSession session, java.lang.Throwable cause)
			throws java.lang.Exception {
		super.exceptionCaught(session,cause);
		logger.info("session异常");
		removeSession(session);
	}

	public void removeSession(IoSession session) {
		List<String> clientList = GuiServer.getInstance().getClientList(session);
		if (clientList == null)
			return;
		for (String clientId : clientList) {
			GuiServer.getInstance().removeSession(clientId, session);
		}
		logger.info("session移除");
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		super.sessionCreated(session);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {

		super.sessionOpened(session);
	}

}
