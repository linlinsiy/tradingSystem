package com.sanjin.business.client.communication;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.google.protobuf.TextFormat;
import com.sanjin.bean.StockPoolClientProtos.SanjinClientMessage;
import com.sanjin.comm.Settings;



public class GuiServer {
	private static Logger logger = LogManager.getLogger("client");
	private static GuiServer cache;
	private ConcurrentHashMap<String,HashSet<IoSession>> clientSessionMap = new ConcurrentHashMap<String,HashSet<IoSession>>();
	private ConcurrentHashMap<IoSession,ArrayList<String>> sessionClientMap = new ConcurrentHashMap<IoSession,ArrayList<String>>();
	private ConcurrentHashMap<String,IoSession> validSessionMap = new ConcurrentHashMap<>();
	
	public static synchronized GuiServer getInstance() {
		if (cache != null) {
			return cache;
		}
		synchronized (GuiServer.class) {
			if (cache == null) {
				cache = new GuiServer();
			}
			return cache;
		}
	}
	
	public synchronized void addSession(String clientId, IoSession session) {
		if(!clientSessionMap.containsKey(clientId))
			clientSessionMap.put(clientId, new HashSet<IoSession>());
		clientSessionMap.get(clientId).add(session);
		if(!sessionClientMap.containsKey(session))
			sessionClientMap.put(session, new ArrayList<String>());
		sessionClientMap.get(session).add(clientId);
	}
	
	public synchronized void addValidSession(String clientId,IoSession session) {
		validSessionMap.put(clientId, session);
	}
	
	public synchronized boolean isSessionValid(IoSession session) {
		return validSessionMap.contains(session);
	}
	
	public synchronized boolean isClientLogon(String clientId) {
		return validSessionMap.containsKey(clientId);
	}
	
	public IoSession getLogonSession(String clientId) {
		return validSessionMap.get(clientId);
	}
	
	public Set<IoSession> getSessionList(String clientId) {
		return clientSessionMap.get(clientId);
	}
	
	public List<String> getClientList(IoSession session) {
		return sessionClientMap.get(session);
	}
	
	public synchronized void removeSession(String clientId, IoSession session) {
		if(sessionClientMap.containsKey(session))
			sessionClientMap.remove(session);
		if(clientSessionMap.containsKey(clientId))
			if(clientSessionMap.get(clientId).contains(session))
				clientSessionMap.get(clientId).remove(session);
		if(validSessionMap.contains(session)) {
			String rmKey = null;
			for(String key:validSessionMap.keySet()) {
				if(validSessionMap.get(key) == session) {
					rmKey = key;
					break;
				}
			}
			if(rmKey!=null)
				validSessionMap.remove(rmKey);
		}
	}
	
	private GuiServer() {
		int port = Integer.parseInt(Settings.getProperties("guiServerPort"));
		
		SocketAcceptor acceptor = new NioSocketAcceptor(); // (1)
		
		acceptor.getFilterChain().addLast( "ProtoBuf_codec", 
				new ProtocolCodecFilter( new GuiProtoBufCodecFactory()));      // (2)
		acceptor.setHandler(new GuiServerHandler()); // (3)
		
		acceptor.getSessionConfig().setReadBufferSize(2048*2048); // (4)
		
		try {
			acceptor.bind(new InetSocketAddress(port)); // (6)
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("绑定端口"+port+"错误!",e);
			throw new RuntimeException("绑定端口错误");
		}
        logger.info("--------------------------------------------------");
        logger.info("Trading System Server Started");
        logger.info("[TradingSys]Listening on port " + port);
        logger.info("--------------------------------------------------");
	}
	
	public String sendMessage(String clientId, SanjinClientMessage message){
		logger.info("准备发送消息："+TextFormat.shortDebugString(message));
		try {
			Set<IoSession> sessionList = getSessionList(clientId);
			
			if(sessionList!=null && sessionList.size()>0) {
				for(IoSession session:sessionList)
					session.write(message);
				logger.info("消息发送成功");
			} else {
				logger.error("消息发送失败");
			}
		}
		catch(Throwable e) {
			logger.error("消息发送失败",e);
		}
		return null;
	}
}
