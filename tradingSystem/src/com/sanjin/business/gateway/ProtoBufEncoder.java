package com.sanjin.business.gateway;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.sanjin.bean.StockPoolGatewayProtos.SanjinGTMessage;

public class ProtoBufEncoder extends ProtocolEncoderAdapter {
	private final Charset charset;

	public ProtoBufEncoder(Charset charset) {
		this.charset = charset;
	}

	@Override
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {

		SanjinGTMessage m = (SanjinGTMessage) message;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		com.google.protobuf.CodedOutputStream cos = com.google.protobuf.CodedOutputStream
				.newInstance(baos);
		cos.writeRawVarint32(3);
		cos.writeRawVarint32(7);
		cos.writeRawVarint32(m.getSerializedSize());
		m.writeTo(cos);
		cos.flush();
		byte[] temp = baos.toByteArray();
		IoBuffer buffer = IoBuffer.allocate(temp.length + 4);

		buffer.putInt(temp.length);
		buffer.put(temp);
		buffer.flip();
		out.write(buffer);
		buffer.free();
	}

}
