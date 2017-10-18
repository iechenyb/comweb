package com.cyb.base;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cyb.web.model.po.Model;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月19日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "classpath*:applicationContext.xml",  
					  "classpath*:applicationContext-job.xml" ,
					  "classpath*:applicationContext-mongodb.xml",
					  "classpath*:applicationContext-redis.xml" })
public class SpringJunitBase {
	Log log = LogFactory.getLog(SpringJunitBase.class);
	public void show(List<Model> list){
		for(Model model:list){
			System.out.println(model.getId()+","+model.getCzsj());
		}
	}
}
