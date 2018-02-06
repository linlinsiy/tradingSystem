package com.sanjin.cache.mapper;

import java.util.List;

import com.sanjin.cache.bean.DbClientPosition;

public interface ClientPositionMapper {
	List<DbClientPosition> getAllClientPositions();
	DbClientPosition getClientPositionById(String userId, String stockId);
	List<DbClientPosition> getClientPositionByCondition(DbClientPosition clientPosition);
	int updateClientPosition(DbClientPosition clientPosition);
	int deleteClientPositionById(String userId, String stockId);
	int insertClientPosition(DbClientPosition clientPosition);
}
