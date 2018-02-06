package com.sanjin.business.orderManage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sanjin.business.client.communication.GuiMessageManager;
import com.sanjin.business.client.communication.GuiSendMessageManager;
import com.sanjin.business.gateway.GatewayManager;

public class ManagementExecutor {
	private static ManagementExecutor cache;
	private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(9);
	
	public static synchronized ManagementExecutor getInstance() {
		if (cache != null) {
			return cache;
		}
		synchronized (ManagementExecutor.class) {
			if (cache == null) {
				cache = new ManagementExecutor();
			}
			return cache;
		}
	}
	
	private ManagementExecutor() {
	}
	
	public void startWork() {
		fixedThreadPool.execute(new OrderManagement());
		fixedThreadPool.execute(GuiMessageManager.getInstance());
		fixedThreadPool.execute(new ExecutionManagement());
		fixedThreadPool.execute(new CancelOrderManagement());
		fixedThreadPool.execute(new PositionManagement());
		fixedThreadPool.execute(new AccountManagement());
		fixedThreadPool.execute(new DbSaveManagement());
		fixedThreadPool.execute(GatewayManager.getInstance());
		fixedThreadPool.execute(GuiSendMessageManager.getInstance());
	}
}
