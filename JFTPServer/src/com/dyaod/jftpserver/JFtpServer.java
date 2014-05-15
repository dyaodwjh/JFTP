package com.dyaod.jftpserver;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;


/**
 * @作者: wang.jianhua
 * @创建于: 2014年5月12日
 * @概述: JFtpServer的server服务类
 */
public class JFtpServer {
	private Logger log = Logger.getLogger(JFtpServer.class);
	private int port;
	private ServerBootstrap server;
	
	
	public JFtpServer(int port){
		log.info(String.format("[JFtpServer] 构造函数 port:%d",  port));
		 this.port = port;
		
		 this.server = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(),Executors.newCachedThreadPool()));
		 
		 this.server.setPipelineFactory(new ChannelPipelineFactory() {
			
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				
				return Channels.pipeline(new JFtpServerHandler());
			}
		});
		 
	}
	
	
	public boolean start(){
		log.info("[start]");
		boolean result = false;
		try{
			server.bind(new InetSocketAddress(this.port));
		result = true;
		}catch(Exception e){
		   	
		}
		return result;
	}
	
	public boolean stop(){
		log.info("[stop]");
		server.shutdown();
		return true;
	}
}
