package com.sanjin.cache;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.protobuf.TextFormat;
import com.sanjin.bean.StockPoolClientProtos.ClientAccount;
import com.sanjin.cache.bean.DbUserAccount;
import com.sanjin.cache.db.DbManager;
import com.sanjin.cache.mapper.UserAccountMapper;
import com.sanjin.cache.mapper.UserInfoMapper;


public class AccountCache implements Cache{
	private static Logger logger = LogManager.getLogger("dbsave");
	private static Logger readLogger = LogManager.getLogger("db");
	private ConcurrentHashMap<String, ClientAccount> accountMap = new ConcurrentHashMap<String, ClientAccount>();
	
	private UserInfoMapper userInfoMapper;
	private UserAccountMapper userAccountMapper;
	private static AccountCache cache;
	
	
	public static synchronized AccountCache getInstance() {
		if (cache != null) {
			return cache;
		}
		synchronized (AccountCache.class) {
			if (cache == null) {
				cache = new AccountCache();
			}
			return cache;
		}
	}
	
	
	
	public ClientAccount getUserById(String userId) {
		if(accountMap.containsKey(userId))
			return accountMap.get(userId);
		return null;
	}
	
	public synchronized void updateAccount(String clientId, ClientAccount account) {
		accountMap.put(clientId, account);
	}
	
	private AccountCache() {
		userInfoMapper = DbManager.getInstance().getSession().getMapper(UserInfoMapper.class);
		userAccountMapper = DbManager.getInstance().getSession().getMapper(UserAccountMapper.class);
		refreshFromDb();
	}
	
	@Override
	public void flashToDb() {
		for (String clientId : accountMap.keySet()) {
			ClientAccount clientAccount = accountMap.get(clientId);
			DbUserAccount dbUserAccount = new DbUserAccount();
			dbUserAccount.setUserId(clientId);
			dbUserAccount.setFrozenBalance(clientAccount.getFrozenBalance());
			dbUserAccount.setInitBalance(clientAccount.getInitBalance());
			dbUserAccount.setUsableBalance(clientAccount.getUsableBalance());
			int i = userAccountMapper.updateUserAccount(dbUserAccount);
			logger.info("Try Update UserAccount "+TextFormat.shortDebugString(clientAccount));
			if(i == 0){
				userAccountMapper.insertUserAccount(dbUserAccount);
				logger.info("Update fail! Insert ClientOrder ");
			}
		}
		
	}

	@Override
	public void refreshFromDb() {
		List<DbUserAccount> accountList = userAccountMapper.getAllUserAccount();
		for (DbUserAccount dbUserAccount : accountList) {
			ClientAccount.Builder builder = ClientAccount.newBuilder();
			builder.setClientId(dbUserAccount.getUserId());
			builder.setFrozenBalance(dbUserAccount.getFrozenBalance());
			builder.setInitBalance(dbUserAccount.getInitBalance());
			builder.setUsableBalance(dbUserAccount.getUsableBalance());
			ClientAccount clientAccount = builder.build();
			accountMap.put(clientAccount.getClientId(), clientAccount);
			readLogger.info(TextFormat.shortDebugString(clientAccount));
		}
		
	}



	public ClientAccount getUserAccount(String clientId) {
		return accountMap.get(clientId);
	}


}
