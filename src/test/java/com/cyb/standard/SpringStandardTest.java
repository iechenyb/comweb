package com.cyb.standard;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cyb.UUIDUtils;
import com.cyb.date.DateUtil;
import com.cyb.web.model.dao.BigDecimalDao;
import com.cyb.web.model.po.Model;
import com.cyb.web.model.po.ModelExtend;
import com.cyb.web.model.po.MyBigDecimal;
import com.cyb.web.model.service.BigDecimalService;
import com.cyb.web.model.service.Model2Service;
import com.cyb.web.model.service.ModelService;
/**
 *作者 : iechenyb<br>
 *类描述: sping单元测试<br>
 *创建时间: 2017年7月19日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "classpath*:applicationContext.xml",  
					  "classpath*:applicationContext-redis.xml",  
					  "classpath*:applicationContext-job.xml"  })
//@Transactional 已经通过配置文件控制事务，这里就不用再注明事务了。
public class SpringStandardTest  {
	Log log = LogFactory.getLog(SpringStandardTest.class);
	@Autowired
	ModelService service;
	@Autowired
	Model2Service service2;
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
		
		ModelExtend model3 = new ModelExtend();//没有策略，不设置id
		String uuid3 = UUIDUtils.getUUID();
		System.out.println("手动设置id:"+uuid3);
		model3.setId(uuid3);
		model3.setCzsj(DateUtil.date2long14(new Date()).toString());
		service2.save(model3);//报错
		
		ModelExtend model4 = new ModelExtend();//没有策略，设置id
		String uuid2 = UUIDUtils.getUUID();
		model4.setId(uuid2);
		System.out.println("手动设置id:"+uuid2);
		model4.setCzsj(DateUtil.date2long14(new Date()).toString());
		service2.save(model4);
		
		
	}
	@After
	public void show(){
		System.out.println("数据展示");
		List<Model> list = service.getList();
		for(Model model:list){
			System.out.println(model.getId()+","+model.getCzsj());
		}
		System.out.println("数据展示2");
		List<ModelExtend> list2 = (List<ModelExtend>) service2.getList();
		for(ModelExtend model:list2){
			System.out.println(model.getId()+","+model.getCzsj());
		}
	}
	@Autowired
	BigDecimalService bigService;
	@Test
	public void testBigDecimal(){
		String value = "20.123456789";
		MyBigDecimal bd = new MyBigDecimal();
		BigDecimal val = new BigDecimal(value);
		bd.setXiaoShu(val);
		bd.setId(1);
		bigService.delete(bd);
		bigService.save(bd);
		bigService.get("1");
	}
}
