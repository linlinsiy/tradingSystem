package com.sanjin.comm;

import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Settings {
	private static Logger logger = LogManager.getLogger("comm");
	private static Properties props = null;
	public static void loadSettings() {
		try {
			props = new Properties();
			InputStream inputStream = Resources.getResourceAsStream("comm.properties");
			props.load(inputStream);
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("加载comm配置文件不成功",e);
			props = null;
		}
	}
	
	public static String getProperties(String propName) {
		if(props== null)
			return null;
		return props.getProperty(propName);
	}
}
