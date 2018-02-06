package sanjin.quickfix.utils.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.google.protobuf.InvalidProtocolBufferException;
import com.sanjin.bean.StockPoolGatewayProtos;
import com.sanjin.bean.StockPoolGatewayProtos.*;


public class ClientHandler extends IoHandlerAdapter {

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
			System.out.println("default");
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
    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
        //心跳
        System.out.println("客户端ide:");
    }
    
    private void clientHandleMessage(BrokerAccount brokerAccount){
    	// TODO
    	
    	System.out.println(brokerAccount.getAvailable());
    }
    private void clientHandleMessage(BrokerOrder brokerOrder){
    	// TODO
    	
    }
    private void clientHandleMessage(BrokerPosition brokerPosition){
    	// TODO
    	
    	System.out.println("client:"+brokerPosition.getBrokerId()+" has "+brokerPosition.getPosition()+" "+brokerPosition.getSymbol()+" stocks!");
    }
    private void clientHandleMessage(BrokerControlMessage controlMessage){
    	// TODO
    	
    }
    private void clientHandleMessage(BrokerExecutionReport executionReport){
    	// TODO
    	
    }

    
}
