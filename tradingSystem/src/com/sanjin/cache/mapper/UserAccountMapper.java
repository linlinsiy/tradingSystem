package com.sanjin.cache.mapper;

import java.util.List;

import com.sanjin.cache.bean.DbUserAccount;

public interface UserAccountMapper {
	List<DbUserAccount> getAllUserAccount();
	DbUserAccount getUserAccountById(String userId);
	int updateUserAccount(DbUserAccount dbUserAccount);
	int insertUserAccount(DbUserAccount dbUserAccount);
}
