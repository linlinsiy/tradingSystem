package com.sanjin.cache.bean;

public class DbClientOrder {
	private String orderId;
	private String clientId;
	private String stockId;
	private String marketId;
	private String date;
	private Integer direction;
	private Double price;
	private Double tradePrice;
	private Integer totalVolume;//totalQty
	private Integer tradeVolume;//tradeQty
	private Long orderTime;
	private Long cancelTime;
	private Integer orderStatus;
	private String message;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getDirection() {
		return direction;
	}
	public void setDirection(Integer direction) {
		this.direction = direction;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getTradePrice() {
		return tradePrice;
	}
	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}
	public Integer getTotalVolume() {
		return totalVolume;
	}
	public void setTotalVolume(Integer totalVolume) {
		this.totalVolume = totalVolume;
	}
	public Integer getTradeVolume() {
		return tradeVolume;
	}
	public void setTradeVolume(Integer tradeVolume) {
		this.tradeVolume = tradeVolume;
	}

	public Long getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Long orderTime) {
		this.orderTime = orderTime;
	}
	public Long getCancelTime() {
		return cancelTime;
	}
	public void setCancelTime(Long cancelTime) {
		this.cancelTime = cancelTime;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
