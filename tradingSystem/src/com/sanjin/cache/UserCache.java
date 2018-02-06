package com.sanjin.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.sanjin.cache.bean.DbUserInfo;
import com.sanjin.cache.db.DbManager;
import com.sanjin.cache.mapper.UserInfoMapper;


public class UserCache implements Cache{
	private ConcurrentHashMap<String, DbUserInfo> userIdMap = new ConcurrentHashMap<String, DbUserInfo>();
	private ConcurrentHashMap<String, DbUserInfo> userNameMap = new ConcurrentHashMap<String, DbUserInfo>();
	private ConcurrentHashMap<String, ArrayList<String>> userParentMap = new ConcurrentHashMap<String, ArrayList<String>>();
	private ConcurrentHashMap<String, ArrayList<String>> userChildMap = new ConcurrentHashMap<String, ArrayList<String>>();
	private UserInfoMapper userInfoMapper;
	private static UserCache cache;
	
	
	public static synchronized UserCache getInstance() {
		if (cache != null) {
			return cache;
		}
		synchronized (UserCache.class) {
			if (cache == null) {
				cache = new UserCache();
			}
			return cache;
		}
	}
	
	public Collection<DbUserInfo> getAllUser() {
		return userIdMap.values();
	}
	
	public DbUserInfo getUserByName(String username) {
		if(userNameMap.containsKey(username))
			return userNameMap.get(username);
		return null;
	}
	
	public DbUserInfo getUserById(String userId) {
		System.out.println(userIdMap.containsKey(userId));
		if(userIdMap.containsKey(userId))
			return userIdMap.get(userId);
		return null;
	}
	
	public List<String> getParentList(String clientId) {
		return userParentMap.get(clientId);
	}
	
	public List<String> getChildList(String clientId) {
		return userChildMap.get(clientId);
	}
	
	private UserCache() {
		userInfoMapper = DbManager.getInstance().getSession().getMapper(UserInfoMapper.class);
		refreshFromDb();
	}
	
	@Override
	public void flashToDb() {
	}

	@Override
	public void refreshFromDb() {
		List<DbUserInfo> userList = userInfoMapper.getAllUsers();
		
		for(DbUserInfo user:userList) {
			if(user.getDisabled()==0) {
				userIdMap.put(user.getUserId(), user);
				userNameMap.put(user.getUserName(), user);
				String[] parentids = user.getUserId().split("-");
				String parentid = "";
				userParentMap.put(user.getUserId(), new ArrayList<String>());
				for(String pid:parentids) {
					parentid += "-"+pid;
					if(parentid.substring(1).equals(user.getUserId()))
						break;
					userParentMap.get(user.getUserId()).add(parentid.substring(1));
				}
				System.out.println(user.getUserId()+":"+user.getPassword());
			}
			
		}
		for(String userid:userParentMap.keySet()) {
			List<String> parentidList = userParentMap.get(userid);
			for(String pid:parentidList) {
				if(!userChildMap.containsKey(pid))
					userChildMap.put(pid, new ArrayList<String>());
				if(!userChildMap.get(pid).contains(userid))
					userChildMap.get(pid).add(userid);
			}
		}
	}

}
