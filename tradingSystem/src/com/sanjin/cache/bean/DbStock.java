package com.sanjin.cache.bean;

public class DbStock {
	private String stockId;
	private String marketId;
	private String stockName;
	private String stockCode;
	private Double stockClose;
	private String closeDate;
	
	public String getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getMarketId() {
		return marketId;
	}
	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public Double getStockClose() {
		return stockClose;
	}
	public void setStockClose(Double stockClose) {
		this.stockClose = stockClose;
	}
	
}
