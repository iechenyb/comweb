package com.cyb.web.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EncodeUtils {
	static Log log = LogFactory.getLog(EncodeUtils.class);
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年12月1日下午1:18:11</br>
	 */
	//èµæç±»æµè¯
	public static String gbk2UTF8(String s){
		 try {
			return  new String(s.getBytes("gbk"),"utf8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return s;
		}    
	}
	public static String utf82GBK(String s){
		 try {
			return  new String(s.getBytes("utf8"),"gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return s;
		}    
	}
	public static String iso88592UTF8(String s){
		 try {
			return  new String(s.getBytes("ISO8859-1"),"utf8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return s;
		}    
	}
	public static String iso88592GBK(String s){
		 try {
			return  new String(s.getBytes("ISO8859-1"),"gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return s;
		}    
	}
	public static void checkEncod(String s){
		log.info("gbk2UTF8:"+gbk2UTF8(s));
		log.info("iso88592UTF8:"+iso88592UTF8(s));
		log.info("iso88592GBK:"+iso88592GBK(s));
		log.info("utf82GBK:"+utf82GBK(s));
	}
	public static void main(String[] args) {
		String s="æ±è¾°å¿";
		log.info(gbk2UTF8(s));
		log.info(iso88592UTF8(s));
		log.info(iso88592GBK(s));
		log.info(utf82GBK(s));
	}
}
