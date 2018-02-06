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

package quickfix.examples.banzai;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.quickfixj.jmx.JmxExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.DefaultMessageFactory;
import quickfix.FileStoreFactory;
import quickfix.Initiator;
import quickfix.LogFactory;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.ScreenLogFactory;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.SessionSettings;
import quickfix.SocketInitiator;

/**
 * Entry point for the Banzai application.
 */
public class TraderInterface {
    private static final CountDownLatch shutdownLatch = new CountDownLatch(1);

    private static final Logger log = LoggerFactory.getLogger(TraderInterface.class);
    private static TraderInterface banzai = new TraderInterface();
    private Initiator initiator = null;
    private JFrame frame = null;
    SanjinApplication application =null;

    public TraderInterface()  {
    	try {
	        InputStream inputStream = TraderInterface.class.getResourceAsStream("fix.cfg");
	        
	        if (inputStream == null) {
	            System.out.println("usage: " + TraderInterface.class.getName() + " [configFile].");
	            return;
	        }
	        SessionSettings settings = new SessionSettings(inputStream);
	        inputStream.close();
	
	        boolean logHeartbeats = Boolean.valueOf(System.getProperty("logHeartbeats", "true"));
	
	        
	        application = new SanjinApplication();
	        MessageStoreFactory messageStoreFactory = new FileStoreFactory(settings);
	        LogFactory logFactory = new ScreenLogFactory(true, true, true, logHeartbeats);
	        MessageFactory messageFactory = new DefaultMessageFactory();
	
	        initiator = new SocketInitiator(application, messageStoreFactory, settings, logFactory,
	                messageFactory);
	        initiator.start();
	        JmxExporter exporter = new JmxExporter();
	        exporter.register(initiator);
	        
    	} catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        

    }
    
    public void login(String account,String pwd)
    {
		try {
			if(application!=null)
				application.accountLogon(account, pwd);
			else
				System.out.println("没有连接服务器");
		} catch (SessionNotFound e) {
			e.printStackTrace();
		}
    }
    
    public void doCancel(Order order,String account)
    {
    	application.doCancel(order, account);
    	return;
    }
    
    public void doOrder(Order order,String account)
    {
    	application.doOrder(order, account);
    	return;
    }


    

    public static TraderInterface get() {
        return banzai;
    }

    public static void main(String[] args) throws Exception {
        
    	TraderInterface banzai1 = TraderInterface.get();
        
        Thread.sleep(3000);
        banzai1.login("sanjinuat","sanjin123");
        Thread.sleep(1000);
        Order order = new Order();
        order.setSymbol("600000");
        order.setQuantity(200);
        order.setLimit(13.3);
        order.setSide(OrderSide.BUY);
        order.setType(OrderType.LIMIT);
        banzai1.doOrder(order, "0326PT0001");
        shutdownLatch.await();
    }

}
