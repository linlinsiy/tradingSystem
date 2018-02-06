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

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

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
import quickfix.field.DeliverToCompID;
import quickfix.field.EncryptMethod;
import quickfix.field.ExecID;
import quickfix.field.HandlInst;
import quickfix.field.LastShares;
import quickfix.field.LeavesQty;
import quickfix.field.LogonPasswd;
import quickfix.field.LogonStatus;
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

public class SanjinApplication implements Application {
    private final DefaultMessageFactory messageFactory = new DefaultMessageFactory();
    private final ConcurrentHashMap<String,Order> orderMap = new ConcurrentHashMap<String,Order>();
    private final ConcurrentLinkedQueue<quickfix.Message> messageQueue = new ConcurrentLinkedQueue<quickfix.Message>();
    private MessageProcessor processor = null;
    private Session session = null;
    private boolean logonstatus = false;
    private boolean isMissingField;

    static private final TwoWayMap sideMap = new TwoWayMap();
    static private final TwoWayMap typeMap = new TwoWayMap();
    static private final TwoWayMap tifMap = new TwoWayMap();
    static private final HashSet<ExecID> execIDs = new HashSet<ExecID>();

    public SanjinApplication() {
    	processor = new MessageProcessor();
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

    public class MessageProcessor implements Runnable {

        public void run() {
            try {
            	while(true) {
            		Message message = messageQueue.poll();
            		if(message == null)
            			continue;
                    MsgType msgType = new MsgType();
//                    if (isMissingField) {
//                        // For OpenFIX certification testing
//                        sendBusinessReject(message, BusinessRejectReason.CONDITIONALLY_REQUIRED_FIELD_MISSING, "Conditionally required field missing");
//                    }
                    if (message.getHeader().getField(msgType).valueEquals("8")) {
                    	System.out.println(message);
                        executionReport(message);
                    } else if (message.getHeader().getField(msgType).valueEquals("9")) {
                        cancelReject(message);
                    } else if (message.getHeader().getField(msgType).valueEquals("UF002")) {
                    	System.out.println(message);
                    	quickfix.fix42.UserLogonResponse m= (quickfix.fix42.UserLogonResponse)message;
                    	if (m.getLogonStatus().getValue()==LogonStatus.LOGON || m.getLogonStatus().getValue() == LogonStatus.ALREADY_LOGON)
                        {
                            logonstatus = true;
                            System.out.println("已经登录啦");
                        }
                    }
                    
            	}
            	
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
    	
        ExecID execID = (ExecID) message.getField(new ExecID());
        if (alreadyProcessed(execID))
            return;

        Order order = orderMap.get(message.getField(new ClOrdID()).getValue());
        if (order == null) {
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
            order.setOpen(0);
        } else if (ordStatus.valueEquals(OrdStatus.CANCELED)
                || ordStatus.valueEquals(OrdStatus.DONE_FOR_DAY)) {
            order.setCanceled(true);
            order.setOpen(0);
        } else if (ordStatus.valueEquals(OrdStatus.NEW)) {
            if (order.isNew()) {
                order.setNew(false);
            }
        }

        try {
            order.setMessage(message.getField(new Text()).getValue());
        } catch (FieldNotFound e) {
        }

        if (message.isSetField(OrdRejReason.FIELD))
            order.setMessage(geterrormsg(message.getString(OrdRejReason.FIELD)));
        notifyOrder(order);
        System.out.println(order);
//        if (fillSize.compareTo(BigDecimal.ZERO) > 0) {
//            Execution execution = new Execution();
//            execution.setExchangeID(message.getField(new ExecID()).getValue());
//
//            execution.setSymbol(message.getField(new Symbol()).getValue());
//            execution.setQuantity(fillSize.intValue());
//            if (message.isSetField(LastPx.FIELD)) {
//                execution.setPrice(Double.parseDouble(message.getString(LastPx.FIELD)));
//            }
//            Side side = (Side) message.getField(new Side());
//            execution.setSide(FIXSideToSide(side));
//        }
    }
    
    

    private void cancelReject(Message message) throws FieldNotFound {

        String id = message.getField(new ClOrdID()).getValue();
        Order order = orderMap.get(id);
        if (order == null)
            return;
        if (order.getOriginalID() != null)
            order = orderMap.get(order.getOriginalID());

        try {
            order.setMessage(message.getField(new Text()).getValue());
        } catch (FieldNotFound e) {
        }
        notifyOrder(order);
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
            System.out.println(message);
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
    
    public void doOrder(Order msg,String account)
    {
        if(!logonstatus)
        {
            System.out.println("请发送logon消息登录");
            return;
        }
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
        
    }

    public void doCancel(Order orimsg,String account) {
    	if (!logonstatus)
        {
    		System.out.println("请发送logon消息登录");
            return;
        }
    	Order msg = orderMap.get(orimsg.getOriginalID());
    	quickfix.fix42.OrderCancelRequest message = new quickfix.fix42.OrderCancelRequest();
        message.set(new ClOrdID(msg.getID()));
        message.set(new Symbol(msg.getSymbol()));
        if(msg.getSide()==OrderSide.BUY)
            message.set(new Side(Side.BUY));
        else
            message.set(new Side(Side.SELL));
        message.set(new TransactTime());
        message.set(new OrigClOrdID(msg.getOriginalID()));
        message.set(new Account(account));
        message.getHeader().setField(new OnBehalfOfCompID("SANJIN"));
        send(message);
        
    }
    

   

    

    public boolean isMissingField() {
        return isMissingField;
    }

    public void setMissingField(boolean isMissingField) {
        this.isMissingField = isMissingField;
    }

    
    
    /**
     * 事件定义
     * For Matlab 订阅
     */
    private java.util.Vector<OrderListener> listeners = new java.util.Vector<OrderListener>();
    public synchronized void addQuoteListener(OrderListener lis) {
        listeners.addElement(lis);
    }
    public synchronized void removeQuoteListener(OrderListener lis) {
        listeners.removeElement(lis);
    }
    public interface OrderListener extends java.util.EventListener {
        void onQuoteEvent(OrderEvent event);
    }
    
    public class OrderEvent extends java.util.EventObject {
        private static final long serialVersionUID = 1L;
        public Order order;

        OrderEvent(Object obj, Order q) {
            super(obj);
            this.order = q;
        }
    }

    @SuppressWarnings("unchecked")
    public void notifyOrder(Order q_) {
        java.util.Vector<OrderListener> listenersCopy;
        synchronized(this) {
            listenersCopy = (java.util.Vector<OrderListener>)listeners.clone();
        }
        for (int i=0; i < listenersCopy.size(); i++) {
            OrderEvent event = new OrderEvent(this, q_);
            listenersCopy.elementAt(i).onQuoteEvent(event);
        }
    }

	@Override
	public void onLogon(SessionID arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLogout(SessionID arg0) {
		// TODO Auto-generated method stub
		
	}
    
}
