package com.cyb.hibernate;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cyb.base.SpringJunitBase;
import com.cyb.date.DateUtil;
import com.cyb.web.model.dao.ModelDao;
import com.cyb.web.model.po.ChildPo;
import com.cyb.web.model.po.ChildPoExtend;
import com.cyb.web.model.po.Model;
import com.cyb.web.model.service.ChildPo2Service;
import com.cyb.web.model.service.ChildPoService;
import com.cyb.web.model.service.ModelService;
import com.cyb.web.sw.dao.SwDao;
import com.cyb.web.sw.service.SwService;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月19日
 */
public class BaseQuery extends SpringJunitBase{
	Log log = LogFactory.getLog(BaseQuery.class);
	
	@Autowired
	ModelService service;
	
	@Autowired
	ModelService servicem;
	
	@Autowired
	private SwService service1;
	
	@Autowired
	ChildPoService childService;
	
	@Autowired
	ChildPo2Service childService2;
	
	@Autowired
	ModelDao dao;
	
	@Autowired
	private SwDao dao1;
	
	@org.junit.Test
	public void findAllWithPoName(){
		System.out.println(service1.clazz+"默认根据id排序，po名称查询所有记录！"+service.clazz);
		show((List<Model>)service.list("Model"));
		show((List<Model>)service.list(Model.class));//error
	}
	
	@org.junit.Test
	public void findAllNoPoName(){
		show((List<Model>)service.list(Model.class));
		
		//System.out.println("默认根据id排序，po泛型名称查询所有记录！");
		show((List<Model>)service.getListOrderById());//测试service层泛型
		show((List<Model>)service.getAll());//测试service层泛型
		show((List<Model>)service.list());//测试dao层泛型
	}
	
	@org.junit.Test
	public void findAllUseChildPo(){
	    ChildPo child = new ChildPo();
	    child.setChildName("chenyb");
	    child.setCzsj(DateUtil.date2long14(new Date()).toString());
	    childService.save(child);
	    
	    ChildPoExtend child2 = new ChildPoExtend();
	    child2.setChildName("iechenyb");
	    child2.setCzsj(DateUtil.date2long14(new Date()).toString());
	    childService2.save(child2);
	   /* @SuppressWarnings("rawtypes")
		BasePo child3 = new ChildPo2();
	    child3.setChildName("chenyuanbao");
	    child3.setCzsj(DateUtil.date2long14(new Date()).toString());
	    childService2.save(child3);*/
	    
		List<ChildPo> list = childService.list(ChildPo.class);
		for(ChildPo po:list){
			System.out.println(po.getId()+","+po.getChildName());
		}
		//---------------------------------------------------------
		List<ChildPoExtend> list2 = childService2.list(ChildPoExtend.class);
		for(ChildPoExtend po:list2){
			System.out.println(po.getId()+","+po.getChildName());
		}
	}
	@org.junit.Test
	public void getFanxing(){
		System.out.println(dao1.clazz+",dao,"+dao.clazz);
		System.out.println(service.clazz+",service,"+servicem.clazz);
	}
	
}
