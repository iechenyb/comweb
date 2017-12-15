package com.cyb.web.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class MyRequestWrapper extends HttpServletRequestWrapper {
	Log log = LogFactory.getLog(LoginFilter.class);
	private byte[] body = null ; // 报文  
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月30日下午3:59:08</br>
	 */
	public MyRequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
		 try {
			body = readBytes(servletRequest.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	@Override  
    public BufferedReader getReader() throws IOException {  
        return new BufferedReader(new InputStreamReader(getInputStream()));  
    }  
	      
    @Override  
    public ServletInputStream getInputStream() throws IOException {  
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);  
        return new ServletInputStream() {  
            @Override  
            public int read() throws IOException {  
                return bais.read();  
            }

			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setReadListener(ReadListener arg0) {
				// TODO Auto-generated method stub
				
			}  
        };  
    }  
	public List<String> getAllParameterNameAndValues() {
		Enumeration<?> paraNames = null;
		StringBuffer queryStr=new StringBuffer("");//http查询字符串
		List<String> values = new ArrayList<String>();
		String value = "";
		String key = "";
		String orginValue = "";
		paraNames = super.getParameterNames();
		if(paraNames!=null&&!"".equals(paraNames)){//post或者条件为空
		 while(paraNames.hasMoreElements()){			    	
		    	key= (String) paraNames.nextElement();
		    	orginValue = super.getParameter(key);
		    	if(orginValue!=null&&!orginValue.equals("")){
		    		try {
						value = new String(orginValue.trim().getBytes("ISO-8859-1"),"UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
		    	}
		    	queryStr.append(key+":"+value+",");
		    	values.add(value);
		    	//清空临时变量
		    	key = "";
		    	value = "" ;
		    	orginValue = "";
		    }
		 }
		return values;
	}
	public String[] getParametersValueByName(String parameter) {
		String[] results = super.getParameterValues(parameter);
		if (results == null)
			return null;
		int count = results.length;
		String[] trimResults = new String[count];
		for (int i = 0; i < count; i++) {
			trimResults[i] = results[i].trim();
		}
		return trimResults;
	}
	public String getAjaxRequestParams() {		
		return new String(body);
	}
	
	public byte[] readBytes(InputStream inStream)throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[1024];
		int rc = 0;
		while ((rc = inStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		swapStream.close();
		return in2b;
	}
	public byte[] readBytesReader(InputStream in){
		BufferedReader bf;
		StringBuffer body = null;
		try {
			bf = new BufferedReader(new InputStreamReader(in, "utf-8"));
			body = new StringBuffer("");
	        String tmp = "";
			while ((tmp = bf.readLine()) != null) {
			    body.append(tmp);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		String data = body.toString().replace("null", "");
        byte[] in2b = data.getBytes();
        return in2b;
	}
	
}
