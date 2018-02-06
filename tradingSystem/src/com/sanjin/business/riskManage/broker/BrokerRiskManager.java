package com.sanjin.business.riskManage.broker;

import java.util.ArrayList;
import java.util.HashMap;

import com.sanjin.bean.StockPoolClientProtos.Direction;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerOrder;

public class BrokerRiskManager {
	private static BrokerRiskManager cache;
	private HashMap<String,ArrayList<BrokerRiskCheck>> riskruleMap = new HashMap<String,ArrayList<BrokerRiskCheck>>();
	
	public static synchronized BrokerRiskManager getInstance() {
		if (cache != null) {
			return cache;
		}
		synchronized (BrokerRiskManager.class) {
			if (cache == null) {
				cache = new BrokerRiskManager();
			}
			return cache;
		}
	}
	
	
	
	
	public String riskCheck(String brokerId,String symbol,Direction direction,long orderQty,double orderPrice) {
		if(!riskruleMap.containsKey(brokerId))
			return null;
		for(BrokerRiskCheck rc:riskruleMap.get(brokerId)) {
			String checkval = rc.riskCheck(symbol,direction,orderQty,orderPrice);
			if(null!= checkval)
				return checkval;
		}
		return null;
	}
	
}
