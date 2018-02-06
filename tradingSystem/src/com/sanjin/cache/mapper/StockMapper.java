package com.sanjin.cache.mapper;

import java.util.List;

import com.sanjin.cache.bean.DbStock;

public interface StockMapper {
	List<DbStock> getAllStocks();	
	DbStock getStockById(String stockId);
	List<DbStock> getStockByCondition(DbStock stock);
	int updateStock(DbStock stock);
	int deleteStockById(String stockId);
	int insertStock(DbStock stock);

}
