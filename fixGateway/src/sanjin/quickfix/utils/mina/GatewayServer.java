package sanjin.quickfix.utils.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.google.protobuf.util.Timestamps;
import com.sanjin.bean.StockPoolClientProtos.Direction;
import com.sanjin.bean.StockPoolClientProtos.Exchange;
import com.sanjin.bean.StockPoolClientProtos.OrderStatus;
import com.sanjin.bean.StockPoolGatewayProtos;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerOrder;
import com.sanjin.bean.StockPoolGatewayProtos.GTMSGTYPE;
import com.sanjin.bean.StockPoolGatewayProtos.SanjinGTMessage;

import sanjin.quickfix.Order;
import sanjin.quickfix.OrderSide;
import sanjin.quickfix.TraderInterface;
import sanjin.quickfix.utils.mina.codec.ProtoBufCodecFactory;

public class GatewayServer {
	private static Logger logger = LogManager.getLogger("gateway");
	private static Logger executionLogger = LogManager.getLogger("execution");
	private static Logger positionLogger = LogManager.getLogger("position");
	private static Logger accountLogger = LogManager.getLogger("account");
	public static DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	public static GatewayServer cache = new GatewayServer();
	
	private GatewayServer() {
		int port = Integer.parseInt(TraderInterface.get().gatewayPort);
		SocketAcceptor acceptor = new NioSocketAcceptor(); // (1)
		
		acceptor.getFilterChain().addLast( "ProtoBuf_codec", 
				new ProtocolCodecFilter( new ProtoBufCodecFactory()));      // (2)
		acceptor.setHandler(new ServerHandler()); // (3)
		
		acceptor.getSessionConfig().setReadBufferSize(2048*2048); // (4)
		
		try {
			acceptor.bind(new InetSocketAddress(port)); // (6)
		} catch (IOException e) {
			e.printStackTrace();
		}
        logger.info("--------------------------------------------------");
        logger.info("Server Started");
        logger.info("[Server]Listening on port " + port);
        logger.info("--------------------------------------------------");
	}
	
	public static GatewayServer getInstance() {
		return cache;
	}
	
	public void sendOrderReport2Client(IoSession session, Order order){
		StockPoolGatewayProtos.BrokerOrder.Builder brokerOrderBuilder = StockPoolGatewayProtos.BrokerOrder.newBuilder();
		boolean rejected = order.getRejected();
		boolean canceled = order.getCanceled();
		boolean finished = order.isFinished();
		long traded = (long) order.getExecuted();
		int quantity = order.getQuantity();
		brokerOrderBuilder.setOrderStatus(OrderStatus.STATUS_NOTTRADED);
		if(!(rejected||canceled||finished)){
			if(traded == 0) {
				brokerOrderBuilder.setOrderStatus(OrderStatus.STATUS_NOTTRADED);
				executionLogger.info("Not Traded");
			}
			else {
				brokerOrderBuilder.setOrderStatus(OrderStatus.STATUS_PARTTRADED);
				executionLogger.info("Part Traded");
			}
		}else {
			if(finished && traded==quantity) {
				brokerOrderBuilder.setOrderStatus(OrderStatus.STATUS_ALLTRADED);
				executionLogger.info("All Traded");
			}
			else if(canceled) {
				brokerOrderBuilder.setOrderStatus(OrderStatus.STATUS_CANCELLED);
				brokerOrderBuilder.setCancelTime(Long.parseLong(df.format(new Date())));
				executionLogger.info("Cancelled");
			} else if(rejected) {
				brokerOrderBuilder.setOrderStatus(OrderStatus.STATUS_REJECTED);
				executionLogger.info("rejected");
			}
		}
		if(order.getOrderTime() != null) 
			brokerOrderBuilder.setOrderTime(Long.parseLong(df.format(order.getOrderTime())));
		Exchange exch = Exchange.SZ;
		if(order.getSymbol().startsWith("6") || order.getSymbol().startsWith("5"))
			exch = Exchange.SH;
		if(TraderInterface.get().isHkTrade.equals("1"))
			if(exch == Exchange.SH)
				exch = Exchange.HKSH;
			else
				exch = Exchange.HKSZ;
		if(order.getMessage()!=null || order.getMessage().length()>0)
			brokerOrderBuilder.setMessage(order.getMessage());
		BrokerOrder brokerOrder = brokerOrderBuilder.setSymbol(order.getSymbol()).setExchange(exch)
				.setBrokerId(TraderInterface.get().brokerId).setBrokerOrderId(order.getID()).
				setDirection(order.getSide().equals(OrderSide.BUY)? Direction.BUY:Direction.SELL).
				setPrice(order.getLimit()).setTotalVolume(order.getQuantity()).setTradedPrice(order.getAvgPx())
				.setTradedVolume(traded).setClientOrderId(order.getClientOrderID()).build();
    	SanjinGTMessage message = SanjinGTMessage.newBuilder().setMsgType(GTMSGTYPE.Broker_Order).setBrokerOrder(brokerOrder).build();
    	//addr + message
		sendToClient(session, message);
    	executionLogger.info(message);
	}
	public static void sendPositionToClient(IoSession ioSession, SanjinGTMessage messageReport) {
		positionLogger.info(messageReport);
		sendToClient(ioSession, messageReport);
	}
	public static void sendBalanceToClient(IoSession ioSession, SanjinGTMessage messageReport) {
		accountLogger.info(messageReport);	
		sendToClient(ioSession, messageReport);
	}
    public static void sendToClient(IoSession session, Object message){
    	session.write(message);
    }
    public static void main(String[] args) {
    	GatewayServer server = new GatewayServer();

    }

}
