package com.sanjin.business.client.communication;

import com.sanjin.bean.StockPoolClientProtos.SanjinClientMessage;

public class GuiSendMsgStructor {
	private SanjinClientMessage msg;
	private String clientId;
	
	
	public GuiSendMsgStructor(SanjinClientMessage msg, String clientId) {
		super();
		this.msg = msg;
		this.clientId = clientId;
	}
	public SanjinClientMessage getMsg() {
		return msg;
	}
	public void setMsg(SanjinClientMessage msg) {
		this.msg = msg;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
}
