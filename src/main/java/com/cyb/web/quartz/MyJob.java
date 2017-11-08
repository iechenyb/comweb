package com.cyb.web.quartz;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.stereotype.Component;

import com.cyb.web.search.controller.SearchController;
@Component
public class MyJob {  
	Log log = LogFactory.getLog(SearchController.class);
	@Resource(name="schedulerFactoryTest")
    private Scheduler scheduler; 
	
	@Resource(name="myQuartzJob") 
	private JobDetail myQuartzJob;
	
	@Resource(name="myTrigger") 
	private Trigger myTrigger;
	
    private static int step=0;  
   
    /** 
     * 定时调用此方法 
     * @throws Exception 
     */  
    public void doSomething() throws Exception {  
        //log.info("***********************"+step+"***********************");
    }  
    /** 
     * 重设定时器表达式 
     * @param cronExpression 
     * @throws Exception 
     */  
    public void resetJob(String cronExpression) throws Exception {  
    	CronTriggerBean trigger = (CronTriggerBean) myTrigger; 
    	String oldConExpression = trigger.getCronExpression();   
        if (!oldConExpression.equalsIgnoreCase(cronExpression)) {  
            trigger.setCronExpression(cronExpression);  
            scheduler.rescheduleJob("myTrigger", Scheduler.DEFAULT_GROUP, trigger);  
        }  
    }  
    
    public void removeJob() throws Exception {  
    	scheduler.deleteJob("myQuartzJob", Scheduler.DEFAULT_GROUP);
    }  
    
    public void addJob(String cronExpression) throws Exception{
    	scheduler.scheduleJob(myQuartzJob, myTrigger);
    	resetJob(cronExpression);
    }
    
    public void infor() throws SchedulerException{
    	System.out.println(scheduler.getSchedulerName());
    	String str[] =scheduler.getJobGroupNames();
    	for(int i=0;i<str.length;i++){
    		System.out.println(str[i]);
    		String str1[] = scheduler.getJobNames(str[i]);
    		for(int j=0;j<str1.length;j++){
    			System.out.println(str1[j]);
    		}
    	}
    }
}  