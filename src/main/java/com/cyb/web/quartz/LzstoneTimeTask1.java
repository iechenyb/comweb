package com.cyb.web.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class LzstoneTimeTask1 implements Job{
	public static long count = 0;
    public void execute(JobExecutionContext context) throws JobExecutionException{
    	Thread.currentThread().setName(context.getJobDetail().getFullName());
           //执行的定时器任务
    	System.out.println(Thread.currentThread().getName()+",hello1 "+count++);
    }
}