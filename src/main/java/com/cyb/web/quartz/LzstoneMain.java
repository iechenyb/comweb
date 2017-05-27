package com.cyb.web.quartz;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
/**
 * 如何传参数  如何进行分组，分组的作用是什么？
 */
public class LzstoneMain {
	 private static Scheduler sched;
     public static void run() throws Exception{
            //创建LzstoneTimeTask的定时任务
            @SuppressWarnings("static-access")
			JobDetail jobDetail = new JobDetail("job","mygroup",LzstoneTimeTask.class);
            //目标 创建任务计划
            CronTrigger trigger = new CronTrigger("trigger","lzstone","*/1 * * * * ?");
           
            //@SuppressWarnings("static-access")
			//JobDetail jobDetail1 = new JobDetail("job1","mygroup1",LzstoneTimeTask1.class);
            //0 0 12 * * ? 代表每天的中午12点触发S
            //CronTrigger trigger1 = new CronTrigger("trigger1","lzstone","*/4 * * * * ?");
            
            //CronTrigger trigger2 = new CronTrigger("trigger1","lzstone","*/4 * * * * ?");
            
            sched = new org.quartz.impl.StdSchedulerFactory().getScheduler();
            sched.scheduleJob(jobDetail,trigger);
            //sched.scheduleJob(jobDetail1,trigger1);
            //sched.scheduleJob(jobDetail1,trigger2);
            sched.start();
            while(true){
            	if(LzstoneTimeTask.count>5){
    				LzstoneMain.stop();
    				System.out.println("定时任务结束");
    				break;
    			}
            }
     }
     //停止
     public static void stop() throws Exception{
            sched.shutdown();
      }
     
     public static void main(String[] args) {
		try {
			LzstoneMain.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
//执行
