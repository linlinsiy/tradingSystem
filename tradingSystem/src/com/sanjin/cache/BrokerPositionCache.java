package com.sanjin.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.protobuf.TextFormat;
import com.sanjin.bean.StockPoolClientProtos.ClientOrder;
import com.sanjin.bean.StockPoolClientProtos.ClientPosition;
import com.sanjin.bean.StockPoolClientProtos.Direction;
import com.sanjin.bean.StockPoolClientProtos.Exchange;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerPosition;
import com.sanjin.business.gateway.GatewayManager;
import com.sanjin.business.riskManage.broker.BrokerRiskManager;
import com.sanjin.business.riskManage.client.ClientRiskManager;
import com.sanjin.cache.bean.DbBrokerPosition;
import com.sanjin.cache.bean.DbConstant;
import com.sanjin.cache.db.DbManager;
import com.sanjin.cache.mapper.BrokerPositionMapper;

public class BrokerPositionCache implements Cache{
	private static Logger logger = LogManager.getLogger("trading");
	private static Logger readLogger = LogManager.getLogger("db");
	private static Logger dbLogger = LogManager.getLogger("dbsave");
	private ConcurrentHashMap<String, ConcurrentHashMap<String,BrokerPosition>> brokerSymbolMap = new ConcurrentHashMap<String, ConcurrentHashMap<String,BrokerPosition>>();
	private ConcurrentHashMap<String, ConcurrentHashMap<String,BrokerPosition>> symbolBrokerMap = new ConcurrentHashMap<String, ConcurrentHashMap<String,BrokerPosition>>();
	private ConcurrentHashMap<String, ConcurrentHashMap<String,DbBrokerPosition>> dbBrokerSymbolMap = new ConcurrentHashMap<String, ConcurrentHashMap<String,DbBrokerPosition>>();
	private static BrokerPositionCache cache;
	private BrokerPositionMapper brokerPositionMapper;
	
	public static synchronized BrokerPositionCache getInstance() {
		if (cache != null) {
			return cache;
		}
		synchronized (BrokerPositionCache.class) {
			if (cache == null) {
				cache = new BrokerPositionCache();
			}
			return cache;
		}
	}
	
	public BrokerPositionCache() {
		brokerPositionMapper = DbManager.getInstance().getSession().getMapper(BrokerPositionMapper.class);
		refreshFromDb();
	}



	public synchronized void putPosition(BrokerPosition pos) {
		if(!brokerSymbolMap.containsKey(pos.getBrokerId()))
			brokerSymbolMap.put(pos.getBrokerId(), new ConcurrentHashMap<String,BrokerPosition>());
		brokerSymbolMap.get(pos.getBrokerId()).put(pos.getSymbol(), pos);
		if(!symbolBrokerMap.containsKey(pos.getSymbol()))
			symbolBrokerMap.put(pos.getSymbol(), new ConcurrentHashMap<String,BrokerPosition>());
		symbolBrokerMap.get(pos.getSymbol()).put(pos.getBrokerId(), pos);
	}
	
	public synchronized BrokerPosition getPosition(String brokerId,String symbol) {
		if(!brokerSymbolMap.containsKey(brokerId))
			return null;
		return brokerSymbolMap.get(brokerId).get(symbol);
				
	}
	
	public synchronized Collection<BrokerPosition> getPosition(String symbol) {
		if(!symbolBrokerMap.containsKey(symbol))
			return null;
		return symbolBrokerMap.get(symbol).values();
	}
	
	public synchronized Map<String, Long> getAvailableMap(ClientOrder clientOrder) {
		//客户端风控校验
		if(ClientRiskManager.getInstance().riskCheck(clientOrder) != null)
			return null;
		if(!symbolBrokerMap.containsKey(clientOrder.getSymbol()))
			return null;
		ConcurrentHashMap<String,BrokerPosition> brokerPosMap = symbolBrokerMap.get(clientOrder.getSymbol());
		Map<String, Long> avlMap = new HashMap<String, Long>();
		long orderQty = clientOrder.getTotalVolume();
		
		//检查客户持仓
		List<ClientPosition> clientPosList = ClientPositionCache.getInstance().getPosition(clientOrder.getClientId(), clientOrder.getSymbol());
		long hkQty = 0;
		for(ClientPosition clientPos : clientPosList){
			if(clientPos.getExchange() == Exchange.HKSH || clientPos.getExchange() == Exchange.HKSZ){
				if(clientOrder.getDirection() == Direction.BUY)
					hkQty += clientPos.getPosition() - clientPos.getPosLong() - clientPos.getPosLongPending();
				else if(clientOrder.getDirection() == Direction.SELL)
					hkQty += clientPos.getPosition() - clientPos.getPosShort() - clientPos.getPosShortPending();
			}
		}
		
		for(BrokerPosition brokerPos : brokerPosMap.values()) {
			if(GatewayManager.getInstance().getClient(brokerPos.getBrokerId()).isConnected()) {
				long avlQty = 0;
				
				//检查券商可用余券
				if(clientOrder.getDirection() == Direction.BUY)
					avlQty = brokerPos.getPosition() - brokerPos.getPosLong() - brokerPos.getPosLongPending();
				else if(clientOrder.getDirection() == Direction.SELL)
					avlQty = brokerPos.getPosition() - brokerPos.getPosShort() - brokerPos.getPosShortPending();
				
				//沪港通校验
				if(BrokerCache.getInstance().isHkTrade(brokerPos.getBrokerId()))
					avlQty = Math.min(avlQty, hkQty);
				
				//风控校验
				String riskMsg = BrokerRiskManager.getInstance().riskCheck(brokerPos.getBrokerId(), clientOrder.getSymbol(), clientOrder.getDirection(), avlQty, clientOrder.getPrice());
				if(riskMsg != null)
					avlQty = 0;
				
				if(avlQty == 0)
					continue;
				else if(avlQty >= orderQty) {
					logger.info("订单"+clientOrder.getClientOrderId()+"获取下单分配"+brokerPos.getBrokerId()+":"+orderQty);
					avlMap.put(brokerPos.getBrokerId(), orderQty);
					orderQty = 0;
					break;
				}
				else{
					logger.info("订单"+clientOrder.getClientOrderId()+"获取下单分配"+brokerPos.getBrokerId()+":"+avlQty);
					avlMap.put(brokerPos.getBrokerId(), avlQty);
					orderQty -= avlQty;
				}
			}
			
		}
		
		if(orderQty == 0){
			logger.info("订单"+clientOrder.getClientOrderId()+"分配成功");
			return avlMap;
		}
		else{
			logger.error("订单"+clientOrder.getClientOrderId()+"分配失败,因可用额度不满足");
			return null;
		}
	}
	
