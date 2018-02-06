package com.sanjin.cache.mapper;

import java.util.List;

import com.sanjin.cache.bean.DbBrokerOrder;

public interface BrokerOrderMapper {
	List<DbBrokerOrder> getAllBrokerOrders();
	int insertBrokerOrder(DbBrokerOrder dbBrokerOrder);
	int updateBrokerOrder(DbBrokerOrder dbBrokerOrder);
}
