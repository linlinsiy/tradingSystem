package com.sanjin.business.client.communication;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * 
 * @author guanzl
 *
 */
public class GuiProtoBufCodecFactory implements ProtocolCodecFactory {
	private final GuiProtoBufEncoder encoder;
	private final GuiProtoBufDecoder decoder;

	public GuiProtoBufCodecFactory() {
		this(Charset.defaultCharset());
	}

	public GuiProtoBufCodecFactory(Charset charSet) {
		this.encoder = new GuiProtoBufEncoder(charSet);
		this.decoder = new GuiProtoBufDecoder();
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}
}