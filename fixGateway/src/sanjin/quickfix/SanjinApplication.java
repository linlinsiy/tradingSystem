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

import static quickfix.field.MsgType.BALANCE_REPORT;
import static quickfix.field.MsgType.EXECUTION_REPORT;
import static quickfix.field.MsgType.LOGIN_RESPONSE;
import static quickfix.field.MsgType.ORDER_CANCEL_REJECT;
import static quickfix.field.MsgType.POSITION_REPORT;
import static sanjin.quickfix.utils.mina.ServerHandler.ioSessionMap;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sanjin.bean.StockPoolClientProtos.Exchange;
import com.sanjin.bean.StockPoolGatewayProtos;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerAccount;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerPosition;
import com.sanjin.bean.StockPoolGatewayProtos.GTMSGTYPE;
import com.sanjin.bean.StockPoolGatewayProtos.SanjinGTMessage;

import quickfix.Application;
import quickfix.DefaultMessageFactory;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.RejectLogon;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.UnsupportedMessageType;
import quickfix.field.Account;
import quickfix.field.AvgPx;
import quickfix.field.BeginString;
import quickfix.field.BusinessRejectReason;
import quickfix.field.ClOrdID;
import quickfix.field.ClientID;
import quickfix.field.CumQty;
import quickfix.field.Currency;
import quickfix.field.EncryptMethod;
import quickfix.field.ExecID;
import quickfix.field.HandlInst;
import quickfix.field.LastShares;
import quickfix.field.LeavesQty;
import quickfix.field.LogonPasswd;
import quickfix.field.LogonStatus;
import quickfix.field.LongQty;
import quickfix.field.MsgSeqNum;
import quickfix.field.MsgType;
import quickfix.field.OnBehalfOfCompID;
import quickfix.field.OrdRejReason;
import quickfix.field.OrdStatus;
import quickfix.field.OrdType;
import quickfix.field.OrderQty;
import quickfix.field.OrigClOrdID;
import quickfix.field.Price;
import quickfix.field.RefMsgType;
import quickfix.field.RefSeqNum;
import quickfix.field.RequestID;
import quickfix.field.SecurityExchange;
import quickfix.field.SenderCompID;
import quickfix.field.SessionRejectReason;
import quickfix.field.Side;
import quickfix.field.Symbol;
import quickfix.field.TargetCompID;
import quickfix.field.Text;
import quickfix.field.TransactTime;
import quickfix.field.UseableAmt;
import quickfix.fix42.BalanceReport;
import quickfix.fix42.PositionReport;
import quickfix.fix42.RequestForBalance;
import quickfix.fix42.RequestForPosition;
import sanjin.quickfix.utils.mina.GatewayServer;

public class SanjinApplication implements Application {
	private static Logger logger = LogManager.getLogger("gateway");
	private static Logger fixOrderLogger = LogManager.getLogger("fixOrder");
	private static Logger acceptLogger = LogManager.getLogger("accept");
	
	private final DefaultMessageFactory messageFactory = new DefaultMessageFactory();
    private final ConcurrentHashMap<String,Order> orderMap = new ConcurrentHashMap<String,Order>();
    private final ConcurrentLinkedQueue<quickfix.Message> messageQueue = new ConcurrentLinkedQueue<quickfix.Message>();
    private FixMessageProcessor processor = null;
    private Session session = null;
    private volatile boolean logonstatus = false;
    private boolean isMissingField;

    static private final TwoWayMap sideMap = new TwoWayMap();
    static private final TwoWayMap typeMap = new TwoWayMap();
    static private final TwoWayMap tifMap = new TwoWayMap();
    static private final HashSet<ExecID> execIDs = new HashSet<ExecID>();
    static private final Map<String,PositionReport> longQtyMap = new HashMap<String, PositionReport>();

    public SanjinApplication() {
    	processor = new FixMessageProcessor();
		new Thread(processor).start();
    }

    public void onCreate(SessionID sessionID) {
    	session = Session.lookupSession(sessionID);
    	
    }

    public void toAdmin(quickfix.Message message, SessionID sessionID) {
    }

    public void toApp(quickfix.Message message, SessionID sessionID) throws DoNotSend {
    }

