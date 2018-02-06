package com.sanjin.cache;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.protobuf.TextFormat;
import com.sanjin.bean.StockPoolClientProtos.ClientPosition;
import com.sanjin.bean.StockPoolClientProtos.Exchange;
import com.sanjin.bean.StockPoolClientProtos.PositionStatus;
import com.sanjin.cache.bean.DbClientPosition;
import com.sanjin.cache.bean.DbConstant;
import com.sanjin.cache.db.DbManager;
import com.sanjin.cache.mapper.ClientPositionMapper;

public class ClientPositionCache implements Cache{
	private static Logger logger = LogManager.getLogger("db");
	private static Logger dbLogger = LogManager.getLogger("dbsave");
	private ConcurrentHashMap<String, ConcurrentHashMap<String,ClientPosition>> clientPosMap = new ConcurrentHashMap<String, ConcurrentHashMap<String,ClientPosition>>();
	
	private ClientPositionMapper clientPositionMapper;
	private static ClientPositionCache cache;
	
	private ClientPositionCache() {
		clientPositionMapper = DbManager.getInstance().getSession().getMapper(ClientPositionMapper.class);
		refreshFromDb();
	}
	
	
	public static synchronized ClientPositionCache getInstance() {
		if (cache != null) {
			return cache;
		}
		synchronized (ClientPositionCache.class) {
			if (cache == null) {
				cache = new ClientPositionCache();
			}
			return cache;
		}
	}
	
	public ClientPosition getPosition(String clientId,String symbol,Exchange exchange) {
		if(!clientPosMap.containsKey(clientId))
			return null;
		if(!clientPosMap.get(clientId).containsKey(symbol+exchange.getNumber()))
			return null;
		return clientPosMap.get(clientId).get(symbol+exchange.getNumber());
	}
	
	public List<ClientPosition> getPosition(String clientId,String symbol) {
		if(!clientPosMap.containsKey(clientId))
			return null;
		ArrayList<ClientPosition> arr = new ArrayList<ClientPosition>();
		if(symbol.startsWith("6") || symbol.startsWith("5")) {
			if(clientPosMap.get(clientId).containsKey(symbol+Exchange.SH.getNumber()))
				arr.add(clientPosMap.get(clientId).get(symbol+Exchange.SH.getNumber()));
			if(clientPosMap.get(clientId).containsKey(symbol+Exchange.HKSH.getNumber()))
				arr.add(clientPosMap.get(clientId).get(symbol+Exchange.HKSH.getNumber()));
				
		} else {
			if(clientPosMap.get(clientId).containsKey(symbol+Exchange.SZ.getNumber()))
				arr.add(clientPosMap.get(clientId).get(symbol+Exchange.SZ.getNumber()));
			if(clientPosMap.get(clientId).containsKey(symbol+Exchange.HKSZ.getNumber()))
				arr.add(clientPosMap.get(clientId).get(symbol+Exchange.HKSZ.getNumber()));
		}
		return arr;
		
		
	}
	
	public synchronized boolean updatePosition(String clientId,String symbol,Exchange exchange,ClientPosition pos) {
		if(!clientPosMap.containsKey(clientId))
			return false;
		if(!clientPosMap.get(clientId).containsKey(symbol+exchange.getNumber()))
			return false;
		clientPosMap.get(clientId).put(symbol+exchange.getNumber(), pos);
		return true;
	}
	
