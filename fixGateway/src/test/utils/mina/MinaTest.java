package test.utils.mina;

import org.junit.Test;

import com.sanjin.bean.StockPoolGatewayProtos;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerPosition;
import com.sanjin.bean.StockPoolGatewayProtos.GTMSGTYPE;
import com.sanjin.bean.StockPoolGatewayProtos.SanjinGTMessage;

import quickfix.field.Account;
import quickfix.field.LongQty;
import quickfix.field.SecurityExchange;
import quickfix.field.Symbol;
import quickfix.fix42.PositionReport;
import sanjin.quickfix.utils.mina.Client;
import sanjin.quickfix.utils.mina.GatewayServer;
import static quickfix.field.MsgType.BALANCE_REPORT;
import static quickfix.field.MsgType.EXECUTION_REPORT;
import static quickfix.field.MsgType.LOGIN_RESPONSE;
import static quickfix.field.MsgType.ORDER_CANCEL_REJECT;
import static quickfix.field.MsgType.POSITION_REPORT;
import static sanjin.quickfix.utils.mina.ServerHandler.ioSessionMap;

import java.util.Set;

public class MinaTest {
	@Test
	public void testMessage() throws Exception {
    	new Client();		

	}
}
