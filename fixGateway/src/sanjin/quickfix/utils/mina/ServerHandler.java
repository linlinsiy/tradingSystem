package sanjin.quickfix.utils.mina;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.sanjin.bean.StockPoolClientProtos.Direction;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerAccount;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerCancelOrder;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerControlMessage;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerExecutionReport;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerOrder;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerPosition;
import com.sanjin.bean.StockPoolGatewayProtos.SanjinGTMessage;

import sanjin.quickfix.Order;
import sanjin.quickfix.OrderSide;
import sanjin.quickfix.TraderInterface;

public class ServerHandler extends IoHandlerAdapter { // (1)
	private static Logger logger = LogManager.getLogger("gateway");
	private static Logger acceptLogger = LogManager.getLogger("accept");
	static public  final Map<String,IoSession> ioSessionMap = new HashMap<String,IoSession>();
	
    @Override
    public void sessionCreated(IoSession session) {
//    	SESSIONSET.add(session.get);
        //session创建时回调
    	String clientIP = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();  
        session.setAttribute("KEY_SESSION_CLIENT_IP", clientIP); 
        ioSessionMap.put(clientIP, session);
        System.out.println(clientIP+":Session Created!");
        logger.info(clientIP+":Session Created!");
        
    }
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        //session关闭时回调
    	logger.info("Session Closed!");
    	removeSession(session);
    }
    
    public void removeSession(IoSession session) {
    	String clientIP = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
    	logger.info(clientIP+" remove from sessionMap");
    	if(ioSessionMap.containsKey(clientIP)) 
    		ioSessionMap.remove(clientIP);
    	else {
    		String removekey = null;
    		for(String key:ioSessionMap.keySet()) {
    			if(ioSessionMap.get(key) == session) {
    				removekey = key;
    				break;
    			}
    		}
    		if(removekey!=null) {
    			ioSessionMap.remove(removekey);
    		}
    	}
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        //session打开时回调
    	logger.info("Session Opened!");
    	
//        ServerHandler.test();
    }


    @Override
    public void exceptionCaught(IoSession session, Throwable cause) {
        //异常时回调
        cause.printStackTrace();
        logger.error("error",cause);
        //关闭session
        session.close(true);
        removeSession(session);
        
    }
    
	@Override
	public void messageReceived(IoSession session, Object message) {
		//异步接收消息
		SanjinGTMessage report = (SanjinGTMessage)message;
		acceptLogger.info(report.getMsgType());
		switch(report.getMsgType()){
		case Broker_Account:
			serverHandleMessage(report.getBrokerAccount());
			break;
		case Broker_Order:
			serverHandleMessage(report.getBrokerOrder());
			break;
		case Broker_Position:
			serverHandleMessage(report.getBrokerPosition());
			break;
		case Broker_Control_Message:
			serverHandleMessage(report.getControlMessage());
			break;
		case Broker_Execution_Report:
			serverHandleMessage(report.getExecutionReport());
			break;
		case Broker_Cancel_Order:
			serverHandleMessage(report.getCancelOrder());
		default:
			break;
		}
	}
	
	   
    private void serverHandleMessage(BrokerCancelOrder cancelOrder) {
		//将CancelOrder转为Order
    	Order canorder = new Order();
      	canorder.setSymbol(cancelOrder.getSymbol());
      	canorder.setSide(cancelOrder.getDirection().equals(Direction.BUY)? OrderSide.BUY:OrderSide.SELL);
      	canorder.setOriginalID(cancelOrder.getOrigOrderid());
      	canorder.setID(cancelOrder.getCancelOrderid());
  		acceptLogger.info(cancelOrder);
  		TraderInterface.get().doCancel(canorder, TraderInterface.get().account);
	}
	private void serverHandleMessage(BrokerAccount brokerAccount){
    	// TODO
    	
    }
    private void serverHandleMessage(BrokerOrder brokerOrder){
    	//将brokerOrder转换为Order
    	Order order = new Order();
    	order.setID(brokerOrder.getBrokerOrderId());
    	order.setClientOrderID(brokerOrder.getClientOrderId());
    	order.setSymbol(brokerOrder.getSymbol());
    	order.setSide(brokerOrder.getDirection().equals(Direction.BUY)? OrderSide.BUY:OrderSide.SELL);
    	order.setLimit(brokerOrder.getPrice());
    	order.setQuantity((int) brokerOrder.getTotalVolume());
    	order.setOrderTime(new Date(brokerOrder.getOrderTime()));//存入当前时间
    	acceptLogger.info(order);
    	TraderInterface.get().doOrder(order, TraderInterface.get().account);
    }
    private void serverHandleMessage(BrokerPosition brokerPosition){
    	// TODO
    	
    }
    private void serverHandleMessage(BrokerControlMessage controlMessage){
    	// TODO
    	
    }
    private void serverHandleMessage(BrokerExecutionReport executionReport){
    	// TODO
    	
    }
	
	
//    public static void test() throws FieldNotFound{
//    	PositionReport report = (new PositionReport());
//		report.set(new Account("linsiyuan"));
//		report.set(new Symbol("60001"));
//		report.set(new SecurityExchange("shanghai"));
//		report.set(new LongQty(1000.1));
//    	String account = report.getAccount().getValue();
//    	String symbol = report.getSymbol().getValue();
//    	LongQty longQty = report.getLongQty();
//		StockPoolGatewayProtos.BrokerPosition.Builder positionBuilder = StockPoolGatewayProtos.BrokerPosition.newBuilder();
//		String brokerId = "001";
//		BrokerPosition positionProto = positionBuilder.setSymbol(symbol)
//				.setExchange(report.getSecurityExchange().getValue())
//				.setBrokerId(brokerId)
//				.setPosition((long)longQty.getValue())
//				.build();
//    	StockPoolGatewayProtos.SanjinGTMessage.Builder messageBuilder = StockPoolGatewayProtos.SanjinGTMessage.newBuilder();
//    	SanjinGTMessage messageReport = messageBuilder.setMsgType(GTMSGTYPE.Broker_Position).setBrokerPosition(positionProto).build();
//		//序列化
////		byte[] byteOfPosition = messageReport.toByteArray();
//    	Set<String> set = ioSessionMap.keySet();
//    	for (String str : set) {
//			Server.sendToClient(ioSessionMap.get(str), messageReport);
//		}
//    }

}