	public ClientPosition getSummaryPosition(String clientId, String symbol) {
		ClientPosition clientPos = getPositionSum(clientId, symbol);
		List<String> childList = UserCache.getInstance().getChildList(clientId);
		
		if(childList != null && childList.size() > 0) {
			ClientPosition.Builder builder = ClientPosition.newBuilder();
			builder.setClientId(clientId);
			builder.setSymbol(symbol);
			builder.setPositionStatus(clientPos.getPositionStatus());
			builder.setPosition(clientPos.getPosition());
			builder.setPosLong(clientPos.getPosLong());
			builder.setPosShort(clientPos.getPosShort());
			builder.setPosLongPending(clientPos.getPosLongPending());
			builder.setPosShortPending(clientPos.getPosShortPending());
			
			for(String childId : childList) {
				ClientPosition childPos = getPositionSum(childId, symbol);
				
				if(childPos != null) {
					builder.setPosition(builder.getPosition() + childPos.getPosition());
					builder.setPosLong(builder.getPosLong() + childPos.getPosLong());
					builder.setPosShort(builder.getPosShort() + childPos.getPosShort());
					builder.setPosLongPending(builder.getPosLongPending() + childPos.getPosLongPending());
					builder.setPosShortPending(builder.getPosShortPending() + childPos.getPosShortPending());
				}
			}
			
			return builder.build();
		}
		
		return null;
	}
	
	public ClientPosition getPositionSum(String clientId,String symbol) {
		List<ClientPosition> posList = getPosition(clientId, symbol);
		
		if(posList!=null && posList.size()>0) {
			ClientPosition.Builder builder = ClientPosition.newBuilder();
			builder.setClientId(clientId);
			builder.setSymbol(symbol);
			for(ClientPosition pos:posList) {
				if(!builder.hasExchange()) {
					builder.setExchange(pos.getExchange());
					if(pos.getExchange() == Exchange.HKSH)
						builder.setExchange(Exchange.SH);
					else if(pos.getExchange() == Exchange.HKSZ)
						builder.setExchange(Exchange.SZ);
				}
				if(!builder.hasPositionStatus())
					builder.setPositionStatus(pos.getPositionStatus());
				
				if(!builder.hasPosition())
					builder.setPosition(pos.getPosition());
				else
					builder.setPosition(builder.getPosition()+pos.getPosition());
				
				if(!builder.hasPosLong())
					builder.setPosLong(pos.getPosLong());
				else
					builder.setPosLong(builder.getPosLong()+pos.getPosLong());
				if(!builder.hasPosShort())
					builder.setPosShort(pos.getPosShort());
				else
					builder.setPosShort(builder.getPosShort()+pos.getPosShort());
				
				if(!builder.hasPosLongPending())
					builder.setPosLongPending(pos.getPosLongPending());
				else
					builder.setPosLongPending(builder.getPosLongPending()+pos.getPosLongPending());
				if(!builder.hasPosShortPending())
					builder.setPosShortPending(pos.getPosShortPending());
				else
					builder.setPosShortPending(builder.getPosShortPending()+pos.getPosShortPending());
			}
			return builder.build();
		}
		return null;
	}
	
	public Set<String> getPositionSymbol(String clientId) {
		if(!clientPosMap.containsKey(clientId))
			return null;
		HashSet<String> symbolSet = new HashSet<String>();
		for(String key:clientPosMap.get(clientId).keySet()) {
			symbolSet.add(key.substring(0, key.length()-1));
		}
		return symbolSet;
	}
	
	
	
	@Override
	public void flashToDb() {
		for(String userId : clientPosMap.keySet()){
			ConcurrentHashMap<String,ClientPosition> innermap = clientPosMap.get(userId);
			for (String innerkey : innermap.keySet()) {
				ClientPosition cpos = innermap.get(innerkey);
				DbClientPosition pos = new DbClientPosition();
				pos.setUserId(cpos.getClientId());
				pos.setStockId(cpos.getSymbol());
				pos.setMarketId(cpos.getExchange().getNumber()%2==1 ? DbConstant.MARKET_SH : DbConstant.MARKET_SZ);
				pos.setBuyQty((int)cpos.getPosLong());
				pos.setSellQty((int)cpos.getPosShort());
				pos.setBuyOrderQty((int)cpos.getPosLongPending());
				pos.setSellOrderQty((int)cpos.getPosShortPending());
				pos.setHkConnect((4-cpos.getExchange().getNumber())/2);
				pos.setBuyAmount(cpos.getLongMarket());
				pos.setSellAmount(cpos.getShortMarket());
				dbLogger.info(TextFormat.shortDebugString(cpos));
				clientPositionMapper.updateClientPosition(pos);					
			}
		}
	}
	