	@Override
	public void flashToDb() {
		for(String userId : brokerSymbolMap.keySet()){
			ConcurrentHashMap<String,BrokerPosition> innermap = brokerSymbolMap.get(userId);
			for (String innerkey : innermap.keySet()) {
				BrokerPosition bpos = innermap.get(innerkey);
				DbBrokerPosition pos = new DbBrokerPosition();
				pos.setMarketId(bpos.getExchange().getNumber()%2==1 ? DbConstant.MARKET_SH : DbConstant.MARKET_SZ);
				pos.setUserId(bpos.getBrokerId());
				pos.setStockId(bpos.getSymbol());
				if(bpos.hasPosLong())
					pos.setBuyQty((int)bpos.getPosLong());
				else
					pos.setBuyQty(0);
				if(bpos.hasPosShort())
					pos.setSellQty((int)bpos.getPosShort());
				else
					pos.setSellQty(0);
				if(bpos.hasPosLongPending())
					pos.setBuyOrderQty((int)bpos.getPosLongPending());
				else
					pos.setBuyOrderQty(0);
				if(bpos.hasPosShortPending())
					pos.setSellOrderQty((int)bpos.getPosShortPending());
				else
					pos.setSellOrderQty(0);
				if(bpos.hasLeavesQty())
					pos.setRealQty((int)bpos.getLeavesQty());
				else
					pos.setRealQty(0);
				dbLogger.info(TextFormat.shortDebugString(bpos));
				if(brokerPositionMapper.updateBrokerPosition(pos) == 0){
					brokerPositionMapper.insertBrokerPosition(pos);
					dbLogger.warn("Insert BrokerPosition!");
				}
			}
		}
	}
	public void refreshPosFromDb(String brokerId, String symbol){
		DbBrokerPosition pos = brokerPositionMapper.getBrokerPositionById(brokerId, symbol);
		
		ConcurrentHashMap<String,BrokerPosition> innermap = brokerSymbolMap.get(pos.getUserId());
		StringBuilder innerkeySB = new StringBuilder(pos.getStockId());
		if(BrokerCache.getInstance().getMap().get(pos.getUserId()).getIsHkTrade() == 1) {
			if(pos.getMarketId().equals(DbConstant.MARKET_SH))
				innerkeySB.append(Exchange.HKSH.getNumber());
			else
				innerkeySB.append(Exchange.HKSZ.getNumber());
		} else {
			if(pos.getMarketId().equals(DbConstant.MARKET_SH))
				innerkeySB.append(Exchange.SH.getNumber());
			else
				innerkeySB.append(Exchange.SZ.getNumber());
		}
		String innerkey = innerkeySB.toString();
		BrokerPosition.Builder builder= innermap.get(innerkey).toBuilder();
		builder.setPosition(pos.getFormerQty()+pos.getDeltaQty()+pos.getOpenQty()+pos.getSendQty()-pos.getRecallQty());
		BrokerPosition brokerPosition = builder.build();
		putPosition(brokerPosition);
		readLogger.info(TextFormat.shortDebugString(brokerPosition));
	}

	@Override
	public void refreshFromDb() {
		List<DbBrokerPosition> brokerPosList = brokerPositionMapper.getAllBrokerPositions();
		for (DbBrokerPosition pos : brokerPosList) {
			BrokerPosition.Builder builder = BrokerPosition.newBuilder();
			builder.setBrokerId(pos.getUserId())
			.setSymbol(pos.getStockId())
			.setPosition(pos.getFormerQty()+pos.getDeltaQty()+pos.getOpenQty()+pos.getSendQty()-pos.getRecallQty())
			.setPosLong(pos.getBuyQty())
			.setPosShort(pos.getSellQty())
			.setPosLongPending(pos.getBuyOrderQty())
			.setPosShortPending(pos.getSellOrderQty());
			if(BrokerCache.getInstance().getMap().get(pos.getUserId()).getIsHkTrade() == 1) {
				if(pos.getMarketId().equals(DbConstant.MARKET_SH))
					builder.setExchange(Exchange.HKSH);
				else
					builder.setExchange(Exchange.HKSZ);
			} else {
				if(pos.getMarketId().equals(DbConstant.MARKET_SH))
					builder.setExchange(Exchange.SH);
				else
					builder.setExchange(Exchange.SZ);
			}
			BrokerPosition bpos = builder.build();
			putPosition(bpos);
			readLogger.info(TextFormat.shortDebugString(bpos));
		}
	}

}
