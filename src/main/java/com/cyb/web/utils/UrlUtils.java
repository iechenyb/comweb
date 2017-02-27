package com.cyb.web.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
public class UrlUtils {
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年12月9日下午5:00:18</br>
	 */
	public static boolean downloadFromUrl(String url,File toFile) {  
        try {  
            URL httpurl = new URL(url);  
            FileUtils.copyURLToFile(httpurl, toFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }   
        return true;  
    }  
	public static InputStream getStream(String address){
		InputStream is = null;
		try {
			URL url = new URL(address);
			HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
			is = urlcon.getInputStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return is;
	}
	public static String getFileNameFromUrl(String url){  
	    String name = new Long(System.currentTimeMillis()).toString() + ".X";  
	    int index = url.lastIndexOf("/");  
	    if(index > 0){  
	        name = url.substring(index + 1);  
	        if(name.trim().length()>0){  
	            return name;  
	        }  
	    }  
	    return name;  
	}
	public static String getData(String add){
		String str; 
        try { 
        	URL url = new URL(add); 
            InputStream is = url.openStream(); 
            InputStreamReader isr = new InputStreamReader(is); 
            BufferedReader br = new BufferedReader(isr); 
            str = br.readLine();
            br.close(); 
            return str;
        } catch(IOException e) { 
            System.out.println(e); 
            return "{}";
        } 
	}
}
