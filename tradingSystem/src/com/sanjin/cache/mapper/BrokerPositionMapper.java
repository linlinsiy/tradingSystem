package com.sanjin.cache.mapper;

import java.util.List;

import com.sanjin.cache.bean.DbBrokerPosition;

public interface BrokerPositionMapper {
	List<DbBrokerPosition> getAllBrokerPositions();
	DbBrokerPosition getBrokerPositionById(String userId, String stockId);
	List<DbBrokerPosition> getBrokerPositionByCondition(DbBrokerPosition brokerPosition);
	int updateBrokerPosition(DbBrokerPosition brokerPosition);
	int deleteBrokerPositionById(String userId, String stockId);
	int insertBrokerPosition(DbBrokerPosition brokerPosition);
}
