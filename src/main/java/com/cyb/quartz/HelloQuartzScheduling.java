package com.cyb.quartz;


public class HelloQuartzScheduling {

    public static void main(String[] args) 
         {

        /*SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail jobDetail = new JobDetail("helloQuartzJob", 
                Scheduler.DEFAULT_GROUP, HelloQuartzJob.class);

        String cronExpression = "30/5 * * * * ?"; // 每分钟的30s起，每5s触发任务        
        CronTrigger cronTrigger = new CronTrigger("cronTrigger", 
                Scheduler.DEFAULT_GROUP, cronExpression);

        scheduler.scheduleJob(jobDetail, cronTrigger);

        scheduler.start();*/
    }

}
