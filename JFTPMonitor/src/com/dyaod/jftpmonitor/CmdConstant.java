package com.dyaod.jftpmonitor;

public class CmdConstant {

	public final static String CLIENT_MONITOR_START = "c_m_start";// client => monitor 表示开始要传送文件,后面跟文件信息,以及一个任务号(目前先用时间戳表示)
	public final static String CLIENT_MONITOR_SUSPEND = "c_m_suspend"; //client => monitor 表示中止传输文件
	public final static String CLIENT_MONITOR_COMPLETE = "c_m_complete";//client => monitor 表示传输完成
	public final static String CLIENT_MONITOR_CONTINUE = "c_m_continue";//client => monitor 用于传输中断后重新开始运行使用(pending)
	
	public final static String MONITOR_SERVER_START = "m_s_start";// monitor => server
	public final static String MONITOR_SERVER_CONTINUE = "m_s_continue"; // monitor => server 用于在接收到 client的continue命令后通知server
	
	public final static String SERVER_MONITOR_COMPLETE = "s_m_complete"; //server => monitor 报告文件传输完成 ,后面加参数,表示是正常完成还是异常完成,例如文件完整性校验失败
	public final static String SERVER_CLIENT_COMPLETE = "s_c_complete"; //server => client server向client报告传输完成可以退出了
	
}
