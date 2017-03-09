package com.cyb.web.quartz;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cyb.web.sw.service.SwService;

@Component
public class TestJob {
	Log log = LogFactory.getLog(TestJob.class);
	@Resource(name = "swService")
	SwService swService;

	@Scheduled(cron = "00 59 * * * ?")
	public void method() {
		log.info("测试定时任务执行！使用springbean servece is " + swService);
	}
}
