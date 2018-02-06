package com.sanjin.business.orderManage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.protobuf.TextFormat;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerAccount;
import com.sanjin.business.gateway.FixMessageManager;
import com.sanjin.cache.BrokerBalanceCache;

public class AccountManagement implements Runnable  {
	private static Logger logger = LogManager.getLogger("position");
	
	@Override
	public void run() {
		logger.info("Account Management已经开启");
		while (true) {
			BrokerAccount account = FixMessageManager.getInstance().popAccount();
			if(account !=null) {
				logger.info(TextFormat.shortDebugString(account));
				BrokerBalanceCache.getInstance().updateAccount(account.getBrokerId(), account.getAvailable());
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
