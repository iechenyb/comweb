package com.cyb.standard;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cyb.UUIDUtils;
import com.cyb.date.DateUtil;
import com.cyb.web.model.po.Model;
import com.cyb.web.model.service.ModelService;
/**
 *作者 : iechenyb<br>
 *类描述: sping单元测试<br>
 *创建时间: 2017年7月19日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "classpath*:applicationContext.xml",  
					  "classpath*:applicationContext-job.xml" })
//@Transactional 已经通过配置文件控制事务，这里就不用再注明事务了。
public class SpringStandardTest  {
	Log log = LogFactory.getLog(SpringStandardTest.class);
	@Autowired
	ModelService service;
	@Before
	public void init() {
		System.out.println("初始化..."+service);
	}
	@org.junit.Test
	@Rollback(true) 
	public void save(){
		Model model = new Model();
		model.setCzsj(DateUtil.date2long14(new Date()).toString());
		service.save(model);
		
		Model model1 = new Model();
		String uuid = UUIDUtils.getUUID();
		model1.setId(uuid);
		System.out.println("手动设置id:"+uuid);
		model1.setCzsj(DateUtil.date2long14(new Date()).toString());
		service.save(model1);
	}
	@After
	public void show(){
		System.out.println("数据展示");
		List<Model> list = service.getList();
		for(Model model:list){
			System.out.println(model.getId()+","+model.getCzsj());
		}
	}
}
