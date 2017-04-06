package com.cyb.web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileUtils {
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月2日下午3:14:35</br>
	 */
	/* 
	 * Java文件操作 获取文件扩展名 
	 * 
	 */  
	    public static String getExtensionName(String filename) {   
	        if ((filename != null) && (filename.length() > 0)) {   
	            int dot = filename.lastIndexOf('.');   
	            if ((dot >-1) && (dot < (filename.length() - 1))) {   
	                return filename.substring(dot + 1);   
	            }else{
	            	return "";
	            }   
	        }else{
	        	return "";
	        }   
	    }   
	/* 
	 * Java文件操作 获取不带扩展名的文件名 
	 * 
	 */  
	    public static String getFileNameNoEx(String filename) {   
	        if ((filename != null) && (filename.length() > 0)) {   
	            int dot = filename.lastIndexOf('.');   
	            if ((dot >-1) && (dot < (filename.length()))) {   
	                return filename.substring(0, dot);   
	            }   
	        }   
	        return filename;   
	    }  
	    
	    public static String resetName(String oringinName,String designName) {  
	        return  designName+"."+getExtensionName(oringinName);
	    }  
	    
	    public static void main(String[] args) {
			System.out.println(getExtensionName("xx.jpg"));
		}
	    
		public static void copyFile(File source, File target) {  
	        InputStream fis = null;  
	        OutputStream fos = null;  
	        try {  
	            fis = new FileInputStream(source);  
	            fos = new FileOutputStream(target);  
	            byte[] buf = new byte[4096];  
	            int i;  
	            while ((i = fis.read(buf)) != -1) {  
	                fos.write(buf, 0, i);  
	            }  
	        }  
	        catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	           try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	           
	        }  
	    }
		@SuppressWarnings("resource")
		public static void overide(String content,String path){
	    	 try {
				 FileChannel fc = new FileOutputStream(path).getChannel();  
				 fc.write(ByteBuffer.wrap(content.getBytes()));  
				 fc.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}  
	    }
		public static String getAbsolutePathAtMavenClass(Class<?> clss){
	    	String packagePath = System.getProperty("user.dir");
	    	String packageName = clss.getPackage().getName().replaceAll("\\.", "/");
	    	return packagePath+"/src/main/java/"+packageName+"/";
	    }
		public static String getAbsolutePathAtClass(Class<?> clss){
	    	String packagePath = System.getProperty("user.dir");
	    	String packageName = clss.getPackage().getName().replaceAll("\\.", "/");
	    	return packagePath+"/src/"+packageName+"/";
	    }
}
