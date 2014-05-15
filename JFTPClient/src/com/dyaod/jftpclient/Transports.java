package com.dyaod.jftpclient;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

public class Transports {
	public static  ChannelBuffer tranStr2Buffer(String str) {
		byte[] msg = str.getBytes();
		ChannelBuffer buffer = ChannelBuffers.buffer(msg.length);
		buffer.writeBytes(msg);
		return buffer;
	}
}
