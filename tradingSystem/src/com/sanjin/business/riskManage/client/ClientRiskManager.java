package com.sanjin.business.riskManage.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sanjin.bean.StockPoolClientProtos.ClientOrder;
import com.sanjin.cache.UserCache;
import com.sanjin.cache.bean.DbUserInfo;

public class ClientRiskManager{
	private ConcurrentHashMap<String, List<ClientRiskCheck>> riskCheckMap = new ConcurrentHashMap<String, List<ClientRiskCheck>>();
	private static ClientRiskManager cache;
	private static Logger logger = LogManager.getLogger("risk");
	
	public static synchronized ClientRiskManager getInstance() {
		if (cache != null) {
			return cache;
		}
		synchronized (ClientRiskManager.class) {
			if (cache == null) {
				cache = new ClientRiskManager();
			}
			return cache;
		}
	}

	private ClientRiskManager() {
		Collection<DbUserInfo> userInfoList = UserCache.getInstance().getAllUser();
		
		for(DbUserInfo userInfo : userInfoList) {
			List<ClientRiskCheck> riskCheckList = new ArrayList<ClientRiskCheck>();
			
			riskCheckList.add(new ClientPositionCheck());
			riskCheckList.add(new ClientAccountCheck());
			
			riskCheckMap.put(userInfo.getUserId(), riskCheckList);
		}
	}
	
	public String riskCheck(ClientOrder order) {
		List<ClientRiskCheck> riskCheckList = riskCheckMap.get(order.getClientId());
		
		for(ClientRiskCheck riskCheck : riskCheckList) {
			String msg = riskCheck.riskCheck(order);
			
			if(msg != null) {
				return msg;
			}
		}
		
		return null;
	}

}
