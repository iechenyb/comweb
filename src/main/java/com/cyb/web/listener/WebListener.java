package com.cyb.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cyb.web.constant.Contants;
import com.cyb.web.utils.Configuration;
/**
 * 
 * 功能描述：
 * 作者：iechenyb
 * 创建时间：2016年11月8日下午4:03:46
 */
public class WebListener implements ServletContextListener {

    public WebListener() {
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	Contants.WEBPATH = sce.getServletContext().getRealPath("/");
    	try {
			Configuration.initConfig("config");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    }
	
}
