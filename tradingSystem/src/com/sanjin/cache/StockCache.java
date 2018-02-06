package com.sanjin.cache;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sanjin.bean.StockPoolClientProtos.Exchange;
import com.sanjin.bean.StockPoolClientProtos.StockInfo;
import com.sanjin.cache.bean.DbConstant;
import com.sanjin.cache.bean.DbStock;
import com.sanjin.cache.db.DbManager;
import com.sanjin.cache.mapper.StockMapper;

public class StockCache implements Cache{
	private static Logger logger = LogManager.getLogger("db");
	private ConcurrentHashMap<String,StockInfo> stockMap = new ConcurrentHashMap<String,StockInfo>();
	private StockMapper stockMapper;
	private static StockCache cache;
	
	private StockCache() {
		stockMapper = DbManager.getInstance().getSession().getMapper(StockMapper.class);
		refreshFromDb();
	}
	
	
	public static synchronized StockCache getInstance() {
		if (cache != null) {
			return cache;
		}
		synchronized (StockCache.class) {
			if (cache == null) {
				cache = new StockCache();
			}
			return cache;
		}
	}
	
	public StockInfo getStock(String symbol) {
		if(!stockMap.containsKey(symbol))
			return null;
		return stockMap.get(symbol);
	}
	
	public Collection<StockInfo> getAllStock() {
		return stockMap.values();
	}
	
	
	@Override
	public void flashToDb() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refreshFromDb() {
		List<DbStock> stockList = stockMapper.getAllStocks();
//		Date date = new Date();
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		
		for(DbStock stk: stockList) {
//			if(!stk.getCloseDate().equals(dateFormat.format(date))) {
//				logger.error("股票日期不对");
//				throw new RuntimeException("股票日期不对");
//			}
			if(stk.getStockClose()==null){
				logger.error("没有收盘价"+stk);
				continue;
			}
			StockInfo.Builder builder = StockInfo.newBuilder();
			builder.setSymbol(stk.getStockCode());
			builder.setStkName(stk.getStockName());
			builder.setPreClosePrice(stk.getStockClose());
			BigDecimal upperlimitprice = new BigDecimal(stk.getStockClose());
			BigDecimal lowerlimitprice = new BigDecimal(stk.getStockClose());
			if(stk.getStockName().contains("ST")){
				upperlimitprice = upperlimitprice.multiply(new BigDecimal(1.05)).setScale(2, BigDecimal.ROUND_HALF_UP);
				lowerlimitprice = lowerlimitprice.multiply(new BigDecimal(0.95)).setScale(2, BigDecimal.ROUND_HALF_UP);
			} else {
				upperlimitprice = upperlimitprice.multiply(new BigDecimal(1.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
				lowerlimitprice = lowerlimitprice.multiply(new BigDecimal(0.9)).setScale(2, BigDecimal.ROUND_HALF_UP);
			}
			builder.setLowerLimitPrice(lowerlimitprice.doubleValue());
			builder.setUpperLimitPrice(upperlimitprice.doubleValue());
			builder.setMinimumPriceChange(0.01);
			builder.setQtyPerHand(100);
			if(stk.getMarketId().equals(DbConstant.MARKET_SH)) {
				builder.setExchange(Exchange.SH);
			} else {
				builder.setExchange(Exchange.SZ);
			}
			stockMap.put(stk.getStockCode(), builder.build());
		}
		
	}
	
}
