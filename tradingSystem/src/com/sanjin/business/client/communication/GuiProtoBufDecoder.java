package com.sanjin.business.client.communication;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.sanjin.bean.StockPoolClientProtos.SanjinClientMessage;

public class GuiProtoBufDecoder extends CumulativeProtocolDecoder {
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {

		try {
			if (in.prefixedDataAvailable(4)) {
				int length = in.getInt();
				int flag1 = in.getUnsigned();
				int flag2 = in.getUnsigned();
				if (flag1 != 3 || flag2 != 7) {
					return false;
				}

				byte[] bytes = new byte[length - 2];
				in.get(bytes);
				SanjinClientMessage.Builder builder = SanjinClientMessage.newBuilder();

				CodedInputStream.newInstance(bytes).readMessage(builder,
						ExtensionRegistryLite.getEmptyRegistry());
				out.write(builder.build());
				return true;
			} else {
				// System.out.println("has dispose");
				// super.dispose(session);
				return false;
			}
		} catch(Exception e){
			e.printStackTrace();
			return false;
		} finally {
			in.free();
		}

	}

}
