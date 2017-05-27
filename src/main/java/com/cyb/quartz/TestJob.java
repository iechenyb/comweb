package com.cyb.quartz;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cyb.web.sw.service.SwService;
//目录不对，不执行 
@Component
public class TestJob {
	Log log = LogFactory.getLog(TestJob.class);
	@Resource(name = "swService")
	SwService swService;

	@Scheduled(cron = "0/5 * * * * ?")
	public void method1() {
		log.info("xxx");
	}
	
	/*@Scheduled(cron = "0 * * * * ?")
	public void method2() {
      com();
	}
	
	public void com(){
		 Callable<Integer> callable = new Callable<Integer>() {
	            public Integer call() throws Exception {
	                int num = new Random().nextInt(100);
	                log.info(num+"-thread");
	                Thread.currentThread().setName(num+"-thread");
	                return num;
	            }
	        };
	        FutureTask<Integer> future = new FutureTask<Integer>(callable);
	        new Thread(future).start();
	        try {
	        	log.info("测试定时任务执行！使用springbean servece is " + swService);
	            Thread.sleep(15000);// 可能做一些事情
	            log.info("测试定时任务执行"+future.get());
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } catch (ExecutionException e) {
	            e.printStackTrace();
	        }
	}*/
	
}
