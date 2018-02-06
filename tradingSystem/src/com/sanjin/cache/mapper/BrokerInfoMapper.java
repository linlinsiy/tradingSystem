package com.sanjin.cache.mapper;

import java.util.List;

import com.sanjin.cache.bean.DbBroker;

public interface BrokerInfoMapper {	
	List<DbBroker> getAllBrokers();
	DbBroker getBrokerById(String brokerId);
	List<DbBroker> getBrokersByCondition(DbBroker broker);
}
