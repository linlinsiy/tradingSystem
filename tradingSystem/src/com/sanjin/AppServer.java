package com.sanjin;

import com.sanjin.business.client.communication.GuiServer;
import com.sanjin.business.orderManage.ExecutionManagement;
import com.sanjin.business.orderManage.ManagementExecutor;
import com.sanjin.cache.BrokerCache;
import com.sanjin.cache.BrokerOrderCache;
import com.sanjin.cache.BrokerPositionCache;
import com.sanjin.cache.ClientOrderCache;
import com.sanjin.cache.ClientPositionCache;
import com.sanjin.cache.UserCache;
import com.sanjin.cache.db.DbManager;
import com.sanjin.comm.Settings;

public class AppServer {
	public static void main(String[] args) {
		Settings.loadSettings();
		DbManager.getInstance();
		BrokerOrderCache.getInstance();
		ClientOrderCache.getInstance();
		ClientPositionCache.getInstance();
		BrokerPositionCache.getInstance();
		UserCache.getInstance();
		BrokerCache.getInstance();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		GuiServer.getInstance();
		ManagementExecutor.getInstance().startWork();
		System.out.println(Settings.getProperties("guiServerPort"));
		
		
	}
}
