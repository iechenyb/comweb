package com.cyb.quartz;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class PriorityTest {
	public static void main(String[] args) throws Exception
    {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();

        JobDetail job1 = new JobDetail();
        job1.setName("jobDetail1");
        job1.setGroup("a");
        job1.setJobClass(JobTest1.class);

        JobDetail job2 = new JobDetail();
        job2.setName("jobDetail2");
        job2.setGroup("a");
        job2.setJobClass(JobTest2.class);

        JobDetail job3 = new JobDetail();
        job3.setName("jobDetail3");
        job3.setGroup("a");
        job3.setJobClass(JobTest3.class);
        Date date = new Date();
        Trigger trigger1 = new SimpleTrigger();
        trigger1.setName("trigger1");
        trigger1.setStartTime(date);
        //优先级默认是5
        trigger1.setPriority(2);
        
        Trigger trigger2 = new SimpleTrigger();
        trigger2.setName("trigger2");
        trigger2.setStartTime(date);
        trigger2.setPriority(1);
        
        Trigger trigger3 = new SimpleTrigger();
        trigger3.setName("trigger3");
        trigger3.setStartTime(date);
        trigger3.setPriority(5);
        
        
        scheduler.scheduleJob(job1, trigger1);
        scheduler.scheduleJob(job2, trigger2);
        scheduler.scheduleJob(job3, trigger3);

        scheduler.start();
    }
}
