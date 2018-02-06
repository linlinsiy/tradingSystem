package com.sanjin.cache;

import java.util.concurrent.ConcurrentHashMap;

public class BrokerBalanceCache implements Cache {
	private static BrokerBalanceCache cache;
	private ConcurrentHashMap<String,Double> brokerAccountMap = new ConcurrentHashMap<String,Double>();
	
	public static synchronized BrokerBalanceCache getInstance() {
		if (cache != null) {
			return cache;
		}
		synchronized (BrokerBalanceCache.class) {
			if (cache == null) {
				cache = new BrokerBalanceCache();
			}
			return cache;
		}
	}
	
	private BrokerBalanceCache() {
	}
	
	public void updateAccount(String brokerId,double balance) {
		brokerAccountMap.put(brokerId, balance);
	}
	
	public Double getAccount(String brokerId) {
		if(brokerAccountMap.contains(brokerId))
			return brokerAccountMap.get(brokerId);
		return null;
	}
	
	
	@Override
	public void flashToDb() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refreshFromDb() {
		// TODO Auto-generated method stub

	}

}
