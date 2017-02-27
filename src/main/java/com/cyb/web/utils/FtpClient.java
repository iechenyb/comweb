package com.cyb.web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * 
 * 功能描述：Ftp上传文件客户端
 * 作者：iechenyb
 * 创建时间：2016年11月8日下午4:16:00
 */
public class FtpClient {
	static Log log = LogFactory.getLog(FTPClient.class);
	/** 
	 * Description: 向FTP服务器上传文件 
	 * @param url FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param path FTP服务器保存目录 
	 * @param filename 上传到FTP服务器上的文件名 
	 * @param input 输入流 
	 * @return 成功返回true，否则返回false 
	 */  
	public static boolean uploadFile(String remoteDir, String filename, InputStream input) {  
		    boolean result = false;  
		    FTPClient ftp = new FTPClient();  
		    try {  
		    	int reply;  
		        //如果采用默认端口21，可以使用ftp.connect(url)的方式直接连接FTP服务器  
		          ftp.connect(Configuration.get("ip"), Integer.valueOf(Configuration.get("port")));
		        try {
		        	  ftp.connect(Configuration.get("ip"), Integer.valueOf(Configuration.get("port")));
		        } catch (UnknownHostException ex) {
		        	   throw new IOException("Can't find FTP server '" + Configuration.get("ip") + "'");
		        }
		        // 登录服务器
		        //ftp.login(username, password);
		        if (!ftp.login(Configuration.get("username"), Configuration.get("password"))) {
		        	throw new IOException("Can't login to server '" + Configuration.get("ip") + "'");
		        }
		        
		        reply = ftp.getReplyCode(); 
		       // 判断返回码是否合法
		        if (!FTPReply.isPositiveCompletion(reply)) {  
		        	// 不合法时断开连接
		        	ftp.disconnect();  
		        	 // 结束程序
		        	return result;  
		        }
		        // 设置文件操作目录
		       /* if(ftp.changeWorkingDirectory(path)){
		        	log.info("成功进入以创建目录$:" + path);
		        }else{
			        String[] pathList = path.split("/");
			        String temp = "";
			        for(String p : pathList){
			        	if(!p.equals("")){
			        		temp += p + "/";
			        		if(!ftp.changeWorkingDirectory(p)){
				        		boolean res = ftp.makeDirectory(p);
				        		if(!res){
				        			log.info("FTP目录创建失败："+temp);
				        		}else{
				        			log.info("FTP目录创建成功："+temp);
				        		}
				        	}else{
				        		log.info("成功进入目录:" + temp);
				        	}
			        	}
			        }//end for
			        log.info("准备进入目录："+path);
			        if(ftp.changeWorkingDirectory(path)){
			        	log.info("成功进入目录$:" + path);
			        }else{
			        	for(String p : pathList){
			        		ftp.changeWorkingDirectory(p);
			        	}
			        	 if(ftp.changeWorkingDirectory(path)){
			        		 log.info("成功进入目录*:" + path);
			        	 }
			        }
		        }  */
		        remoteDir = Configuration.get("ftpServerRootDir")+remoteDir;//上传的ftp目录上加一个根目录，默认是/
		        if(mkDir(ftp, remoteDir)){
		        	log.info("创建目录成功!"+remoteDir);
		        }
		        if(hasDirectory(ftp,remoteDir)){
		        	log.info("成功进入工作目录!"+remoteDir);
		        }
		        ftp.enterLocalPassiveMode();//被动模式
		        //上传文件
		        ftp.setFileType(FTPClient.BINARY_FILE_TYPE); 
		        log.info("文件名"+filename);
		        filename = new String(filename.getBytes("utf-8"),"iso-8859-1");// 转换后的目录名或文件名。
		        if (!ftp.storeFile(filename, input)) {
		        	throw new IOException("Can't upload file '" + filename + "' to FTP server. Check FTP permissions and path.");
		         }
		        input.close();  
		        ftp.logout();  
		        result = true; 
		    } catch (Exception e) { 
		    	result = false;
		        e.printStackTrace();  
		    } finally {  
		        if (ftp.isConnected()) {  
		            try {  
		                ftp.disconnect();  
		            } catch (IOException ioe) {  
		            }  
		        }  
		    }  
		return result;  
	}
	
