package com.cyb.web.ftp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.cyb.web.utils.Configuration;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年4月28日
 */
public class FtpUtils {
	static Log log = LogFactory.getLog(FtpUtils.class);
	static Properties p = null;

	public static void initConfig(String file) throws IOException {
		InputStream inputstream = null;
		if (p == null) {
			p = new Properties();
			log.info("初始化属性文件:" + file);
			inputstream = new FileInputStream(file);
			p.load(inputstream);
		}
	}

	public static FTPClient getFtpClient() {
		 FTPClient ftpClient = new FTPClient();
		 try {
			 //连接FTP服务器
			 ftpClient.connect(p.get("ftp.server.ip").toString(),
					 Integer.valueOf(p.get("ftp.server.port").toString()));
			 //登录FTP服务器
			 ftpClient.login(p.get("ftp.server.username").toString(),
					 p.get("ftp.server.password").toString());
			 //验证FTP服务器是否登录成功
			 ftpClient.getReplyCode();
			 return ftpClient;
		 }catch(Exception e){
			 return null;
		 }
	}
	
	//d://data//ftp//ftp.properties
}
