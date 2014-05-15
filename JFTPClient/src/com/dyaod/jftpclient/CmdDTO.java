package com.dyaod.jftpclient;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

public class CmdDTO {

	private static Logger log = Logger.getLogger(CmdDTO.class);
	
	public final static String CLIENT_MONITOR_START = "c_m_start";// client => monitor
																	// 表示开始要传送文件,后面跟文件信息,以及一个任务号(目前先用时间戳表示)
	public final static String CLIENT_MONITOR_SUSPEND = "c_m_suspend"; // client => monitor 表示中止传输文件
	public final static String CLIENT_MONITOR_COMPLETE = "c_m_complete";// client => monitor 表示传输完成
	public final static String CLIENT_MONITOR_CONTINUE = "c_m_continue";// client => monitor 用于传输中断后重新开始运行使用(pending)

	public final static String MONITOR_SERVER_START = "m_s_start";// monitor => server
	public final static String MONITOR_SERVER_CONTINUE = "m_s_continue"; // monitor => server 用于在接收到
																			// client的continue命令后通知server

	public final static String SERVER_MONITOR_COMPLETE = "s_m_complete"; // server => monitor 报告文件传输完成
																			// ,后面加参数,表示是正常完成还是异常完成,例如文件完整性校验失败
	public final static String SERVER_CLIENT_COMPLETE = "s_c_complete"; // server => client server向client报告传输完成可以退出了

	public final static String CLIENT_SERVER_FILE = "c_s_file"; // client => server 用于client向server传输文件,当且仅当为该类型时
																// content有值

	public CmdDTO() {
	}

	private String cmd; // 命令的动作,即上面那些静态变量
	private String[] params; // 命令的参数,具体的内容根据param不同而变化
	private byte[] content;// 文件内容

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	private final static int CMD_HEADER_LENGTH = 2;
	private final static int CONTENT_HEADER_LENGTH = 5;
    private final static String SPLIT = ",";
	
	/**
	 * @名称:
	 * @作者: wang.jianhua
	 * @创建于: 2014年5月14日
	 * @return
	 * @throws UnsupportedEncodingException
	 * @描述: 传输格式 $(几段)__X(CMD+PARAMS)XXX..XX________XXX....XXXXX 转换成的内容格式如下: 1) cmdStrLen:2个字节 内容是2个数字(每个数字最大127) 用来表示
	 *      下面 cmdStrBytes 内容 的长度 2) cmdStrBytes: n个字节,n由 cmdStrLen 决定, 内容是 cmd,param1,param2,param3...... 3)
	 *      contentLen: 5个字节,内容是5个数字(每个数字最大127),用来表示后面的contentBytes长度 4) contentBytes: 文件内容,最大容量是32MB,大于32MB的文件则按照32MB分片
	 */
	public ChannelBuffer toChannelBuffers() throws UnsupportedEncodingException {

		String cmdStr = cmd;

		for (String param : this.params) {
			cmdStr += (SPLIT + param);
		}

		byte[] cmdStrBytes = cmdStr.getBytes(Config.CHARSET);

		byte[] cmdStrLen = Utils.encodeLength(cmdStrBytes.length, CMD_HEADER_LENGTH);

		ChannelBuffer channelBuffer = null;

		// 当为传输文件命令时,content不为空
		if (CLIENT_SERVER_FILE.equals(this.getCmd())) {

			byte[] contentLen = Utils.encodeLength(content.length, CONTENT_HEADER_LENGTH);

			channelBuffer = ChannelBuffers.buffer(cmdStrLen.length + cmdStrBytes.length + contentLen.length
					+ content.length);

			channelBuffer.writeBytes(cmdStrLen);
			channelBuffer.writeBytes(cmdStrBytes);
			channelBuffer.writeBytes(contentLen);
			channelBuffer.writeBytes(content);

		}
		// 当不是传输文件命令时,content为空
		else {
			channelBuffer = ChannelBuffers.buffer(cmdStrLen.length + cmdStrBytes.length);
			channelBuffer.writeBytes(cmdStrLen);
			channelBuffer.writeBytes(cmdStrBytes);
		}
		return channelBuffer;
	}

	/**
	 * @名称:
	 * @作者: wang.jianhua
	 * @创建于: 2014年5月14日
	 * @param channelBuffer
	 * @return
	 * @描述:将 ChannelBuffer 解析转换回 cmdDTO
	 */
	public static CmdDTO toCmdDTO(ChannelBuffer channelBuffer) {
		
		try {
			CmdDTO cmdDTO = new CmdDTO();
			
			channelBuffer.resetReaderIndex();

			// 读取 cmd+params的内容的长度
			int cmdStrLen = Utils.decodeLength(channelBuffer.readBytes(CMD_HEADER_LENGTH).array());

			String cmdStr = new String(channelBuffer.readBytes(cmdStrLen).array(), Config.CHARSET);
			
			String[] cmdArray = cmdStr.split(SPLIT);
			
			cmdDTO.cmd = cmdArray[0];
			
			cmdDTO.params = Arrays.copyOfRange(cmdArray, 1, cmdArray.length);

			if(CLIENT_SERVER_FILE.equals(cmdDTO.cmd)){
				int contentLen = Utils.decodeLength(channelBuffer.readBytes(CONTENT_HEADER_LENGTH).array());
				cmdDTO.content = channelBuffer.readBytes(contentLen).array();
			}

			return cmdDTO;
		} catch (UnsupportedEncodingException e) {
			log.error("[toCmdDTO]: 转码失败" , e);
		}

		return null;
	}

}
