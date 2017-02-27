package com.cyb.web.utils;
 import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.web.constant.Contants;

public class Configuration {
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月8日下午4:01:39</br>
	 * */
	 	private static Properties p = null;
	 	static Log log = LogFactory.getLog(Configuration.class);
	 	public synchronized static void initConfig(String propertyName) throws Exception {
	 		InputStream inputstream = null;
	 		try {
	 			if (p == null) 
	 			{
	 				p = new Properties();
	 				String filePath = Contants.WEBPATH + "WEB-INF"+ File.separator +"classes"+ File.separator + propertyName + ".properties";
	 				log.info("初始化属性文件:"+filePath);
	 				inputstream = new FileInputStream(filePath);
	 				p.load(inputstream);
	 			}
	 		} catch (Exception e) {
	 			e.printStackTrace();
	 		}finally{
	 			if(inputstream!=null){
	 				inputstream.close();
	 				inputstream = null;
	 			}
	 		}
	 	}

	 	public static String get(String key) {
	 		String result = "";
	 		try {
	 			result = p.getProperty(key);
	 			return result;
	 		} catch (Exception e) {
	 			e.printStackTrace();
	 			return "";
	 		}
	 	}
}
