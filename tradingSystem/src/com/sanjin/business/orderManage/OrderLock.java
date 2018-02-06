package com.sanjin.business.orderManage;

import java.util.concurrent.ConcurrentHashMap;

import com.sanjin.bean.StockPoolClientProtos.Exchange;

public class OrderLock {
	public static final ConcurrentHashMap<String,Object> orderLockMap = new ConcurrentHashMap<String,Object>();
	
	public static Object getLock(String lockKey) {
		Object orderLock = OrderLock.orderLockMap.get(lockKey);
		while(orderLock == null) {
			synchronized(OrderLock.class) {
				if(!OrderLock.orderLockMap.containsKey(lockKey))
					OrderLock.orderLockMap.put(lockKey,new Object());
			}
			orderLock = OrderLock.orderLockMap.get(lockKey);
		}
		return orderLock;
	}
}
