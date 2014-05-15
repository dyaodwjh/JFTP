package com.dyaod.jftpclient;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

public class JFtpClientForMonitor {

	private Logger log = Logger.getLogger(JFtpClientForMonitor.class);
	private String host;
	private int monitorPort;
	private ClientBootstrap clientToMonitor;
   
	
	public JFtpClientForMonitor(String host, int port) {

		log.info(String.format("[JFtpClientForMonitor] 构造函数  host:%s  port:%d", host, port));

		this.host = host;
		this.monitorPort = port;
		this.clientToMonitor = new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool()));

		clientToMonitor.setPipelineFactory(new ChannelPipelineFactory() {

			@Override
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new JFtpClientForMonitorHandler());
			}
		});
	}
	
	public boolean connectMonitor(){
		log.info("[connectMonitor]");
		clientToMonitor.connect(new InetSocketAddress(host, monitorPort));
		return true;
	}
	
	
	public boolean stop(){
		log.info("[stop]");
		clientToMonitor.shutdown();
		return true;
	}

}
