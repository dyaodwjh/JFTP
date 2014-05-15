package com.dyaod.jftpserver;


public class Server {

	private  static int PORT = 8121;
	
	public static void main(String[] args) {
    
		String portTemp = args[0];
		
		if(portTemp != null && portTemp.trim().length()!=0 ){
			PORT = Integer.valueOf(portTemp);
		}
		
		JFtpServer jftpServer = new JFtpServer(PORT);
		jftpServer.start();
		
	}

}
