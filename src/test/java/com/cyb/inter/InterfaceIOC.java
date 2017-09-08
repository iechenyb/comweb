package com.cyb.inter;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.cyb.base.SpringJunitBase;
import com.cyb.web.abstr.service.ChildAbstractServiceImpl;
import com.cyb.web.inter.po.Car;
import com.cyb.web.inter.service.ICarService;

public class InterfaceIOC extends SpringJunitBase{
	Log log = LogFactory.getLog(InterfaceIOC.class);
	
	@Resource(name="bmwCarServiceImpl")
	ICarService<Car> service;
	
	@Resource(name="childAbstractServiceImpl")
	ChildAbstractServiceImpl service2;
	//测试接口注入
	@Test
	public void testInter(){
		service.run();
	}
	@Test
	public void testAbstract(){
		service2.show("iechenyb");
	}
	
}
