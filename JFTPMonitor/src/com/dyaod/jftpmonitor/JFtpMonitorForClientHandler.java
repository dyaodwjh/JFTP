package com.dyaod.jftpmonitor;

import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.WriteCompletionEvent;

public class JFtpMonitorForClientHandler extends SimpleChannelHandler {

	private Logger log = Logger.getLogger(this.getClass());
	private JFtpMonitorBusiness jftpMonitorBusiness;

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		log.info("channelOpen");
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		log.info("channelConnected");
		
		
		
		// e.getChannel().write(Transports.tranStr2Buffer("haha"));
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		log.info("channelDisconnected");
	}

	@Override
	public void channelUnbound(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		log.info("channelUnbound");
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		log.info("channelClosed");
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		log.info("messageReceived");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		log.info("exceptionCaught");

	}

	@Override
	public void writeComplete(ChannelHandlerContext ctx, WriteCompletionEvent e) throws Exception {
		log.info("writeComplete");

	}

}
