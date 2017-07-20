package com.cyb.web.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cyb.web.sw.dao.SwDao;
@Component
public class LzstoneTimeTask implements Job{
	public static long count = 0;
	@Autowired
	SwDao swDao;
    public void execute(JobExecutionContext context) throws JobExecutionException{
    	Thread.currentThread().setName(context.getTrigger().getFullName());
    	System.out.println("定时器使用springbean:"+swDao.list());
    	//执行的定时器任务
    	System.out.println(Thread.currentThread().getName()+",hello "+count++);
    }
}