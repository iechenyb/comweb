package com.cyb.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cyb.h2.H2Manager;
import com.cyb.web.constant.Contants;
import com.cyb.web.search.utils.LuceneMemIndexIK;
import com.cyb.web.search.utils.LuceneMemIndexStandard;
import com.cyb.web.utils.AppConfiguration;
import com.cyb.web.utils.BaseConfiguration;
import com.cyb.web.utils.Configuration;
import com.cyb.web.utils.SpringUtils;
/**
 * 
 * 功能描述：
 * 作者：iechenyb
 * 创建时间：2016年11月8日下午4:03:46
 */
public class WebListener implements ServletContextListener {
    Log log = LogFactory.getLog(WebListener.class);
    public WebListener() {
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	Contants.WEBPATH = sce.getServletContext().getRealPath("/");
    	try {
    		WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
    		Contants.springContext = springContext;
    		H2Manager.start();
			BaseConfiguration.initConfig("base");
			AppConfiguration.initConfig("App-"+BaseConfiguration.get("environmental"));
			Configuration.initConfig("config-"+BaseConfiguration.get("environmental"));
			LuceneMemIndexIK.createIndex();
			LuceneMemIndexStandard.createIndex();
			H2Manager.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	try {
			H2Manager.stop();
			H2Manager.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	try {
    		log.info("正在销毁测试定时任务！");
			Scheduler testJob = (Scheduler) SpringUtils.getBean("jobTest");
			if(testJob!=null){
				if(testJob.isStarted()){
					testJob.shutdown();
				}else{
					testJob = null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
}
