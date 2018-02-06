package com.sanjin.cache.bean;

public class DbUserAccount {
	private String userId;
	private Double usableBalance;
	private Double initBalance;
	private Double frozenBalance;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Double getUsableBalance() {
		return usableBalance;
	}
	public void setUsableBalance(Double usableBalance) {
		this.usableBalance = usableBalance;
	}
	public Double getInitBalance() {
		return initBalance;
	}
	public void setInitBalance(Double initBalance) {
		this.initBalance = initBalance;
	}
	public Double getFrozenBalance() {
		return frozenBalance;
	}
	public void setFrozenBalance(Double frozenBalance) {
		this.frozenBalance = frozenBalance;
	}
	public DbUserAccount() {
		super();
	}
	public DbUserAccount(String userId, Double usableBalance, Double initBalance, Double frozenBalance) {
		super();
		this.userId = userId;
		this.usableBalance = usableBalance;
		this.initBalance = initBalance;
		this.frozenBalance = frozenBalance;
	}
	@Override
	public String toString() {
		return "DbUserAccount [userId=" + userId + ", usableBalance=" + usableBalance + ", initBalance=" + initBalance
				+ ", frozenBalance=" + frozenBalance + "]";
	}
	
}
