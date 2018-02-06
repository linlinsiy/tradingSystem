package com.sanjin.business.client.communication;

import org.apache.mina.core.session.IoSession;

import com.sanjin.bean.StockPoolClientProtos.SanjinClientMessage;

public class GuiMsgStructor {
	private IoSession session;
	private SanjinClientMessage msg;
	
	public GuiMsgStructor(IoSession session,SanjinClientMessage msg) {
		this.session = session;
		this.msg = msg;
	}

	public IoSession getSession() {
		return session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}

	public SanjinClientMessage getMsg() {
		return msg;
	}

	public void setMsg(SanjinClientMessage msg) {
		this.msg = msg;
	}
	
	
}
