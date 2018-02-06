package com.sanjin.business.gateway;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sanjin.cache.UserCache;
import com.sanjin.cache.BrokerCache;
import com.sanjin.cache.bean.DbBroker;

public class GatewayManager implements Runnable {
	private static Logger logger = LogManager.getLogger("gateway");
	private ConcurrentHashMap<String, FixClient> gatewayMap = new ConcurrentHashMap<String, FixClient>();
	
	private static GatewayManager cache;
	
	
	public static synchronized GatewayManager getInstance() {
		if (cache != null) {
			return cache;
		}
		synchronized (GatewayManager.class) {
			if (cache == null) {
				cache = new GatewayManager();
			}
			return cache;
		}
	}
	
	public FixClient getClient(String brokerId) {
		return gatewayMap.get(brokerId);
	}
	
	
	private GatewayManager() {
		connectGateway();
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				connectGateway();
				Thread.sleep(3000);
			}catch(Throwable e) {
				e.printStackTrace();
				logger.error("Gateway Manager error",e);
			}
		}
	}
	
	public void connectGateway() {
		ConcurrentHashMap<String, DbBroker> brokerMap  = BrokerCache.getInstance().getMap();
		for(String brokerid:brokerMap.keySet()) {
			if(!gatewayMap.containsKey(brokerid)) { 
				DbBroker broker = brokerMap.get(brokerid);
				try {
					FixClient client = new FixClient(broker.getGatewayIp(),broker.getGatewayPort());
					client.create();
					gatewayMap.put(brokerid, client);
				} catch(Exception e) {
					logger.error("error",e);
				}
			}
			FixClient client = gatewayMap.get(brokerid);
			if(!client.isConnected()) {
				synchronized(client) {
					if(!client.isConnected()) {
						client.close();
						client.create();
					}
				}
			}
		}
	}
}
