package com.dyaod.jftpclient;

import java.io.File;
import java.nio.file.Path;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

/**
 * @作者: wang.jianhua
 * @创建于: 2014年5月15日
 * @概述: 文件容器,用来存放文件信息
 */
public class FilesContainer {

	public Logger log = Logger.getLogger(FilesContainer.class);

	public final int LITTLE_LEVEL = 1 * 1024 * 1024;
	public final int NORMAL_LEVEL = 16 * 1024 * 102;

	public Queue<File> littleFilesQueue; // 小文件 ,小于等于 {@LITTLE_LEVEL} 的文件均被认为是小文件
	public Queue<File> normalFileQueue; // 普通文件,大于 {@LITTLE_LEVEL} 小于等于 {@NORMAL_LEVEL} 的文件均被认为是普通文件
	public Queue<File> bigFileQueue; // 大文件,大于 {@NORMAL_LEVEL} 的文件均被认为是大文件

	private Path basePath;

	/**
	 * @作者: wang.jianhua
	 * @创建于: 2014年5月15日
	 * @param basePath
	 * @描述: 初始化文件容器信息
	 */
	public FilesContainer(Path basePath) {
		log.info("JFtpClientBusiness => 构造函数 "+ basePath.toString());
		this.basePath = basePath;
		littleFilesQueue = new ConcurrentLinkedQueue<File>();
		normalFileQueue = new ConcurrentLinkedQueue<File>();
		bigFileQueue = new ConcurrentLinkedQueue<File>();
	}

	/**
	 * @作者: wang.jianhua
	 * @创建于: 2014年5月15日
	 * @描述: 扫描basePath路径信息,并且根据level分类放到不同的容器
	 */
	public void scan() {
    
		log.info("scan => "+ basePath.toString());
		File rootfile = basePath.toFile(); 
		// 判断文件是否存在,如果不存在则异常退出
		if (!rootfile.exists()) {
			Err.exit(log, Err.BASE_PATH_NOT_EXIT, "(%s)文件不存在!!!!!",basePath.toString());
		}
		if (rootfile.isDirectory()) { // 文件是文件夹
			addDirectory(basePath.toFile());
		} else { // 文件是一个单独文件
			addFile(basePath.toFile());
		}
	}

	/**
	 * @作者: wang.jianhua
	 * @创建于: 2014年5月15日
	 * @param path
	 * @描述: 添加文件夹,递归子文件夹
	 */
	private void addDirectory(File dir) {
		log.info("addDirectory=>" + dir.getAbsolutePath());
		File[] subFiles = dir.listFiles();
		for (File file : subFiles) {
			if (file.isDirectory()) {
				addDirectory(file);
			} else {
				addFile(file);
			}
		}
	}

	/**
	 * @作者: wang.jianhua
	 * @创建于: 2014年5月15日
	 * @param file
	 * @描述: 根据文件的大小将文件添加到指定queue队列中
	 */
	private void addFile(File file) {
		long fileLen = file.length();
		if (fileLen <= LITTLE_LEVEL) { // 小文件
			log.info("littleFileQueue => file:" + file.getAbsolutePath() + " fileSize:" + fileLen);
			littleFilesQueue.add(file);
		} else if (fileLen > LITTLE_LEVEL && fileLen <= NORMAL_LEVEL) { // 普通文件
			log.info("normalFileQueue => file:" + file.getAbsolutePath() + " fileSize:" + fileLen);
			normalFileQueue.add(file);
		} else { // 大文件
			log.info("bigFileQueue => file:" + file.getAbsolutePath() + " fileSize:" + fileLen);
			bigFileQueue.add(file);
		}
	}

}