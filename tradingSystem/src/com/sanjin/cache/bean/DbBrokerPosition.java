package com.sanjin.cache.bean;

public class DbBrokerPosition {
	private String userId;
	private String stockId;
	private String marketId;
	private Integer formerQty;
	private Integer deltaQty;
	private Integer recallQty;
	private Integer openQty;
	private Integer sendQty;
	private Integer realQty;
	private Integer buyQty;
	private Integer sellQty;
	private Double buyAmount;
	private Double sellAmount;
	private Integer buyOrderQty;
	private Integer sellOrderQty;
	
	public DbBrokerPosition() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DbBrokerPosition(String userId, String stockId, Integer formerQty, Integer deltaQty, Integer recallQty,
			Integer openQty, Integer sendQty) {
		super();
		this.userId = userId;
		this.stockId = stockId;
		this.formerQty = formerQty;
		this.deltaQty = deltaQty;
		this.recallQty = recallQty;
		this.openQty = openQty;
		this.sendQty = sendQty;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public Integer getFormerQty() {
		return formerQty;
	}
	public void setFormerQty(Integer formerQty) {
		this.formerQty = formerQty;
	}
	public Integer getDeltaQty() {
		return deltaQty;
	}
	public void setDeltaQty(Integer deltaQty) {
		this.deltaQty = deltaQty;
	}
	public Integer getRecallQty() {
		return recallQty;
	}
	public void setRecallQty(Integer recallQty) {
		this.recallQty = recallQty;
	}
	public Integer getOpenQty() {
		return openQty;
	}
	public void setOpenQty(Integer openQty) {
		this.openQty = openQty;
	}
	public Integer getSendQty() {
		return sendQty;
	}
	public void setSendQty(Integer sendQty) {
		this.sendQty = sendQty;
	}
	public Integer getRealQty() {
		return realQty;
	}
	public void setRealQty(Integer realQty) {
		this.realQty = realQty;
	}
	public Integer getBuyQty() {
		return buyQty;
	}
	public void setBuyQty(Integer buyQty) {
		this.buyQty = buyQty;
	}
	public Integer getSellQty() {
		return sellQty;
	}
	public void setSellQty(Integer sellQty) {
		this.sellQty = sellQty;
	}
	public Double getBuyAmount() {
		return buyAmount;
	}
	public void setBuyAmount(Double buyAmount) {
		this.buyAmount = buyAmount;
	}
	public Double getSellAmount() {
		return sellAmount;
	}
	public void setSellAmount(Double sellAmount) {
		this.sellAmount = sellAmount;
	}
	public Integer getBuyOrderQty() {
		return buyOrderQty;
	}
	public void setBuyOrderQty(Integer buyOrderQty) {
		this.buyOrderQty = buyOrderQty;
	}
	public Integer getSellOrderQty() {
		return sellOrderQty;
	}
	public void setSellOrderQty(Integer sellOrderQty) {
		this.sellOrderQty = sellOrderQty;
	}
	@Override
	public String toString() {
		return "DbBrokerPosition [userId=" + userId + ", stockId=" + stockId + ", marketId=" + marketId + ", formerQty="
				+ formerQty + ", deltaQty=" + deltaQty + ", recallQty=" + recallQty + ", openQty=" + openQty
				+ ", sendQty=" + sendQty + ", realQty=" + realQty + ", buyQty=" + buyQty + ", sellQty=" + sellQty
				+ ", buyAmount=" + buyAmount + ", sellAmount=" + sellAmount + ", buyOrderQty=" + buyOrderQty
				+ ", sellOrderQty=" + sellOrderQty + "]";
	}

	
	
}
