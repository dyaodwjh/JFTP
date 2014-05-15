package com.dyaod.jftpclient;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.log4j.Logger;

public class JFtpClientBusiness {

	private Logger log = Logger.getLogger(JFtpClientBusiness.class);
	private Path basePath;
    private FilesContainer fileContainer;
	 
	
	public JFtpClientBusiness(String baseFileUri) {
		log.info("JFtpClientBusiness => 构造函数");
		this.basePath = Paths.get(baseFileUri);
	}
	
	/**
	 * @作者: wang.jianhua
	 * @创建于: 2014年5月15日
	 * @描述: 扫描并分析文件信息
	 */
	public void scanFiles(){
		log.info("scanFiles => "+ this.basePath.toString());
		fileContainer = new FilesContainer(this.basePath); //初始化文件信息
		fileContainer.scan(); //扫描并分析文件信息
	}
	

}
