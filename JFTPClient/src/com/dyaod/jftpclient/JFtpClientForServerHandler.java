package com.dyaod.jftpclient;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.WriteCompletionEvent;

public class JFtpClientForServerHandler extends SimpleChannelHandler {

	private Logger log = Logger.getLogger(this.getClass());
    
	public JFtpClientForServerHandler(){
	}
	
	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		log.info("JFtpClientForServerHandler => channelOpen");
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		log.info("JFtpClientForServerHandler => channelConnected");
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		log.info("JFtpClientForServerHandler => channelDisconnected");
	}

	@Override
	public void channelUnbound(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		log.info("JFtpClientForServerHandler => channelUnbound");
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		log.info("JFtpClientForServerHandler => channelClosed");
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		log.info("channelOpen => messageReceived");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		log.info("JFtpClientForServerHandler => exceptionCaught");

	}

	@Override
	public void writeComplete(ChannelHandlerContext ctx, WriteCompletionEvent e) throws Exception {
		log.info("JFtpClientForServerHandler => writeComplete");

	}
	

}
