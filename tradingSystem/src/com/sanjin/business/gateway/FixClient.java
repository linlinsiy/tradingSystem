package com.sanjin.business.gateway;

import java.net.InetSocketAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.sanjin.bean.StockPoolGatewayProtos.SanjinGTMessage;

public class FixClient {
	private static Logger logger = LogManager.getLogger("gateway");  
	private static final long CONNECT_TIMEOUT = 30 * 1000L; // 30 秒;
	public static DateFormat df = new SimpleDateFormat("HHmmss");
	private String hostname = "172.31.11.72";
	private int port = 8080;
	private NioSocketConnector connector = null;
	private ConnectFuture connectFu = null;
	private CloseFuture closeFu = null;
    private IoSession ioSession = null;
    private static int clientIdIndex = 0;
	
	public static void main(String[] args) {		
		FixClient client = new FixClient();
		client.create();
//		client.close();
	}
	
	public static synchronized String generateID(String clientId) {
		return clientId+df.format(new Date())+(clientIdIndex++);
	}
	
	
	private FixClient() {
	}


	public FixClient(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}

	public void create() {
		try {
            if(isConnected()) {
                return ;
            }
			connector = new NioSocketConnector(); // (1)
			connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);
	        connector.getSessionConfig().setReadBufferSize(2048*2048);
			connector.getFilterChain().addLast( "ProtoBuf_codec", 
					new ProtocolCodecFilter(new ProtoBufCodecFactory()));
			connector.setHandler(new FixClientHandler(this));
			connectFu = connector.connect(new InetSocketAddress(
					hostname, port));  // (2)
			boolean completed = connectFu.awaitUninterruptibly(CONNECT_TIMEOUT);
			if (!completed) {
                throw new RuntimeException("无法连接至server");
            }
            if (connectFu.isDone()) {
                // 若在指定时间内没连接成功，则抛出异常
                if (!connectFu.isConnected()) {
                    // 不关闭的话会运行一段时间后抛出，too many open files异常，导致无法连接
                	connectFu =null;
                    connector.dispose();
                    connector=null;
                    throw new Exception();
                }
            }
            ioSession = connectFu.awaitUninterruptibly().getSession();
//          ioSession.getConfig().setUseReadOperation(true);
			ioSession.getConfig().setMinReadBufferSize(2048);
			closeFu = connectFu.getSession().getCloseFuture();
			logger.info("mina连接建立成功,"+this.hostname+":"+this.port);

		} catch(Exception e) {
			e.printStackTrace();
			logger.error("初始化mina连接错误", e);
		}
	}
	
	public IoSession getSession() {
		return ioSession;
	}
	
	public void sendMessage(SanjinGTMessage msg) {
		logger.info(msg);
		ioSession.write(msg);
	}
	
    public boolean isConnected() {
        return ioSession != null && ioSession.isConnected();
    }
    
    public void close() {
        if (connectFu != null) {
            try {
                if (connectFu.getSession().isConnected()) {
                    closeFu.getSession().close(true);
                }

                closeFu.awaitUninterruptibly(100);
                connector.dispose();

            } catch (Exception e) {
            } finally {
                connector = null;
            }
        }
    }
    
    public void reconnect() {
        logger.info("开始重连");
        int index = 1;
        while (!isConnected()) {
        	logger.info("尝试第" + (index++) + "次重连");
            synchronized (this) {
                if (!isConnected()) {
                	close();
                	create();
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
        }
    }
    
    
}
