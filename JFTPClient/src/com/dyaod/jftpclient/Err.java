package com.dyaod.jftpclient;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.ExceptionEvent;

/**
 * @作者: wang.jianhua
 * @创建于: 2014年5月15日
 * @概述: 用来记录错误代码
 */
public class Err {

	public final static int MONITOR_ERROR = -1;
	public final static int BASE_PATH_NOT_EXIT = -2;

	/**
	 * @作者: wang.jianhua
	 * @创建于: 2014年5月15日
	 * @param code
	 * @param errorMessage
	 * @描述: 用于快速的异常退出应用并显示错误信息
	 */
	public static void exit(Logger log, final int errorCode, String message, String... params) {

		String msg = "[ERR_CODE:" + errorCode + "]:" + String.format(message, params);

		try {
			throw new Exception(msg);
		} catch (Exception e) {
			log.error(msg, e);
			System.exit(errorCode);
		}
	}

	/**
	 * @作者: wang.jianhua
	 * @创建于: 2014年5月15日
	 * @param code
	 * @param errorMessage
	 * @描述: 用于快速的异常退出应用并显示错误信息
	 */
	public static void exit(Logger log, final int errorCode, Throwable err, String message, String... params) {

		String msg = "[ERR_CODE:" + errorCode + "]:" + String.format(message, params);
		log.error(msg, err);
		System.exit(errorCode);
	}

}
