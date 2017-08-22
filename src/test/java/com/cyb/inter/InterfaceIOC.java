package com.cyb.inter;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.cyb.base.BaseUtils;
import com.cyb.web.inter.po.Car;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月22日
 */
import com.cyb.web.inter.service.ICarService;
public class InterfaceIOC extends BaseUtils {
	Log log = LogFactory.getLog(InterfaceIOC.class);
	
	@Resource(name="bmwCarServiceImpl")
	ICarService<Car> service;
	
	@Test
	public void test(){
		service.run();
	}
}