	public static boolean uploadFile(String remoteDir,File localFile){
		InputStream input;
		try {
			input = new FileInputStream(localFile);
			return FtpClient.uploadFile(remoteDir, localFile.getName(), input);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static boolean uploadFile(String remoteDir,File localFile,String fileName){
		InputStream input;
		try {
			input = new FileInputStream(localFile);
			return  FtpClient.uploadFile(remoteDir, fileName, input);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static boolean uploadFile(String remoteDir,String localFile){
		InputStream input;
		try {
			File file = new File(localFile);
			input = new FileInputStream(file);
			return FtpClient.uploadFile(remoteDir, file.getName(), input);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static boolean uploadFile(String remoteDir,String localFile,String fileName){
		InputStream input;
		try {
			File file = new File(localFile);
			input = new FileInputStream(file);
			return FtpClient.uploadFile(remoteDir, fileName, input);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static boolean hasDirectory(FTPClient ftp,String dir){
		boolean result = false;
	    try {  
	        if(ftp.changeWorkingDirectory(dir)){
	        	log.info("目录已经存在:" + dir);
	        }
	    } catch (Exception e) {
			e.printStackTrace();
		}
	    return result;
	}
	public static boolean mkDir(FTPClient ftp,String dir){
		boolean result = false;
		try{
	        String[] dirs = dir.split("/");
	        String showPath = "";
	        for(int i=0;i<dirs.length;i++){
	        	showPath = showPath +dirs[i]+"/";
	        	if(ftp.changeWorkingDirectory(dirs[i])){
		        	log.info("成功进入目录$:" + showPath);
		        }else{
		        	if(ftp.makeDirectory(dirs[i])){
		        		log.info("成功创建目录"+showPath);
		        		ftp.changeWorkingDirectory(dirs[i]);
		        	}
		        }
	        }
	        if(ftp.changeWorkingDirectory(dir)){
	        	log.info("成功进入目录$:" + dir);
	        }
	    } catch (Exception e) {
			e.printStackTrace();
		}
	    return result;
	}
	

	 /**
	 * 删除文件
	 * @param hostname FTP服务器地址
	 * @param port FTP服务器端口号
	 * @param username FTP登录帐号
	 * @param password FTP登录密码
	 * @param pathname FTP服务器保存目录
	 * @param filename 要删除的文件名称
	 * @return
	 */
	 public static boolean deleteFile(String password, String pathname, String filename){
		 boolean flag = false;
		 FTPClient ftpClient = new FTPClient();
		 try {
			//连接FTP服务器
			 ftpClient.connect(Configuration.get("ip"), Integer.valueOf(Configuration.get("port")));
			 //登录FTP服务器
			 ftpClient.login(Configuration.get("username"), Configuration.get("password"));
			 //验证FTP服务器是否登录成功
			 int replyCode = ftpClient.getReplyCode();
			 if(!FTPReply.isPositiveCompletion(replyCode)){
				 return flag;
			 }
			 //切换FTP目录
			 ftpClient.changeWorkingDirectory(pathname);
			 ftpClient.dele(filename);
			 ftpClient.logout();
			 flag = true;
		 } catch (Exception e) {
			 e.printStackTrace();
		 } finally{
			 if(ftpClient.isConnected()){
				 try {
					 ftpClient.logout();
				 } catch (IOException e) {
				  
				 }
			 	}
		}
	 return flag;
	}
	  
	 /**
	 * 下载文件
	 * @param hostname FTP服务器地址
	 * @param port FTP服务器端口号
	 * @param username FTP登录帐号
	 * @param password FTP登录密码
	 * @param pathname FTP服务器文件目录
	 * @param filename 文件名称
	 * @param localpath 下载后的文件路径
	 * @return
	 */
	 public static boolean downloadFile(String pathname, String filename, String localpath){
	 boolean flag = false;
	 FTPClient ftpClient = new FTPClient();
	 try {
		 //连接FTP服务器
		 ftpClient.connect(Configuration.get("ip"), Integer.valueOf(Configuration.get("port")));
		 //登录FTP服务器
		 ftpClient.login(Configuration.get("username"), Configuration.get("password"));
		 //验证FTP服务器是否登录成功
		 int replyCode = ftpClient.getReplyCode();
		 if(!FTPReply.isPositiveCompletion(replyCode)){
			 return flag;
		 }
		 pathname = Configuration.get("ftpServerRootDir")+pathname;
		 //切换FTP目录
		 ftpClient.changeWorkingDirectory(pathname);
		 FTPFile[] ftpFiles = ftpClient.listFiles();
		 for(FTPFile file : ftpFiles){
			 if(filename.equalsIgnoreCase(file.getName())){
				 File localFile = new File(localpath + "/" + file.getName());
				 OutputStream os = new FileOutputStream(localFile);
				 ftpClient.retrieveFile(file.getName(), os);
				 os.close();
			 }
		 }
		 ftpClient.logout();
		 flag = true;
	 } catch (Exception e) {
		 e.printStackTrace();
	 } finally{
		 if(ftpClient.isConnected()){
			 try {
				 ftpClient.logout();
			 } catch (IOException e) {
			  
			 }
		 }
	 }
	 return flag;	 
	}

	public static void main(String[] args) {
		try {
			
			 testConnection();
			 // Configuration.initConfig("config");
			 //FtpClient.uploadFile("qhweb/image/xxx/www", new File("d://test//中文.txt"));
			 //FtpClient.uploadFile("qhweb/video/yyy/zzz", "d://test//中文.jpg");
			 /*String hostname = "192.168.61.90";
			 int port = 21;
			 String username = "ftpuser";
			 String password = "cesfftpuser999";
			 String pathname = "qhweb/xzzx/file/"; 
			 String filename = "24cd002108ab4ffc87cbfc4cf147e601.sql"; */
			 //uploadFileFromProduction(hostname, port, username, password, pathname, filename, originfilename);
			 // String localpath = "D:/";
			 //downloadFile(pathname, filename, localpath);
			 //deleteFile(password, pathname, filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void testConnection(){
		String ip = "192.168.108.239";
		int port=21;
		String name = "administrator";
		String password="cesfkiiik";
		 FTPClient ftp = new FTPClient();  
		    try {  
		    	int reply;  
		        //如果采用默认端口21，可以使用ftp.connect(url)的方式直接连接FTP服务器  
		          ftp.connect(ip, port);
		        try {
		        	 ftp.connect(ip, port);
		        } catch (UnknownHostException ex) {
		        	   throw new IOException("Can't find FTP server '" + ip + "'");
		        }
		        // 登录服务器
		        //ftp.login(username, password);
		        if (!ftp.login(name,password)) {
		        	throw new IOException("Can't login to server '" + ip + "'");
		        }
		        
		        reply = ftp.getReplyCode(); 
		       // 判断返回码是否合法
		        if (!FTPReply.isPositiveCompletion(reply)) {  
		        	// 不合法时断开连接
		        	ftp.disconnect();  
		        	 // 结束程序
		        }
		        System.out.println("连接成功！");
	          }catch (Exception e){
	        	  e.printStackTrace();
	          }
	 }
}
