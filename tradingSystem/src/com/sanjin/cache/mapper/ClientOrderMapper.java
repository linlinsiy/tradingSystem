package com.sanjin.cache.mapper;

import java.util.List;

import com.sanjin.cache.bean.DbClientOrder;

public interface ClientOrderMapper {
	List<DbClientOrder> getAllClientOrders();
	int insertClientOrder(DbClientOrder dbClientOrder);
	int updateClientOrder(DbClientOrder dbClientOrder);
}
