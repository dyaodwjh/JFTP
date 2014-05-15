package com.dyaod.jftpclient;

import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.WriteCompletionEvent;

public class JFtpClientForMonitorHandler extends SimpleChannelHandler{

	Logger log = Logger.getLogger(this.getClass());
	private JFtpClientBusiness jFtpClientBusiness;
	
	public JFtpClientForMonitorHandler(){
	}
	
	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		log.info("JFtpClientForMonitorHandler => channelOpen");
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		log.info("JFtpClientForMonitorHandler => channelConnected");
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		log.info("JFtpClientForMonitorHandler => channelDisconnected");
	}

	@Override
	public void channelUnbound(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		log.info("JFtpClientForMonitorHandler => channelUnbound");
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		log.info("JFtpClientForMonitorHandler => channelClosed");
	}
	
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		log.info("JFtpClientForMonitorHandler => messageReceived");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		Err.exit(log, Err.MONITOR_ERROR,e.getCause(), "JFtpClientForMonitorHandler => exceptionCaught");
		

	}

	@Override
	public void writeComplete(ChannelHandlerContext ctx, WriteCompletionEvent e) throws Exception {
		log.info("writeComplete");

	}

	@Override
	public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		// TODO 自动生成方法存根
		super.writeRequested(ctx, e);
	}

	private ChannelBuffer tranStr2Buffer(String str){
		byte[] msg = str.getBytes();
		ChannelBuffer buffer = ChannelBuffers.buffer(msg.length);
		buffer.writeBytes(msg);
		return buffer;
	}

}
