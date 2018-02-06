package com.sanjin.cache;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.util.ConcurrentHashSet;

import com.google.protobuf.TextFormat;
import com.sanjin.bean.StockPoolClientProtos.ClientCancelOrder;
import com.sanjin.bean.StockPoolClientProtos.ClientOrder;
import com.sanjin.bean.StockPoolClientProtos.Direction;
import com.sanjin.bean.StockPoolClientProtos.Exchange;
import com.sanjin.bean.StockPoolClientProtos.OrderStatus;
import com.sanjin.cache.bean.DbClientOrder;
import com.sanjin.cache.bean.DbConstant;
import com.sanjin.cache.db.DbManager;
import com.sanjin.cache.mapper.ClientOrderMapper;
import com.sanjin.comm.OrderUtils;

public class ClientOrderCache implements Cache{
	private static Logger logger = LogManager.getLogger("dbsave");
	private static Logger readLogger = LogManager.getLogger("db");
	private static ClientOrderCache cache;
	private volatile int index = 0;
	public static DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	public static DateFormat DATEDF = new SimpleDateFormat("yyyy-MM-dd");
	private ConcurrentLinkedQueue<ClientOrder> orderList = new ConcurrentLinkedQueue<ClientOrder>();
	private ConcurrentHashMap<String,ClientOrder> orderMap = new ConcurrentHashMap<String,ClientOrder>();
	private ConcurrentHashMap<String,HashSet<String>> clientOrderMap = new ConcurrentHashMap<String,HashSet<String>>();
	private ConcurrentLinkedQueue<ClientCancelOrder> cancelOrderList = new ConcurrentLinkedQueue<ClientCancelOrder>();
	private ConcurrentHashSet<ClientOrder> finishedOrderSet = new ConcurrentHashSet<ClientOrder>();
	private ClientOrderMapper clientOrderMapper;
	
	public static synchronized ClientOrderCache getInstance() {
		if (cache != null) {
			return cache;
		}
		synchronized (ClientOrderCache.class) {
			if (cache == null) {
				cache = new ClientOrderCache();
			}
			return cache;
		}
	}
	
	private ClientOrderCache() {
		clientOrderMapper = DbManager.getInstance().getSession().getMapper(ClientOrderMapper.class);
		refreshFromDb();
	}
	
	public synchronized String generateClientId() {
		return df.format(new Date())+String.format("%07d", (index++));
	}
	
	@Override
	public void flashToDb() {
		for (String orderId : orderMap.keySet()) {
			ClientOrder clientOrder = orderMap.get(orderId);
			if(OrderUtils.isOrderFinished(clientOrder) && finishedOrderSet.contains(clientOrder)){
				logger.info("订单已完成",TextFormat.shortDebugString(clientOrder));
			}else {
				DbClientOrder dbClientOrder = new DbClientOrder();
				dbClientOrder.setOrderId(clientOrder.getClientOrderId());
				dbClientOrder.setClientId(clientOrder.getClientId());
				dbClientOrder.setStockId(clientOrder.getSymbol());
				dbClientOrder.setMarketId(clientOrder.getExchange().getNumber()%2==1 ? DbConstant.MARKET_SH : DbConstant.MARKET_SZ);
				dbClientOrder.setDate(DATEDF.format(new Date()).toString());
				dbClientOrder.setDirection(clientOrder.getDirection().getNumber());
				dbClientOrder.setPrice(clientOrder.getPrice());
				dbClientOrder.setTradePrice(clientOrder.getTradedPrice());
				dbClientOrder.setTotalVolume((int)clientOrder.getTotalVolume());
				dbClientOrder.setTradeVolume((int)clientOrder.getTradedVolume());
				dbClientOrder.setOrderTime(clientOrder.getOrderTime());
				dbClientOrder.setCancelTime(clientOrder.getCancelTime());
				dbClientOrder.setOrderStatus(clientOrder.getOrderStatus().getNumber());
				dbClientOrder.setMessage(clientOrder.getMessage());
				int i = clientOrderMapper.updateClientOrder(dbClientOrder);
				logger.info("Try Update ClientOrder"+TextFormat.shortDebugString(clientOrder));
				if(i == 0){
					clientOrderMapper.insertClientOrder(dbClientOrder);
					logger.info("Update fail! Insert ClientOrder ");
				}
				if(OrderUtils.isOrderFinished(clientOrder) && !finishedOrderSet.contains(clientOrder))
					finishedOrderSet.add(clientOrder);
			}
		}
	}

	@Override
	public void refreshFromDb() {
		List<DbClientOrder> orderList = clientOrderMapper.getAllClientOrders();
		for (DbClientOrder order : orderList) {
			ClientOrder.Builder builder = ClientOrder.newBuilder();
			builder.setSymbol(order.getStockId())
			.setPrice(order.getPrice())
			.setTotalVolume(order.getTotalVolume())
			.setTradedPrice(order.getTradePrice())
			.setTradedVolume(order.getTradeVolume())
			.setMessage(order.getMessage())
			.setDirection(Direction.forNumber(order.getDirection()))
			.setClientOrderId(order.getOrderId())

			.setOrderStatus(OrderStatus.forNumber(order.getOrderStatus()))
			.setExchange((order.getMarketId()).equals("1000")? Exchange.SZ:Exchange.SH)
			.setClientId(order.getClientId());	
			if(order.getOrderTime() != 0) builder.setOrderTime(order.getOrderTime());
			if(order.getCancelTime() != 0) builder.setCancelTime(order.getCancelTime());
			ClientOrder clientOrder = builder.build();
			orderMap.put(clientOrder.getClientOrderId(), clientOrder);
			if(!clientOrderMap.containsKey(clientOrder.getClientId()))
				clientOrderMap.put(clientOrder.getClientId(), new HashSet<String>());
			clientOrderMap.get(clientOrder.getClientId()).add(clientOrder.getClientOrderId());
			readLogger.info(TextFormat.shortDebugString(clientOrder));
		}
	}
	
	public synchronized boolean push(ClientOrder order) {
		if(orderMap.containsKey(order.getClientOrderId()))
			return false;
		orderMap.put(order.getClientOrderId(), order);
		if(!clientOrderMap.containsKey(order.getClientId()))
			clientOrderMap.put(order.getClientId(), new HashSet<String>());
		clientOrderMap.get(order.getClientId()).add(order.getClientOrderId());
		orderList.add(order);
		return true;
	}
	
	public synchronized void update(ClientOrder order) {
		if(!orderMap.containsKey(order.getClientOrderId()))
			push(order);
		else {
			orderMap.put(order.getClientOrderId(), order);
		}
	}
	
	public ClientOrder pop() {
		return orderList.poll();
	}
	
	public ClientOrder getOrder(String clientOrderId) {
		return orderMap.get(clientOrderId);
	}
	
	public Set<String> getClientOrders(String clientId) {
		return clientOrderMap.get(clientId);
	}
	
	public void push(ClientCancelOrder cancelOrder) {
		cancelOrderList.add(cancelOrder);
	}
	
	public ClientCancelOrder popCancelOrder() {
		return cancelOrderList.poll();
	}
	
}
