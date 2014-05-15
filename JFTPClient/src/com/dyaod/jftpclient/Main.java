package com.dyaod.jftpclient;

import org.apache.log4j.Logger;

public class Main {

	 private static Logger log = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		String argMsg = " ";
		
		for(String arg : args){
			argMsg += (arg +" ");
		}
		
		log.info("main =>"+ argMsg);
		
		if (args.length != 3) {
			System.exit(1);
		}

		String hostTemp = args[0];
		String portTemp = args[1];
		String filePathTemp = args[2];// 需要传输的文件,可以为文件夹或者文件,如果是文件夹的话则会递归传输

		log.info("\n\n=========================初始化 JFtpClient============================================================\n");
		JFtpClient client = new JFtpClient(hostTemp, Integer.valueOf(portTemp), filePathTemp);
		log.info("\n\n=========================扫描并分析文件================================================================\n");
		client.scanFiles(); //扫描并分析文件
		log.info("\n\n=========================连接monitor服务器============================================================\n");
		client.connectMonitor();//连接monitor服务器
		
		


	}

}
