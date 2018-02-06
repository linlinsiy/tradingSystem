package com.sanjin.cache.bean;

public class DbBrokerOrder {
	private String orderId;
	private String brokerId;
	private String clientOrderId;
	private String stockId;
	private String marketId;
	private String date;
	private Integer direction;
	private Double price;
	private Double tradePrice;
	private Integer totalVolume;
	private Integer tradeVolume;
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
	public String getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}
	public String getClientOrderId() {
		return clientOrderId;
	}
	public void setClientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
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
	@Override
	public String toString() {
		return "DbBrokerOrder [orderId=" + orderId + ", brokerId=" + brokerId + ", clientOrderId=" + clientOrderId
				+ ", stockId=" + stockId + ", marketId=" + marketId + ", date=" + date + ", direction=" + direction
				+ ", price=" + price + ", tradePrice=" + tradePrice + ", totalVolume=" + totalVolume + ", tradeVolume="
				+ tradeVolume + ", orderTime=" + orderTime + ", cancelTime=" + cancelTime + ", orderStatus="
				+ orderStatus + ", message=" + message + "]";
	}
	
}
