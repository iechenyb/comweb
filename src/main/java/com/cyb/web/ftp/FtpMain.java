package com.cyb.web.ftp;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.htmlparser.filters.LinkStringFilter;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年4月28日
 */
public class FtpMain {
	Log log = LogFactory.getLog(FtpMain.class);
	static String ftpConfig = "d://data//ftp//ftp.properties";
	public static void main(String[] args) {
		try {
			FtpUtils.initConfig(ftpConfig);
			FTPClient client = FtpUtils.getFtpClient();
			list(client);
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	public static void list(FTPClient client) throws IOException{
		//client.changeWorkingDirectory("/app");
		FTPFile[] files = client.listFiles();
		if(files!=null&&files.length>0){
			for(int i=0;i<files.length;i++){
				System.out.println(files[i]);
			}
		}
	}
}
