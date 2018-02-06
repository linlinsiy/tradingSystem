package com.sanjin.business.riskManage.client;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sanjin.bean.StockPoolClientProtos.ClientOrder;
import com.sanjin.bean.StockPoolClientProtos.ClientPosition;
import com.sanjin.bean.StockPoolClientProtos.Direction;
import com.sanjin.cache.ClientPositionCache;
import com.sanjin.cache.UserCache;

public class ClientPositionCheck implements ClientRiskCheck {
	private static Logger logger = LogManager.getLogger("risk");

	@Override
	public String riskCheck(ClientOrder order) {
		ClientPosition clientPos = ClientPositionCache.getInstance().getPositionSum(order.getClientId(), order.getSymbol());
		String msg = "";
		
		if(clientPos == null) {
			msg = "未找到客户持仓" + order.getClientId();
			logger.error(msg);
			return msg;
		}
		else {
			if(order.getDirection() == Direction.BUY) {
				if(clientPos.getPosition() - clientPos.getPosLong() - clientPos.getPosLongPending() < order.getTotalVolume()) {
					msg = "挂单超出持仓量" + order.getClientId() + ":" + order.getClientOrderId();
					logger.error(msg);
					return msg;
				}
				else {
					msg = "订单" + order.getClientOrderId() + "通过用户风控" + clientPos.getClientId() + "持仓量:" + clientPos.getPosition() + " 已买量:" + clientPos.getPosLong() + " 挂买量:" + clientPos.getPosLongPending();
					logger.info(msg);
				}
			}
			else if(order.getDirection() == Direction.SELL) {
				if(clientPos.getPosition() - clientPos.getPosShort() - clientPos.getPosShortPending() < order.getTotalVolume()) {
					msg = "挂单超出持仓量" + order.getClientId() + ":" + order.getClientOrderId();
					logger.error(msg);
					return msg;
				}
				else {
					msg = "订单" + order.getClientOrderId() + "通过用户风控" + clientPos.getClientId() + "持仓量:" + clientPos.getPosition() + " 已卖量:" + clientPos.getPosShort() + " 挂卖量:" + clientPos.getPosShortPending();
					logger.info(msg);
				}
			}
			
			List<String> parentIdList = UserCache.getInstance().getParentList(order.getClientId());
			
			//遍历检查所有的父节点
			for(String parentId : parentIdList) {
				ClientPosition parentPos = ClientPositionCache.getInstance().getSummaryPosition(parentId, order.getSymbol());
				
				if(parentPos != null) {
					if(order.getDirection() == Direction.BUY) {
						if(parentPos.getPosition() - parentPos.getPosLong() - parentPos.getPosLongPending() < order.getTotalVolume()) {
							msg = "挂单超出持仓量" + parentPos.getClientId() + ":" + order.getClientOrderId();
							logger.error(msg);
							return msg;
						}
						else {
							msg = "订单" + order.getClientOrderId() + "通过用户风控" + clientPos.getClientId() + "持仓量:" + clientPos.getPosition() + " 已买量:" + clientPos.getPosLong() + " 挂买量:" + clientPos.getPosLongPending();
							logger.info(msg);
						}
					}
					else if(order.getDirection() == Direction.SELL) {
						if(parentPos.getPosition() - parentPos.getPosShort() - parentPos.getPosShortPending() < order.getTotalVolume()) {
							msg = "挂单超出持仓量" + parentPos.getClientId() + ":" + order.getClientOrderId();
							logger.error(msg);
							return msg;
						}
						else {
							msg = "订单" + order.getClientOrderId() + "通过用户风控" + clientPos.getClientId() + "持仓量:" + clientPos.getPosition() + " 已卖量:" + clientPos.getPosShort() + " 挂卖量:" + clientPos.getPosShortPending();
							logger.info(msg);
						}
					}
				}
				else {
					msg = "未找到持仓数据" + parentId;
					logger.error(msg);
					return msg;
				}
			}
		}
		
		return null;
	}

}
