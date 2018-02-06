package com.sanjin.cache;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sanjin.cache.bean.DbBroker;
import com.sanjin.cache.db.DbManager;
import com.sanjin.cache.mapper.BrokerInfoMapper;

public class BrokerCache implements Cache{
	private static Logger logger = LogManager.getLogger("db");
	private ConcurrentHashMap<String, DbBroker> brokerMap = new ConcurrentHashMap<String, DbBroker>();
	private BrokerInfoMapper brokerInfoMapper;
	
	private static BrokerCache cache;
	
	
	public static synchronized BrokerCache getInstance() {
		if (cache != null) {
			return cache;
		}
		synchronized (BrokerCache.class) {
			if (cache == null) {
				cache = new BrokerCache();
			}
			return cache;
		}
	}
	
	public ConcurrentHashMap<String, DbBroker> getMap() {
		return brokerMap;
	}
	
	private BrokerCache() {
		brokerInfoMapper = DbManager.getInstance().getSession().getMapper(BrokerInfoMapper.class);
		refreshFromDb();
	}
	
	public boolean isHkTrade(String brokerId) {
		if(brokerMap.containsKey(brokerId) && brokerMap.get(brokerId).getIsHkTrade()==1)
			return true;
		return false;
	}
	
	@Override
	public void flashToDb() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refreshFromDb() {
		List<DbBroker> list = brokerInfoMapper.getAllBrokers();
		if(list!=null && list.size()>0) {
			for(DbBroker broker:list) {
				brokerMap.put(broker.getBrokerId(), broker);
				logger.info(broker);
				System.out.println(broker);
			}
		}
		
	}
	
}
