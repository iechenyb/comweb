package com.cyb.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 
 * 功能描述：后台管理系统登录人员信息记录
 * 作者：iechenyb
 * 创建时间：2016年12月13日上午10:51:30
 */
public class SessionListener implements HttpSessionListener {
	private Log log = LogFactory.getLog(SessionListener.class);
	private static int activeSessions =0;  
    public SessionListener() {
        
    }
	
    public void sessionCreated(HttpSessionEvent event)  { 
    	ServletContext ctx = event.getSession( ).getServletContext( );  
        Integer numSessions = (Integer) ctx.getAttribute("numSessions");  
        if (numSessions == null) {  
            numSessions = new Integer(1);  
        }  
        else {  
            int count = numSessions.intValue( );  
            numSessions = new Integer(count + 1);  
        }  
        ctx.setAttribute("numSessions", numSessions);  
        activeSessions ++;
        Object name = event.getSession( ).getAttribute("username");
        if(name==null){
        	name="游客";
        }
        log.info("用户["+name+"]进入系统,在线人数为"+activeSessions);
    }

	
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	 ServletContext ctx=se.getSession().getServletContext();  
    	 Integer numSessions = (Integer)ctx.getAttribute("numSessions");  
	     if(numSessions == null){ 
	        numSessions = new Integer(0);  
	     }else{  
            int count = numSessions.intValue( );  
            numSessions = new Integer(count - 1);  
	     }  
	     ctx.setAttribute("numSessions", numSessions);   
	     activeSessions --;
	     Object name = se.getSession().getAttribute("username");
	     if(name==null){
	        name="游客";
	     }
	     log.info("用户["+name+"]进入系统,在线人数为"+activeSessions);
    }
	
}