	public void refreshPosFromDb(String symbol){
		DbClientPosition dbClientPosition = new DbClientPosition();
		dbClientPosition.setStockId(symbol);
		List<DbClientPosition> clientPosList = clientPositionMapper.getClientPositionByCondition(dbClientPosition);
		for (DbClientPosition pos : clientPosList) {
			if(pos.getDisabled() == 0) {
				if(!clientPosMap.containsKey(pos.getUserId()))
					clientPosMap.put(pos.getUserId(), new ConcurrentHashMap<String,ClientPosition>());
				ConcurrentHashMap<String,ClientPosition> innermap = clientPosMap.get(pos.getUserId());
				StringBuilder innerkeySB = new StringBuilder(pos.getStockId());
				if(pos.getHkConnect() == 1) {
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
				ClientPosition.Builder builder = innermap.get(innerkey).toBuilder();
				builder.setPosition(pos.getFormerQty()+pos.getDeltaQty()+pos.getOpenQty()+pos.getSendQty()-pos.getRecallQty());
				ClientPosition clientPosition = builder.build();
				innermap.put(innerkey, clientPosition);
				logger.info(TextFormat.shortDebugString(clientPosition));
			}
		}
	}

	@Override
	public synchronized void refreshFromDb() {
		
		List<DbClientPosition> clientPosList = clientPositionMapper.getAllClientPositions();
		for(DbClientPosition pos:clientPosList) {
			if(pos.getDisabled() == 0) {
				ClientPosition.Builder builder = ClientPosition.newBuilder();
				builder.setClientId(pos.getUserId());
				builder.setSymbol(pos.getStockId());
				if(pos.getHkConnect() == 1) {
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
				builder.setPosition(pos.getFormerQty()+pos.getDeltaQty()+pos.getOpenQty()+pos.getSendQty()-pos.getRecallQty());
				builder.setPositionStatus(PositionStatus.NORMAL);
				builder.setPosLong(pos.getBuyQty());
				builder.setPosShort(pos.getSellQty());
				builder.setLongMarket(pos.getBuyAmount());
				builder.setShortMarket(pos.getSellAmount());
				ClientPosition cpos = builder.build();
				if(!clientPosMap.containsKey(pos.getUserId()))
					clientPosMap.put(pos.getUserId(), new ConcurrentHashMap<String,ClientPosition>());
				ConcurrentHashMap<String,ClientPosition> innermap = clientPosMap.get(pos.getUserId());
				String innerkey = cpos.getSymbol()+cpos.getExchange().getNumber();
				innermap.put(innerkey,cpos);
				logger.info(TextFormat.shortDebugString(cpos));
			}
		}
	}
	
	
	public static void main(String[] args) {
		ClientPosition.Builder builder = ClientPosition.newBuilder();
		builder.setClientId("1233241").setSymbol("600000").setExchange(Exchange.HKSH).setPosition(1234).setPositionStatus(PositionStatus.ALL_FORBIDDEN);
		
		ClientPosition pos = builder.build();
		builder.setClientId("asdfsadf");
		ClientPosition pos1 = builder.build();
		System.out.println(pos == pos1);
		System.out.println(pos);
		System.out.println(pos1);
//		System.out.println(pos.toBuilder().setClientId("asfdsaf").build());
//		System.out.println(pos);
//		BigDecimal a = new BigDecimal(3.23);
//		System.out.println(a);
//		System.out.println(a=a.multiply(new BigDecimal(1.05)).setScale(2, BigDecimal.ROUND_HALF_UP));
//		System.out.println(a);
	}
}
