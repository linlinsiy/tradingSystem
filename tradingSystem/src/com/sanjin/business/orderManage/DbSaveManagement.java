package com.sanjin.business.orderManage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sanjin.cache.BrokerOrderCache;
import com.sanjin.cache.BrokerPositionCache;
import com.sanjin.cache.ClientOrderCache;
import com.sanjin.cache.ClientPositionCache;

public class DbSaveManagement implements Runnable {
	private static Logger logger = LogManager.getLogger("dbsave");
	BrokerPositionCache brokerPositionCache = BrokerPositionCache.getInstance();
	ClientPositionCache clientPositionCache = ClientPositionCache.getInstance();
	BrokerOrderCache brokerOrderCache = BrokerOrderCache.getInstance();
	ClientOrderCache clientOrderCache = ClientOrderCache.getInstance();
	@Override
	public void run() {
		logger.info("DbSave Management已经开启");
		while(true){
			try {
				Thread.sleep(1000);
				brokerOrderCache.flashToDb();
				clientOrderCache.flashToDb();
				brokerPositionCache.flashToDb();
				clientPositionCache.flashToDb();				
			} catch (Throwable e) {
				logger.error("Throwable异常！",e);
				e.printStackTrace();
			}
		}
	}

}
