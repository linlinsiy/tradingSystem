package com.sanjin.business.gateway;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.sanjin.bean.StockPoolGatewayProtos.BrokerAccount;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerOrder;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerPosition;

public class FixMessageManager {
	private static FixMessageManager cache;
	public static int QUEUE_LEN = 1000000;
	private ConcurrentLinkedQueue<BrokerOrder> orderQueue = new ConcurrentLinkedQueue<BrokerOrder>();
	private ArrayBlockingQueue<BrokerPosition> positionQueue = new ArrayBlockingQueue<BrokerPosition>(QUEUE_LEN);
	private ArrayBlockingQueue<BrokerAccount> accountQueue = new ArrayBlockingQueue<BrokerAccount>(QUEUE_LEN);
	
	public static synchronized FixMessageManager getInstance() {
		if (cache != null) {
			return cache;
		}
		synchronized (FixMessageManager.class) {
			if (cache == null) {
				cache = new FixMessageManager();
			}
			return cache;
		}
	}
	private FixMessageManager() {
	}
	
	public void addMessage(BrokerOrder order) {
		orderQueue.add(order);
	}
	
	public BrokerOrder popOrder() {
		return orderQueue.poll();
	}
	
	public void addMessage(BrokerPosition position) {
		positionQueue.add(position);
	}
	
	public BrokerPosition popPosition() {
		return positionQueue.poll();
	}
	
	public void addMessage(BrokerAccount account) {
		accountQueue.add(account);
	}
	
	public BrokerAccount popAccount() {
		return accountQueue.poll();
	}
}
