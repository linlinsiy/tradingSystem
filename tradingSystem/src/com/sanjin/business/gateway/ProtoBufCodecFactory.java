package com.sanjin.business.gateway;

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
public class ProtoBufCodecFactory implements ProtocolCodecFactory {
	private final ProtoBufEncoder encoder;
	private final ProtoBufDecoder decoder;

	public ProtoBufCodecFactory() {
		this(Charset.defaultCharset());
	}

	public ProtoBufCodecFactory(Charset charSet) {
		this.encoder = new ProtoBufEncoder(charSet);
		this.decoder = new ProtoBufDecoder();
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