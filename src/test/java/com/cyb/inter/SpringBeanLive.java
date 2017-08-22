package com.cyb.inter;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cyb.web.beanlive.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"classpath*:applicationContext-beanlive.xml"  })
public class SpringBeanLive{
	Log log = LogFactory.getLog(SpringBeanLive.class);
	
	@Resource(name="person")
	Person p;
	//测试springbean生命周期
	@Test
	public void testSpringBean(){
		log.info(p);
	}
}
