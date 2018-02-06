package com.sanjin.business.client.communication;

import java.util.concurrent.ArrayBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sanjin.bean.StockPoolClientProtos.SanjinClientMessage;

public class GuiSendMessageManager implements Runnable {
	private static Logger logger = LogManager.getLogger("client");
	public static int QUEUE_LEN = 1000000;
	private static GuiSendMessageManager cache;
	private ArrayBlockingQueue<GuiSendMsgStructor> messageQueue = new ArrayBlockingQueue<GuiSendMsgStructor>(QUEUE_LEN);

	public static synchronized GuiSendMessageManager getInstance() {
		if (cache != null) {
			return cache;
		}
		synchronized (GuiSendMessageManager.class) {
			if (cache == null) {
				cache = new GuiSendMessageManager();
			}
			return cache;
		}
	}

	private GuiSendMessageManager() {
	}
	
	public void sendMessage(String clientId,SanjinClientMessage msg) {
		messageQueue.add(new GuiSendMsgStructor(msg, clientId));
	}
	
	
	public void run() {
		while (true) {
			try {
				GuiSendMsgStructor msg = messageQueue.poll();
				if(msg!=null)
					GuiServer.getInstance().sendMessage(msg.getClientId(), msg.getMsg());
			} catch (Throwable e) {
				e.printStackTrace();
				logger.error("client message process error", e);
			}
		}
	}
}
