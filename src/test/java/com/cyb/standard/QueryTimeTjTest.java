package com.cyb.standard;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cyb.web.model.po.Model;
import com.cyb.web.model.service.ModelService;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "classpath*:applicationContext.xml",  
					  "classpath*:applicationContext-redis.xml",  
					  "classpath*:applicationContext-job.xml"  })
public class QueryTimeTjTest {
	Log log = LogFactory.getLog(QueryTimeTjTest.class);
	@Autowired
	ModelService service;
	@Test
	public void testQuery(){
		long s = System.currentTimeMillis();
		for(int i=1;i<=10*10000;i++){
			service.getAll();
		}
		long e = System.currentTimeMillis();
		log.info("用时："+(e-s)/1000.0+"s");//22s
		
		s = System.currentTimeMillis();
		for(int i=1;i<=100*10000;i++){
			service.getAll();
		}
		e = System.currentTimeMillis();
		log.info("用时："+(e-s)/1000.0+"s");//182s
	}
	
	@Test
	public void testUpdate(){
		long s = System.currentTimeMillis();
		Model model = new Model();
		model.setCzymc("xxx");
		model.setId("1");
		service.save(model);
		for(int i=1;i<=10*10000;i++){
			service.update(model);
		}
		long e = System.currentTimeMillis();
		log.info("用时："+(e-s)/1000.0+"s");//22s
		
		s = System.currentTimeMillis();
		for(int i=1;i<=100*10000;i++){
			service.update(model);
		}
		e = System.currentTimeMillis();
		log.info("用时："+(e-s)/1000.0+"s");//182s
	}
}
