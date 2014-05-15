package com.dyaod.jftpmonitor;

import java.net.InetSocketAddress;

import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

/**
 * 
 * @作者: wang.jianhua
 * @创建于: 2014年5月14日
 * @概述:
 */
public class JFtpMonitorForClient {
	private Logger log = Logger.getLogger(JFtpMonitorForClient.class);
	private int port;
	private ServerBootstrap monitor;
	
	public JFtpMonitorForClient(int port){
		log.info(String.format("[JFtpMonitor] 构造函数 port:%d", port));
		
		this.port = port;
		this.monitor = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(),Executors.newCachedThreadPool()));
		
		monitor.setPipelineFactory(new ChannelPipelineFactory() {
			
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new JFtpMonitorForClientHandler());
			}
		});
		
	}
	
	public boolean start(){
		log.info("[start]");
		boolean result = false;
		try{
			monitor.bind(new InetSocketAddress(this.port));
		result = true;
		}catch(Exception e){
		   	
		}
		return result;
	}
	
}
