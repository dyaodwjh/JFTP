package com.dyaod.jftpclient;

public class JFtpClient {

	private String host = "127.0.0.1";
	private int port = 8120;
	private String filePath;

	private JFtpClientForMonitor jFtpClientForMonitor;
	private JFtpClientBusiness jFtpClientBusiness;

	public JFtpClient(String host, int port, String filePath) {
		this.host = host;
		this.port = port;
		this.filePath = filePath;
		this.jFtpClientBusiness = new JFtpClientBusiness(filePath);//初始化业务类
		this.jFtpClientForMonitor = new JFtpClientForMonitor(host, port);
	}

	public void connectMonitor() {
		this.jFtpClientForMonitor.connectMonitor();
	}
	
	public void scanFiles(){
		this.jFtpClientBusiness.scanFiles();//开始扫描文件
	}

}
