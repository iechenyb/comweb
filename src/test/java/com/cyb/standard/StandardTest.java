package com.cyb.standard;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.UUIDUtils;
import com.cyb.base.JunitBase;
import com.cyb.date.DateUtil;
import com.cyb.web.model.po.Model;
import com.cyb.web.model.po.Model2;
import com.cyb.web.model.service.Model2Service;
import com.cyb.web.model.service.ModelService;
/**
 *作者 : iechenyb<br>
 *类描述: 标准的单元测试<br>
 *创建时间: 2017年7月19日
 */
@Transactional
public class StandardTest extends JunitBase {
	Log log = LogFactory.getLog(StandardTest.class);
	ModelService service;
	
	Model2Service service2;
	
	@Before
	public void init() {
		service = (ModelService) this.ac.getBean("modelService");
		service2 = (Model2Service) this.ac.getBean("model2Service");
	}
	@org.junit.Test
	public void start(){
		
	}
	@org.junit.Test
	public void save(){
		Model model = new Model();
		model.setCzsj(DateUtil.date2long14(new Date()).toString());
		service.save(model);
		
		Model model1 = new Model();//有策略，不设置id
		String uuid = UUIDUtils.getUUID();
		model1.setId(uuid);//有策略，设置id
		System.out.println("手动设置id:"+uuid);
		model1.setCzsj(DateUtil.date2long14(new Date()).toString());
		service.save(model1);
		
		Model2 model3 = new Model2();//没有策略，不设置id
		String uuid3 = UUIDUtils.getUUID();
		System.out.println("手动设置id:"+uuid3);
		model3.setId(uuid3);
		model3.setCzsj(DateUtil.date2long14(new Date()).toString());
		service2.save(model3);//报错
		
		Model2 model4 = new Model2();//没有策略，设置id
		String uuid2 = UUIDUtils.getUUID();
		model4.setId(uuid2);
		System.out.println("手动设置id:"+uuid2);
		model4.setCzsj(DateUtil.date2long14(new Date()).toString());
		service2.save(model4);
	}
	@After
	public void show(){
		System.out.println("数据展示1");
		List<Model> list = (List<Model>) service.getList();
		for(Model model:list){
			System.out.println(model.getId()+","+model.getCzsj());
		}
		System.out.println("数据展示2");
		List<Model2> list2 = (List<Model2>) service2.getList();
		for(Model2 model:list2){
			System.out.println(model.getId()+","+model.getCzsj());
		}
		
	}
}
