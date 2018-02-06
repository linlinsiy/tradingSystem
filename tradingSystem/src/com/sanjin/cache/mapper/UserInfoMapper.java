package com.sanjin.cache.mapper;

import java.util.List;

import com.sanjin.cache.bean.DbBroker;
import com.sanjin.cache.bean.DbUserInfo;

public interface UserInfoMapper {
	List<DbUserInfo> getAllUsers();
	DbUserInfo getUserById(String userId);
	List<DbUserInfo> getUsersByCondition(DbUserInfo userInfo);
}
