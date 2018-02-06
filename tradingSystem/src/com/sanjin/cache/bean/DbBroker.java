package com.sanjin.cache.bean;
/**
 * 对应BrokerInfo
 * @author linsy
 *
 */
public class DbBroker {
	private String brokerId;
	private String brokerName;
	private Integer brokerType;
	private String account;
	private String password;
	private String gatewayIp;
	private Integer gatewayPort;
	private Integer isActive;
	private Double feeRatio;
	private Double stampRatio;
	private Double exchRatio;
	private String fundAccount;
	private Integer isHkTrade;
	public Integer getIsHkTrade() {
		return isHkTrade;
	}
	public void setIsHkTrade(Integer isHkTrade) {
		this.isHkTrade = isHkTrade;
	}
	public String getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}
	public Integer getBrokerType() {
		return brokerType;
	}
	public void setBrokerType(Integer brokerType) {
		this.brokerType = brokerType;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGatewayIp() {
		return gatewayIp;
	}
	public void setGatewayIp(String gatewayIp) {
		this.gatewayIp = gatewayIp;
	}
	public Integer getGatewayPort() {
		return gatewayPort;
	}
	public void setGatewayPort(Integer gatewayPort) {
		this.gatewayPort = gatewayPort;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public Double getFeeRatio() {
		return feeRatio;
	}
	public void setFeeRatio(Double feeRatio) {
		this.feeRatio = feeRatio;
	}
	public Double getStampRatio() {
		return stampRatio;
	}
	public void setStampRatio(Double stampRatio) {
		this.stampRatio = stampRatio;
	}
	public Double getExchRatio() {
		return exchRatio;
	}
	public void setExchRatio(Double exchRatio) {
		this.exchRatio = exchRatio;
	}
	public String getFundAccount() {
		return fundAccount;
	}
	public void setFundAccount(String fundAccount) {
		this.fundAccount = fundAccount;
	}
	@Override
	public String toString() {
		return "DbBroker [brokerId=" + brokerId + ", brokerName=" + brokerName + ", brokerType=" + brokerType
				+ ", account=" + account + ", password=" + password + ", gatewayIp=" + gatewayIp + ", gatewayPort="
				+ gatewayPort + ", isActive=" + isActive + ", feeRatio=" + feeRatio + ", stampRatio=" + stampRatio
				+ ", exchRatio=" + exchRatio + ", fundAccount=" + fundAccount + ", isHkTrade=" + isHkTrade + "]";
	}
	
	

	
}
