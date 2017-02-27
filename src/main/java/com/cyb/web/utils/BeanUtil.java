package com.cyb.web.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BeanUtil {
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月30日下午3:44:02</br>
	 */
	 @SuppressWarnings("unchecked")
	public static <T> T cloneTo(T src) throws RuntimeException {
	        ByteArrayOutputStream memoryBuffer = new ByteArrayOutputStream();
	        ObjectOutputStream out = null;
	        ObjectInputStream in = null;
	        T dist = null;
	        try {
	            out = new ObjectOutputStream(memoryBuffer);
	            out.writeObject(src);
	            out.flush();
	            in = new ObjectInputStream(new ByteArrayInputStream(memoryBuffer.toByteArray()));
	            dist = (T) in.readObject();
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        } finally {
	            if (out != null)
	                try {
	                    out.close();
	                    out = null;
	                } catch (IOException e) {
	                    throw new RuntimeException(e);
	                }
	            if (in != null)
	                try {
	                    in.close();
	                    in = null;
	                } catch (IOException e) {
	                    throw new RuntimeException(e);
	                }
	        }
	        return dist;
	    }
	 public static void main(String[] args) {
		
	}
}
