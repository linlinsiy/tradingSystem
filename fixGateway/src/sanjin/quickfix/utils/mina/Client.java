package sanjin.quickfix.utils.mina;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.sanjin.bean.StockPoolClientProtos.Direction;
import com.sanjin.bean.StockPoolClientProtos.Exchange;
import com.sanjin.bean.StockPoolClientProtos.OrderStatus;
import com.sanjin.bean.StockPoolGatewayProtos;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerCancelOrder;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerOrder;
import com.sanjin.bean.StockPoolGatewayProtos.GTMSGTYPE;
import com.sanjin.bean.StockPoolGatewayProtos.SanjinGTMessage;

import sanjin.quickfix.utils.mina.codec.ProtoBufCodecFactory;

public class Client {
	private static final long CONNECT_TIMEOUT = 30 * 1000L; // 30 秒;
	private static final String HOSTNAME = "127.0.0.1";
	private static final int PORT = 8080;
	
	public static void main(String[] args) {		
		new Client();
	}
	public Client() {
		NioSocketConnector connector = new NioSocketConnector(); // (1)
		connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);
        connector.getSessionConfig().setReadBufferSize(2048*2048);
		connector.getFilterChain().addLast( "ProtoBuf_codec", 
				new ProtocolCodecFilter(new ProtoBufCodecFactory()));
		connector.setHandler(new ClientHandler());
		IoSession session;
		ConnectFuture future = connector.connect(new InetSocketAddress(
				HOSTNAME, PORT));  // (2)
		future.awaitUninterruptibly();
		session = future.getSession();
		
		//client发送消息
		SanjinGTMessage clientMessage = generateCancelMessage();
		send(session, clientMessage);		
        //等待session关闭
        session.getCloseFuture().awaitUninterruptibly();
        //释放connector
        connector.dispose();
	}
	
	public void send(IoSession session, Object message){
		session.write(message);
	}
	
	private SanjinGTMessage generateOrderMessage(){
		StockPoolGatewayProtos.BrokerOrder.Builder brokerOrderBuilder = StockPoolGatewayProtos.BrokerOrder.newBuilder();
		String brokerId = "gateway0001";
		BrokerOrder brokerOrder = brokerOrderBuilder.setSymbol("60001").setExchange(Exchange.SH)
				.setBrokerId(brokerId).setBrokerOrderId("").setDirection(Direction.BUY).setPrice(20)
				.setTotalVolume(500).setTradedVolume(0).setOrderStatus(OrderStatus.STATUS_UNKNOWN)
				.build();
    	StockPoolGatewayProtos.SanjinGTMessage.Builder messageBuilder = StockPoolGatewayProtos.SanjinGTMessage.newBuilder();
    	SanjinGTMessage messageReport = messageBuilder.setMsgType(GTMSGTYPE.Broker_Order).setBrokerOrder(brokerOrder).build();
    	return messageReport;
	}
	
	private SanjinGTMessage generateCancelMessage(){
		BrokerCancelOrder.Builder cancelOrderBuilder = BrokerCancelOrder.newBuilder();
		BrokerCancelOrder cancelOrder = cancelOrderBuilder.setSymbol("60001")
				.setDirection(Direction.BUY).setCancelOrderid("cancel0001")
				.setOrigOrderid("gateway0001").build();
		SanjinGTMessage messageReport = StockPoolGatewayProtos.SanjinGTMessage.newBuilder().setMsgType(GTMSGTYPE.Broker_Cancel_Order).setCancelOrder(cancelOrder).build();
    	return messageReport;
	}
	
}
