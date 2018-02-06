package com.sanjin.business.riskManage.client;

import com.sanjin.bean.StockPoolClientProtos.ClientOrder;

public interface ClientRiskCheck {
	public String riskCheck(ClientOrder order);
}
