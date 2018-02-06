package com.sanjin.comm;

import com.sanjin.bean.StockPoolClientProtos.ClientOrder;
import com.sanjin.bean.StockPoolClientProtos.Exchange;
import com.sanjin.bean.StockPoolClientProtos.OrderStatus;
import com.sanjin.bean.StockPoolClientProtos.StockInfo;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerOrder;
import com.sanjin.cache.StockCache;

public class OrderUtils {
	public static boolean isOrderFinished(BrokerOrder order) {
		if(order == null)
			return true;
		return order.getOrderStatus()==OrderStatus.STATUS_ALLTRADED||order.getOrderStatus()==OrderStatus.STATUS_CANCELLED||order.getOrderStatus()==OrderStatus.STATUS_REJECTED;
	}
	
	public static boolean isOrderFinished(ClientOrder order) {
		if(order == null)
			return true;
		return order.getOrderStatus()==OrderStatus.STATUS_ALLTRADED||order.getOrderStatus()==OrderStatus.STATUS_CANCELLED||order.getOrderStatus()==OrderStatus.STATUS_REJECTED||order.getOrderStatus()==OrderStatus.STATUS_PARTREJECTED;
	}
	
	public static String isOrderValid(ClientOrder order) {
		if(order.getSymbol().startsWith("6") || order.getSymbol().startsWith("5") ) {
			if(order.getExchange()!=Exchange.SH)
				return "请送入正确的市场"+order.getSymbol()+":SH";
		} else {
			if(order.getExchange()!=Exchange.SZ)
				return "请送入正确的市场"+order.getSymbol()+":SZ";
		}
		if(order.getTotalVolume()<0 || order.getTotalVolume()%100 !=0)
			return "委托数量必须为正整数且为100的倍数";
		if(order.getPrice()<=0)
			return "委托价格必须为正数";
		StockInfo stock = StockCache.getInstance().getStock(order.getSymbol());
		if(stock==null) {
			return "委托股票不在预设股票中";
		}else {
			if(order.getPrice()<stock.getLowerLimitPrice() || order.getPrice()>stock.getUpperLimitPrice())
				return "委托价格在涨跌停价范围之外";
		}
		return null;
	}
}
