package com.cyb.web.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OutPutObject {

	public static void printStr(HttpServletResponse response, String message) {
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			out.print(message);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void printjson(HttpServletResponse response, String message) {
		response.setContentType("text/html;charset=utf-8");
		try {
			JSONObject json = JSONObject.fromObject(message);
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void printJson(HttpServletResponse response, Map objMap) {
		response.setContentType("text/html;charset=utf-8");
		try {
			JSONArray json = JSONArray.fromObject(objMap);
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void printJson(HttpServletResponse response, List objList) {
		response.setContentType("text/html;charset=utf-8");
		try {
			JSONArray jsonArray = JSONArray.fromObject(objList);
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void printJsonObject(HttpServletResponse response, JSONObject object) {
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			out.print(object);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String string2Json(String s) {      
        StringBuffer sb = new StringBuffer ();      
        for (int i=0; i<s.length();i++){
            char c = s.charAt(i);      
            switch (c) {      
            case '\"':      
                sb.append("\\\"");      
                break;      
            case '\\':      
                sb.append("\\\\");      
                break;      
            case '/':      
                sb.append("\\/");      
                break;      
            case '\b':      
                sb.append("\\b");      
                break;      
            case '\f':      
                sb.append("\\f");      
                break;      
            case '\n':      
                sb.append("\\n");      
                break;      
            case '\r':      
                sb.append("\\r");      
                break;      
            case '\t':      
                sb.append("\\t");      
                break;      
            default:      
                sb.append(c);      
            } 
        }
        return sb.toString();
     }   
	public static void mainTest(String[] args) {
		try {
			Socket server = new Socket(InetAddress.getLocalHost(), 5678);
			BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
			PrintWriter out = new PrintWriter(server.getOutputStream());
			// 标准输入
			BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				String str = wt.readLine();
				out.println(str);
				out.flush();
				if ("end".equals(str)) {
					break;
				}
				System.out.println(in.readLine());
			}
			server.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
//		try {
//			SocketUtil socketUtil=new SocketUtil();
//			String host = InetAddress.getLocalHost().toString();
//			System.out.println("host:"+host);
//			Socket socket = socketUtil.getConnect("192.168.16.96", 10000);
//			socketUtil.sendReq(socket, "test socket");
//
//			while (true) {
//				String str = socketUtil.getSocketData(socket);
//				System.out.println(str);
//			}
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		try   
        {  
			Socket socket = new Socket("127.0.0.1", 10000);  
              
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);  
            BufferedReader line = new BufferedReader(new InputStreamReader(System.in));  
  
            out.println(line.readLine());  
              
            line.close();  
            out.close();  
            in.close();  
              
            socket.close();  
              
        } catch (IOException e) {  
        }  
		
	}
}