    public void fromAdmin(quickfix.Message message, SessionID sessionID) throws FieldNotFound,
            IncorrectDataFormat, IncorrectTagValue, RejectLogon {
    }

    public void fromApp(quickfix.Message message, SessionID sessionID) throws FieldNotFound,
            IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
    	messageQueue.add(message);
    }

    public class FixMessageProcessor implements Runnable {

        public void run() {
        	
				while(true) {
					try {
						Message message = messageQueue.poll();
						if(message == null)
							continue;
					    MsgType msgType = new MsgType();
					    
	//                    if (isMissingField) {
	//                        // For OpenFIX certification testing
	//                        sendBusinessReject(message, BusinessRejectReason.CONDITIONALLY_REQUIRED_FIELD_MISSING, "Conditionally required field missing");
	//                    }
					    if (message.getHeader().getField(msgType).valueEquals(EXECUTION_REPORT)) {
					        executionReport(message);
					    } else if (message.getHeader().getField(msgType).valueEquals(ORDER_CANCEL_REJECT)) {
					        cancelReject(message);
					    } else if (message.getHeader().getField(msgType).valueEquals(POSITION_REPORT)) {
					        //持仓消息
					    	executePositionReport(message);
					    } else if (message.getHeader().getField(msgType).valueEquals(BALANCE_REPORT)) {
					        //资金消息
					    	executeBalanceReport(message);
					    } else if (message.getHeader().getField(msgType).valueEquals(LOGIN_RESPONSE)) {
					    	quickfix.fix42.UserLogonResponse m= (quickfix.fix42.UserLogonResponse)message;
					    	if (m.getLogonStatus().getValue()==LogonStatus.LOGON || m.getLogonStatus().getValue() == LogonStatus.ALREADY_LOGON)
					        {
					            logonstatus = true;
					            System.out.println("已经登录了");
					        }
					    } 
					} catch (Throwable e) {
						e.printStackTrace();
						logger.error("error",e);
					}
				}
			
        }
    }

    public boolean isLogon() {
    	return logonstatus;
    }
    
    private void sendSessionReject(Message message, int rejectReason) throws FieldNotFound,
            SessionNotFound {
        Message reply = createMessage(message, MsgType.REJECT);
        reverseRoute(message, reply);
        String refSeqNum = message.getHeader().getString(MsgSeqNum.FIELD);
        reply.setString(RefSeqNum.FIELD, refSeqNum);
        reply.setString(RefMsgType.FIELD, message.getHeader().getString(MsgType.FIELD));
        reply.setInt(SessionRejectReason.FIELD, rejectReason);
        Session.sendToTarget(reply);
    }

    private void sendBusinessReject(Message message, int rejectReason, String rejectText)
            throws FieldNotFound, SessionNotFound {
        Message reply = createMessage(message, MsgType.BUSINESS_MESSAGE_REJECT);
        reverseRoute(message, reply);
        String refSeqNum = message.getHeader().getString(MsgSeqNum.FIELD);
        reply.setString(RefSeqNum.FIELD, refSeqNum);
        reply.setString(RefMsgType.FIELD, message.getHeader().getString(MsgType.FIELD));
        reply.setInt(BusinessRejectReason.FIELD, rejectReason);
        reply.setString(Text.FIELD, rejectText);
        Session.sendToTarget(reply);
    }

    private Message createMessage(Message message, String msgType) throws FieldNotFound {
        return messageFactory.create(message.getHeader().getString(BeginString.FIELD), msgType);
    }

    private void reverseRoute(Message message, Message reply) throws FieldNotFound {
        reply.getHeader().setString(SenderCompID.FIELD,
                message.getHeader().getString(TargetCompID.FIELD));
        reply.getHeader().setString(TargetCompID.FIELD,
                message.getHeader().getString(SenderCompID.FIELD));
    }

    private void executionReport(Message message) throws FieldNotFound {
    	System.out.println(message);
    	fixOrderLogger.info("Get Order Report:" + message);
        ExecID execID = (ExecID) message.getField(new ExecID());
        if (alreadyProcessed(execID))
            return;

        //如果是撤单，从orderMap中得到原order
        Order order = orderMap.get(message.getField(new ClOrdID()).getValue());
        if (order == null) {
            order = orderMap.get(message.getField(new OrigClOrdID()).getValue());
            if(order == null)
            	return;
        }

        BigDecimal fillSize;

        if (message.isSetField(LastShares.FIELD)) {
            LastShares lastShares = new LastShares();
            message.getField(lastShares);
            fillSize = new BigDecimal("" + lastShares.getValue());
        } else {
            // > FIX 4.1
            LeavesQty leavesQty = new LeavesQty();
            message.getField(leavesQty);
            fillSize = new BigDecimal(order.getQuantity()).subtract(new BigDecimal("" + leavesQty.getValue()));
        }

        if (fillSize.compareTo(BigDecimal.ZERO) > 0) {
            order.setOpen(order.getOpen() - (int) Double.parseDouble(fillSize.toPlainString()));
            order.setExecuted(Double.parseDouble(message.getString(CumQty.FIELD)));
            order.setAvgPx(Double.parseDouble(message.getString(AvgPx.FIELD)));
        }

        OrdStatus ordStatus = (OrdStatus) message.getField(new OrdStatus());
        
        if (ordStatus.valueEquals(OrdStatus.REJECTED)) {
            order.setRejected(true);
            order.setFinished(true);
            order.setOpen(0);
        } else if (ordStatus.valueEquals(OrdStatus.CANCELED)
                || ordStatus.valueEquals(OrdStatus.DONE_FOR_DAY)) {
            order.setCanceled(true);
            //添加canceltime
            order.setCancelTime(new Date());            
            order.setFinished(true);
            order.setOpen(0);
        } else if (ordStatus.valueEquals(OrdStatus.NEW)) {
            if (order.isNew()) {
                order.setNew(false);
            }
        } else if(ordStatus.valueEquals(OrdStatus.FILLED))
        {
        	order.setFinished(true);
        }
        	
        try {
            order.setMessage(message.getField(new Text()).getValue());
        } catch (FieldNotFound e) {
        	logger.error("Field Not Found");
        }
        
        if (message.isSetField(OrdRejReason.FIELD))
        	if(order.getMessage()==null)
        		order.setMessage(geterrormsg(message.getString(OrdRejReason.FIELD)));
        	else
        		order.setMessage(order.getMessage()+geterrormsg(message.getString(OrdRejReason.FIELD)));
        fixOrderLogger.info("Get Order Report:" + order);
    	Set<String> set = ioSessionMap.keySet();
    	for (String str : set) {
			GatewayServer.getInstance().sendOrderReport2Client(ioSessionMap.get(str), order);
		} 
    }
    
    private void cancelReject(Message message){
    	System.out.println(message);
    	fixOrderLogger.info("Get Cancel Report:" + message);
        try {
			String id = message.getField(new ClOrdID()).getValue();
			Order order = orderMap.get(id);
			order.setCanceled(true);
			if (order == null)
			    return;
			if (order.getOriginalID() != null)
			    order = orderMap.get(order.getOriginalID());          
			order.setMessage(message.getField(new Text()).getValue());
  
			Set<String> set = ioSessionMap.keySet();
			for (String str : set) {
				GatewayServer.getInstance().sendOrderReport2Client(ioSessionMap.get(str), order);
			}
		} catch (FieldNotFound e) {
			logger.error("Field Not Found");
		}
    }

    private void executePositionReport(Message message){
    	System.out.println(message);
//    	fixOrderLogger.info("Get Position Report:" + message);
    	//1.缓存
    	quickfix.fix42.PositionReport report = (quickfix.fix42.PositionReport)message;
    	SanjinGTMessage messageReport;
		try {
			String account = report.getAccount().getValue();
			String symbol = report.getSymbol().getValue();
			LongQty longQty = report.getLongQty();
			
			longQtyMap.put(account+symbol, report);
			//2.mina发送消息出去,发送给所有client
			StockPoolGatewayProtos.BrokerPosition.Builder positionBuilder = StockPoolGatewayProtos.BrokerPosition.newBuilder();
			String brokerId = TraderInterface.get().brokerId;
			Exchange exch = Exchange.SZ;
			if(symbol.startsWith("6") || symbol.startsWith("5"))
				exch = Exchange.SH;
			if(TraderInterface.get().isHkTrade.equals("1"))
				if(exch == Exchange.SH)
					exch = Exchange.HKSH;
				else
					exch = Exchange.HKSZ;
			BrokerPosition positionProto = positionBuilder.setSymbol(symbol)
					.setExchange(exch)
					.setBrokerId(brokerId)
					.setPosition((long)longQty.getValue())
					.setLeavesQty((long)report.getLeavesQty().getValue())
					.build();
			StockPoolGatewayProtos.SanjinGTMessage.Builder messageBuilder = StockPoolGatewayProtos.SanjinGTMessage.newBuilder();
			messageReport = messageBuilder.setMsgType(GTMSGTYPE.Broker_Position).setBrokerPosition(positionProto).build();
			//		byte[] byteOfPosition = messageReport.toByteArray();
	    	Set<String> set = ioSessionMap.keySet();
	    	for (String str : set) {
				GatewayServer.sendPositionToClient(ioSessionMap.get(str), messageReport);
			}    	   	
		} catch (FieldNotFound e) {
			logger.error("Field Not Found");
		}
    }
    
    private void executeBalanceReport(Message message){
    	System.out.println(message);
//    	fixOrderLogger.info("Get Balance Report:" + message);
    	
    	quickfix.fix42.BalanceReport report = (quickfix.fix42.BalanceReport)message;
    	try {
			String account = report.getAccount().getValue();
			if(!report.getCurrency().valueEquals("HKD"))
				return;
			Double useableAmt = report.getUseableAmt().getValue();
			StockPoolGatewayProtos.BrokerAccount.Builder balanceBuilder = StockPoolGatewayProtos.BrokerAccount.newBuilder();
			String brokerId = TraderInterface.get().brokerId;
			BrokerAccount brokerAccount = balanceBuilder.setAccount(account)
					.setBrokerId(brokerId)
					.setAvailable(useableAmt)
					.build();
			StockPoolGatewayProtos.SanjinGTMessage.Builder messageBuilder = StockPoolGatewayProtos.SanjinGTMessage.newBuilder();
			SanjinGTMessage messageReport = messageBuilder.setMsgType(GTMSGTYPE.Broker_Account).setBrokerAccount(brokerAccount).build();
//    	byte[] byteOfBalance = brokerAccount.toByteArray();
			Set<String> set = ioSessionMap.keySet();
			for (String str : set) {
				GatewayServer.sendBalanceToClient(ioSessionMap.get(str), messageReport);
			}
		} catch (FieldNotFound e) {
			logger.error("Field Not Found");
		}
    }
    
    private boolean alreadyProcessed(ExecID execID) {
        
        if (execIDs.contains(execID))
            return true;
        execIDs.add(execID);
        return false;
    }

    private void send(quickfix.Message message) {
        try {
            Session.sendToTarget(message, session.getSessionID());
        } catch (SessionNotFound e) {
            System.out.println(e);
        }
    }

    private static String geterrormsg(String vali)
    {
    	int val = Integer.parseInt(vali);
        switch (val)
        {
            case OrdRejReason.UNKNOWN_ACCOUNT:
                return "未知账号";
            case OrdRejReason.UNKNOWN_ORDER:
                return "未知订单";
            case OrdRejReason.UNKNOWN_SYMBOL:
                return "未知股票代码";
            case OrdRejReason.UNSUPPORTED_ORDER_CHARACTERISTIC:
                return "不支持的下单类型";
            case OrdRejReason.ORDER_EXCEEDS_LIMIT:
                return "下单数量超过阈值";
            case OrdRejReason.INCORRECT_QUANTITY:
                return "下单数量格式错误";
            case OrdRejReason.EXCHANGE_CLOSED:
                return "已休市";
            case OrdRejReason.DUPLICATE_ORDER:
                return "重复下单";
            default:
                return "其他错误";
        }
    }
    
    public void accountLogon(String account, String pwd) throws SessionNotFound
    {
    	quickfix.fix42.UserLogonRequest message = new quickfix.fix42.UserLogonRequest();
        message.set(new RequestID(Order.generateID()));
        message.set(new ClientID(account));
        message.set(new EncryptMethod(EncryptMethod.NONE_OTHER));
        message.set(new LogonPasswd(pwd));
        send(message);
    }
    
    public void accountLogout(String account)
    {
    	quickfix.fix42.UserLogoutRequest message = new quickfix.fix42.UserLogoutRequest();
        message.set(new RequestID(Order.generateID()));
        message.set(new ClientID(account));
        send(message);
    }
    
    public String doOrder(Order msg,String account)
    {
    	logger.info("Request For Order:"+msg);
    	quickfix.fix42.NewOrderSingle message = new quickfix.fix42.NewOrderSingle();
        message.set(new ClOrdID(msg.getID()));
        message.set(new Symbol(msg.getSymbol()));
        if(msg.getSide()==OrderSide.BUY)
            message.set(new Side(Side.BUY));
        else
            message.set(new Side(Side.SELL));
        if (msg.getSymbol().startsWith("6"))
            message.set(new SecurityExchange(SecurityExchange.SHANGHAI_STOCK_EXCHANGE_SHANGHAI_HONG_KONG_STOCK_CONNECT));
        else
            message.set(new SecurityExchange(SecurityExchange.SHENZHEN_STOCK_EXCHANGE_SHENZHEN_HONG_KONG_STOCK_CONNECT));
        message.set(new TransactTime());
        message.set(new Currency("CNY"));
        message.set(new Price(msg.getLimit()));
        message.set(new OrderQty(msg.getQuantity()));
        message.set(new OrdType(OrdType.LIMIT));
        message.set(new HandlInst(HandlInst.AUTOMATED_EXECUTION_ORDER_PRIVATE_NO_BROKER_INTERVENTION));
        message.set(new Account(account));
        message.getHeader().setField(new OnBehalfOfCompID("SANJIN"));
        orderMap.put(msg.getID(), msg);
        send(message);
        return "";
    }

    public void doCancel(Order orimsg,String account) {
    	logger.info("Request For Cancel Order:"+orimsg);
    	Order msg = orderMap.get(orimsg.getOriginalID());
    	quickfix.fix42.OrderCancelRequest message = new quickfix.fix42.OrderCancelRequest();
        message.set(new ClOrdID(orimsg.getID()));
        message.set(new Symbol(msg.getSymbol()));
        if(msg.getSide()==OrderSide.BUY)
            message.set(new Side(Side.BUY));
        else
            message.set(new Side(Side.SELL));
        message.set(new TransactTime());
        message.set(new OrigClOrdID(msg.getID()));
        message.set(new Account(account));
        message.getHeader().setField(new OnBehalfOfCompID("SANJIN"));
        send(message);
    }
    
    /**
     * 查询持仓消息
     */
    public void queryPosition(String account){
    	logger.info("Request For Query Position" + account);
    	RequestForPosition message = new RequestForPosition();
        message.set(new RequestID(Order.generateID()));
        message.set(new Account(account));
        message.getHeader().setField(new OnBehalfOfCompID("SANJIN"));
//        System.out.println("query position");
    	send(message); 
    }
   
    /**
     * 查询资金消息
     */
    public void queryBalance(String account){
    	logger.info("Request For Query Balance" + account);
    	quickfix.fix42.RequestForBalance message = new RequestForBalance();
        message.set(new RequestID(Order.generateID()));
        message.set(new Account(account));
        message.getHeader().setField(new OnBehalfOfCompID("SANJIN"));
//        System.out.println("query balance");
    	send(message);
    }

    public boolean isMissingField() {
        return isMissingField;
    }

    public void setMissingField(boolean isMissingField) {
        this.isMissingField = isMissingField;
    }
    
	@Override
	public void onLogon(SessionID arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLogout(SessionID arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public static void main(String[] args) throws FieldNotFound {
		SanjinApplication app = new SanjinApplication();
//		Set<String> set = ioSessionMap.keySet();
//		for (String string : set) {
//			System.out.println(string + "->" + ioSessionMap.get(string).toString());
//		}

//		PositionReport positionReport = (new PositionReport());
//		positionReport.set(new Account("linsiyuan"));
//		positionReport.set(new Symbol("60001"));
//		positionReport.set(new SecurityExchange("shanghai"));
//		positionReport.set(new LongQty(1000.1));
//		app.executePositionReport(positionReport);
		
		BalanceReport balanceReport = new BalanceReport();
		balanceReport.set(new Account("linsiyuan"));
		balanceReport.set(new UseableAmt(200.1));
		app.executeBalanceReport(balanceReport);
		
	}
    
}
