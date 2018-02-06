package com.sanjin.business.gateway;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.google.protobuf.TextFormat;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerAccount;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerControlMessage;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerExecutionReport;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerOrder;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerPosition;
import com.sanjin.bean.StockPoolGatewayProtos.SanjinGTMessage;




public class FixClientHandler extends IoHandlerAdapter {
	private static Logger logger = LogManager.getLogger("gateway");
	private FixClient client;
	
	public FixClientHandler(FixClient client) {
		super();
		this.client = client;
	}	

	public FixClientHandler() {
		super();
	}


	@Override
	public void messageReceived(IoSession session, Object message){
        //异步接收消息
		SanjinGTMessage report = (SanjinGTMessage)message;
		switch(report.getMsgType()){
		case Broker_Account:
			clientHandleMessage(report.getBrokerAccount());
			break;
		case Broker_Order:
			clientHandleMessage(report.getBrokerOrder());
			break;
		case Broker_Position:
			clientHandleMessage(report.getBrokerPosition());
			break;
		
		case Broker_Control_Message:
			clientHandleMessage(report.getControlMessage());
			break;
		case Broker_Execution_Report:
			clientHandleMessage(report.getExecutionReport());
			break;		
		default:
			break;
		}
	}
	
    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        //出现异常
        cause.printStackTrace();
        session.close(true);
    }    
    
    @Override
	public void sessionClosed(IoSession session) throws Exception {
        if (session != null) {
            session.close(true);
            logger.info("sessionClosed");
        }
        client.reconnect();
	}

	private void clientHandleMessage(BrokerAccount brokerAccount){
    	FixMessageManager.getInstance().addMessage(brokerAccount);
    	logger.info("Query Balance:"+brokerAccount.getAvailable());
    }
    private void clientHandleMessage(BrokerOrder brokerOrder){
    	FixMessageManager.getInstance().addMessage(brokerOrder);
    	logger.info("Do Order:"+TextFormat.shortDebugString(brokerOrder));
    }
    private void clientHandleMessage(BrokerPosition brokerPosition){
    	FixMessageManager.getInstance().addMessage(brokerPosition);
    	logger.info("Query Position:"+TextFormat.shortDebugString(brokerPosition));
    }
    
    private void clientHandleMessage(BrokerControlMessage controlMessage){
    	// TODO
    	
    }
    private void clientHandleMessage(BrokerExecutionReport executionReport){
    	// TODO
    	
    }
    
    

    
}
