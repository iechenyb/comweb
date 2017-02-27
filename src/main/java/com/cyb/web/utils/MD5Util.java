package com.cyb.web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

public class MD5Util {
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年8月18日上午10:58:32</br>
	 */

	/*** 
     * MD5加密 生成32位md5码
     * @param 待加密字符串
     * @return 返回32位md5码
     */
    public static String md5Encode(String inStr)  {
        MessageDigest md5 = null;
        try {  
        	StringBuffer hexValue = new StringBuffer();
            md5 = MessageDigest.getInstance("MD5");
            byte[] byteArray = inStr.getBytes("UTF-8");
	        byte[] md5Bytes = md5.digest(byteArray);
	        for (int i = 0; i < md5Bytes.length; i++) {
	            int val = ((int) md5Bytes[i]) & 0xff;
	            if (val < 16) {
	                hexValue.append("0");
	            }
	            hexValue.append(Integer.toHexString(val));
	        }
	        return hexValue.toString();
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }       
    }

    /**
     * 测试主函数
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        String str = new String("0");
        System.out.println("原始：" + str);
        System.out.println("MD5后：" + md5Encode(str));
        System.out.println("MD5后：" + md5Encode(str));
        System.out.println("MD5后：" + md5Encode(str));
        System.out.println("MD5后：" + md5Encode(str));
        System.out.println("MD5后：" + md5Encode(str));
    }
    public static String getFileMD5Hex(InputStream inputStream){
		 try {
			return DigestUtils.md5Hex(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return StringUtils.EMPTY;
		} catch (IOException e) {
			e.printStackTrace();
			return StringUtils.EMPTY;
		}
	 }
	 public static String getFileMD5Hex(String path){
		 try {
			return DigestUtils.md5Hex(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return StringUtils.EMPTY;
		} catch (IOException e) {
			e.printStackTrace();
			return StringUtils.EMPTY;
		}
	 }
    public static String getFileMD5Hex(File file){
    	try {
			return DigestUtils.md5Hex(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return StringUtils.EMPTY;
		} catch (IOException e) {
			e.printStackTrace();
			return StringUtils.EMPTY;
		}
	 }
}
