package test;

import com.google.protobuf.ByteString;
import com.sanjin.bean.StockPoolClientProtos.ClientPosition;
import com.sanjin.bean.StockPoolClientProtos.Exchange;
import com.sanjin.bean.StockPoolClientProtos.PositionStatus;

public class ProtoTest {
	public static void main(String[] args) {
		ClientPosition.Builder builder = ClientPosition.newBuilder();
		builder.setClientIdBytes(ByteString.copyFrom("asdfasdf".getBytes()));
		builder.setSymbol("我的");
		builder.setExchange(Exchange.SH);
		builder.setPositionStatus(PositionStatus.NORMAL);
		builder.setPosition(1000);
		System.out.println(builder.hasExchange());
		System.out.println(builder.hasPosition());
		builder.setPosition(builder.getPosLong()+200);
		System.out.println(builder.getPosition());
		ClientPosition pos = builder.build();
		String a = new String("asdfasdf".getBytes());
		String b = new String("asdfasdf".getBytes());
		System.out.println(pos);
		
		
	}
}
