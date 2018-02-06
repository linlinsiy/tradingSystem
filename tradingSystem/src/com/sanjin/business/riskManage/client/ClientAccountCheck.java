package com.sanjin.business.riskManage.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sanjin.bean.StockPoolClientProtos.ClientAccount;
import com.sanjin.bean.StockPoolClientProtos.ClientOrder;
import com.sanjin.bean.StockPoolClientProtos.Direction;
import com.sanjin.cache.AccountCache;

public class ClientAccountCheck implements ClientRiskCheck {
	private static Logger logger = LogManager.getLogger("risk");

	@Override
	public String riskCheck(ClientOrder order) {
		ClientAccount clientAccount = AccountCache.getInstance().getUserAccount(order.getClientId());
		String msg = "";
		
		if(clientAccount == null) {
			msg = "未找到客户资金账户" + order.getClientId();
			logger.error(msg);
			return msg;
		}
		else {
			if(order.getDirection() == Direction.BUY) {
				if(order.getTotalVolume() * order.getPrice() > clientAccount.getUsableBalance() - clientAccount.getFrozenBalance()) {
					msg = "账户资金不足" + order.getClientId() + ":" + order.getClientOrderId() + ", 可用金额:" + (clientAccount.getUsableBalance() - clientAccount.getFrozenBalance());
					logger.error(msg);
					return msg;
				}
			}
		}
		
		return null;
	}

}
