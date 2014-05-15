package com.dyaod.jftpmonitor;

public class Main {

	public static void main(String[] args) {

		if (args.length == 2) {

			String portTemp = args[1];

			port = Integer.valueOf(portTemp);
		}

		JFtpMonitorForClient monitor = new JFtpMonitorForClient(port);
		monitor.start();

	}
	
}
