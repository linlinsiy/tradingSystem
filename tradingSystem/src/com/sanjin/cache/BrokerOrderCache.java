package com.sanjin.cache;

import static com.sanjin.cache.ClientOrderCache.DATEDF;

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
import com.sanjin.bean.StockPoolClientProtos.ClientOrder;
import com.sanjin.bean.StockPoolClientProtos.Direction;
import com.sanjin.bean.StockPoolClientProtos.Exchange;
import com.sanjin.bean.StockPoolClientProtos.OrderStatus;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerOrder;
import com.sanjin.cache.bean.DbBrokerOrder;
import com.sanjin.cache.bean.DbConstant;
import com.sanjin.cache.db.DbManager;
import com.sanjin.cache.mapper.BrokerOrderMapper;
import com.sanjin.comm.OrderUtils;

public class BrokerOrderCache implements Cache{
	private static Logger loggerTrading = LogManager.getLogger("trading");
	private static Logger loggerDb = LogManager.getLogger("db");	
	private static Logger logger = LogManager.getLogger("dbsave");
	private ConcurrentHashMap<String, BrokerOrder> brokerOrderMap = new ConcurrentHashMap<String, BrokerOrder>();
	private ConcurrentHashMap<String, HashSet<String>> clientBrokerIdMap = new ConcurrentHashMap<String,HashSet<String>>();
	private ConcurrentHashSet<BrokerOrder> finishedOrderSet = new ConcurrentHashSet<BrokerOrder>();
	private BrokerOrderMapper brokerOrderMapper;
	private static BrokerOrderCache cache;
	
	
	public static synchronized BrokerOrderCache getInstance() {
		if (cache != null) {
			return cache;
		}
		synchronized (BrokerOrderCache.class) {
			if (cache == null) {
				cache = new BrokerOrderCache();
			}
			return cache;
		}
	}
	
	private BrokerOrderCache() {
		brokerOrderMapper = DbManager.getInstance().getSession().getMapper(BrokerOrderMapper.class);
		refreshFromDb();
	}
	
	public synchronized void addBrokerOrder(BrokerOrder order) {
		brokerOrderMap.put(order.getBrokerOrderId(), order);
		if(!clientBrokerIdMap.containsKey(order.getClientOrderId()))
			clientBrokerIdMap.put(order.getClientOrderId(), new HashSet<String>());
		clientBrokerIdMap.get(order.getClientOrderId()).add(order.getBrokerOrderId());
		loggerTrading.info("add relationship:"+order.getClientOrderId()+"-"+order.getBrokerOrderId());
	}
	
	public BrokerOrder getBrokerOrder(String brokerOrderId) {
		return brokerOrderMap.get(brokerOrderId);
	}
	
	public Set<String> getClientBrokerIdSet(String clientOrderId) {
		return clientBrokerIdMap.get(clientOrderId);
	}
	
	@Override
	public void flashToDb() {
		//直接update，如果受影响的行为0，就insert。
		for (String orderId : brokerOrderMap.keySet()) {
			BrokerOrder order = brokerOrderMap.get(orderId);
			if(OrderUtils.isOrderFinished(order) && finishedOrderSet.contains(order)){
				logger.info("订单已完成",TextFormat.shortDebugString(order));
			}else{
				DbBrokerOrder dbBroker = new DbBrokerOrder();
				dbBroker.setOrderId(order.getBrokerOrderId());
				dbBroker.setBrokerId(order.getBrokerId());
				dbBroker.setClientOrderId(order.getClientOrderId());
				dbBroker.setStockId(order.getSymbol());
				dbBroker.setMarketId(order.getExchange().getNumber()%2==1 ? DbConstant.MARKET_SH : DbConstant.MARKET_SZ);
				dbBroker.setDate(DATEDF.format(new Date()).toString());
				dbBroker.setDirection(order.getDirection().getNumber());
				dbBroker.setPrice(order.getPrice());
				dbBroker.setTradePrice(order.getTradedPrice());
				dbBroker.setTotalVolume((int)order.getTotalVolume());
				dbBroker.setTradeVolume((int)order.getTradedVolume());
				dbBroker.setOrderTime(order.getOrderTime());
				dbBroker.setCancelTime(order.getCancelTime());
				dbBroker.setOrderStatus(order.getOrderStatus().getNumber());
				dbBroker.setMessage(order.getMessage());
				int i = brokerOrderMapper.updateBrokerOrder(dbBroker);
				logger.info("Try Update BrokerOrder:" +TextFormat.shortDebugString(order));
				logger.info(dbBroker);
				if(i == 0){
					brokerOrderMapper.insertBrokerOrder(dbBroker);
					logger.info("Update fail! Insert BrokerOrder ");
				}
				if(OrderUtils.isOrderFinished(order) && !finishedOrderSet.contains(order))
					finishedOrderSet.add(order);
				
			}
		}
	}

	@Override
	public void refreshFromDb() {
		List<DbBrokerOrder> orderList = brokerOrderMapper.getAllBrokerOrders();
		
		for(DbBrokerOrder order : orderList) {
			BrokerOrder.Builder builder = BrokerOrder.newBuilder();
			builder.setBrokerOrderId(order.getOrderId());
			builder.setBrokerId(order.getBrokerId());
			builder.setSymbol(order.getStockId());
			if(BrokerCache.getInstance().getMap().get(order.getBrokerId()).getIsHkTrade() == 1) {
				if(order.getMarketId().equals(DbConstant.MARKET_SH))
					builder.setExchange(Exchange.HKSH);
				else
					builder.setExchange(Exchange.HKSZ);
			}
			else {
				if(order.getMarketId().equals(DbConstant.MARKET_SH))
					builder.setExchange(Exchange.SH);
				else
					builder.setExchange(Exchange.SZ);
			}
			builder.setPrice(order.getPrice());
			builder.setClientOrderId(order.getClientOrderId());
			builder.setTradedPrice(order.getTradePrice());
			builder.setTotalVolume(order.getTotalVolume());
			builder.setTradedVolume(order.getTradeVolume());
			if(order.getCancelTime() != 0) builder.setCancelTime(order.getCancelTime());
			if(order.getOrderTime() != 0) builder.setOrderTime(order.getOrderTime());
			builder.setMessage(order.getMessage());
			builder.setDirection(Direction.forNumber(order.getDirection()));
			builder.setOrderStatus(OrderStatus.forNumber(order.getOrderStatus()));
			BrokerOrder brokerOrder = builder.build();
			addBrokerOrder(brokerOrder);
			loggerDb.info(TextFormat.shortDebugString(brokerOrder));
		}
	}

}
