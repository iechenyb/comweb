package com.cyb.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;
@Component
public class JobTest1 implements Job
{
    
    public void execute(JobExecutionContext jobexecutioncontext)
            throws JobExecutionException
    {
        System.out.println("JobTest1");
    }

}
