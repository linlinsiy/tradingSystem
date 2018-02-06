package com.sanjin.cache.db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DbManager {
	private static Logger logger = LogManager.getLogger("db");
	private static String mybatisResource = "mybatis-config.xml";
	private static DbManager cache;
	private SqlSession session = null;
	
	public static synchronized DbManager getInstance() {
		if (cache != null) {
			return cache;
		}
		synchronized (DbManager.class) {
			if (cache == null) {
				cache = new DbManager();
			}
			return cache;
		}
	}
	
	private DbManager() {
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(mybatisResource);
			SqlSessionFactoryBuilder facBuilder = new SqlSessionFactoryBuilder();
			SqlSessionFactory sqlSessionFactory = facBuilder.build(inputStream);
			session = sqlSessionFactory.openSession();
			
			logger.info("连接数据库成功");
		} catch (IOException e) {
			logger.info("连接数据库失败");
			logger.error("error",e);
			session = null;
			e.printStackTrace();
		}
	}
	
	public void reconnect() {
		if(session !=null)
			session.close();
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(mybatisResource);
			SqlSessionFactoryBuilder facBuilder = new SqlSessionFactoryBuilder();
			SqlSessionFactory sqlSessionFactory = facBuilder.build(inputStream);
			session = sqlSessionFactory.openSession();
			logger.info("连接数据库成功");
		} catch (IOException e) {
			logger.info("连接数据库失败");
			logger.error("error",e);
			session = null;
			e.printStackTrace();
		}
	}
	
	public SqlSession getSession() {
		return session;
	}
}
