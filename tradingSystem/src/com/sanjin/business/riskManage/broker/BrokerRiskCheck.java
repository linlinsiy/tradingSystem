package com.sanjin.business.riskManage.broker;

import com.sanjin.bean.StockPoolClientProtos.Direction;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerOrder;

public interface BrokerRiskCheck {
	public String riskCheck(String symbol,Direction direction,long orderQty,double orderPrice);
}
