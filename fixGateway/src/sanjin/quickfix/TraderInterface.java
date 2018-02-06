/*******************************************************************************
 * Copyright (c) quickfixengine.org  All rights reserved.
 *
 * This file is part of the QuickFIX FIX Engine
 *
 * This file may be distributed under the terms of the quickfixengine.org
 * license as defined by quickfixengine.org and appearing in the file
 * LICENSE included in the packaging of this file.
 *
 * This file is provided AS IS with NO WARRANTY OF ANY KIND, INCLUDING
 * THE WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE.
 *
 * See http://www.quickfixengine.org/LICENSE for licensing information.
 *
 * Contact ask@quickfixengine.org if any conditions of this licensing
 * are not clear to you.
 ******************************************************************************/

package sanjin.quickfix;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.comm.Resources;

import quickfix.DefaultMessageFactory;
import quickfix.FileStoreFactory;
import quickfix.Initiator;
import quickfix.LogFactory;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.ScreenLogFactory;
import quickfix.SessionNotFound;
import quickfix.SessionSettings;
import quickfix.SocketInitiator;
import sanjin.quickfix.thread.QueryThread;
import sanjin.quickfix.utils.mina.GatewayServer;

/**
 * Entry point for the Banzai application.
 */
public class TraderInterface {
    private static final CountDownLatch shutdownLatch = new CountDownLatch(1);

    private static final Logger logger = LoggerFactory.getLogger("gateway");

    private static TraderInterface trader = new TraderInterface();
    private Initiator initiator = null;
    private SanjinApplication application =null;
    
    public String userName;
    public String password;
    public String account;
    public String brokerId;
    public String isHkTrade;
    public String gatewayPort;

    public TraderInterface()  {
    	try {
    		//读取配置文件中账户信息
        	Properties props = new Properties();
        	
        	InputStream in = Resources.getResourceAsStream("account.properties");
//        	InputStream in = new BufferedInputStream (new FileInputStream("D:/workspace/fixGateway/src/"));
        	props.load(in);
        	userName = props.getProperty("userName");        	
        	password = props.getProperty("password");
        	account = props.getProperty("account");
        	brokerId = props.getProperty("brokerId");
        	isHkTrade = props.getProperty("isHkTrade");
        	gatewayPort = props.getProperty("gatewayPort");
//    		String path = TraderInterface.class.getClassLoader().getResource("").getPath();
//    		File file = new File(path+"./fix.cfg");
	        InputStream inputStream = Resources.getResourceAsStream("fix.cfg");
	        
	        
	        if (inputStream == null) {
	            System.out.println("usage: " + TraderInterface.class.getName() + " [configFile].");
	            return;
	        }
	        SessionSettings settings = new SessionSettings(inputStream);
	        inputStream.close();
	
	        boolean logHeartbeats = Boolean.valueOf(System.getProperty("logHeartbeats", "true"));
	
	        
	        application = new SanjinApplication();
	        MessageStoreFactory messageStoreFactory = new FileStoreFactory(settings);
	        LogFactory logFactory = new ScreenLogFactory(settings);
	        MessageFactory messageFactory = new DefaultMessageFactory();
	
	        initiator = new SocketInitiator(application, messageStoreFactory, settings, logFactory,
	                messageFactory);
	        initiator.start();
	        
	        
    	} catch(Exception e){
    		e.printStackTrace();
    		logger.error("出问题啦",e);
    		if(initiator != null) {
	    		initiator.stop();
	    		initiator = null;
    		}
    		
    	} 
        

    }
    
    private void reopen()  {
    	try {
//    		String path = TraderInterface.class.getClassLoader().getResource("").getPath();
//    		File file = new File(path+"./fix.cfg");
    		
	        InputStream inputStream = Resources.getResourceAsStream("fix.cfg");
	        
	        if (inputStream == null) {
	            System.out.println("usage: " + TraderInterface.class.getName() + " [configFile].");
	            return;
	        }
	        SessionSettings settings = new SessionSettings(inputStream);
	        inputStream.close();
	
	        boolean logHeartbeats = Boolean.valueOf(System.getProperty("logHeartbeats", "true"));
	
	        
	        application = new SanjinApplication();
	        MessageStoreFactory messageStoreFactory = new FileStoreFactory(settings);
	        LogFactory logFactory = new ScreenLogFactory(settings);
	        MessageFactory messageFactory = new DefaultMessageFactory();
	
	        initiator = new SocketInitiator(application, messageStoreFactory, settings, logFactory,
	                messageFactory);
	        initiator.start();
	        
	        
    	} catch(Exception e) {
    		logger.error("出问题啦",e);
    		e.printStackTrace();
    		if(initiator != null) {
	    		initiator.stop();
	    		initiator = null;
    		}
    	} 
    }
        
    public void login()
    {
		try {
			if(application!=null)
				application.accountLogon(userName, password);
			else
				System.out.println("没有连接服务");
		} catch (SessionNotFound e) {
			e.printStackTrace();
		}
    }
    
    public void doCancel(Order order,String account)
    {
    	application.doCancel(order, account);
    	return;
    }
    
    public String doOrder(Order order,String account)
    {
    	return application.doOrder(order, account);
    	
    }
    
    public void queryPosition(String account){
    	application.queryPosition(account);
    }
    
    public void queryBalance(String account){
    	application.queryBalance(account);
    }

    

    public static TraderInterface get() {
    	if(trader.initiator == null)
    		trader.reopen();
        return trader;
    }
    
    public void close()
    {
    	initiator.stop();
    	initiator = null;
    }
    
    public SanjinApplication getApplication() {
    	return application;
    }
    
    public static void main(String[] args) throws Exception {
    	TraderInterface traderOne = TraderInterface.get();
        Thread.sleep(3000);
        traderOne.login();
        new Thread(new QueryThread(traderOne.get().getApplication())).start();
        GatewayServer.getInstance();
        Thread.sleep(1000);
        
        shutdownLatch.await();
    }

}
