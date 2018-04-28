package com.cyb.web.sw.service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.web.base.service.HibernateBaseService;
import com.cyb.web.sw.po.Sw;

/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年3月6日
 */
@Service
public class BusinessServiceImplA extends HibernateBaseService<Sw>{
	Log log = LogFactory.getLog(BusinessServiceImplA.class);
	@Autowired
	BusinessServiceImplB serviceB;
	@SuppressWarnings("unused")
	@Transactional(propagation = Propagation.REQUIRED, 
	isolation = Isolation.DEFAULT, rollbackFor = Exception.class)  
	public String doA(){
		 Sw sw = new Sw();
		 sw.setId("1");
		 sw.setCardNo("0000");
		 sw.setMoney(100);
		 //根据不同的事务传播机制，b方法进行不同的事务处理
		 serviceB.doB();
		 int i=1/0;
		 return "success";
	}
	 /**
	  * 参考：http://blog.csdn.net/hsgao_water/article/details/52860380
	  * 1 当b的传播机制为ROPAGATION_REQUIRES_NEW时，a成功回滚，b成功提交。
	  * 2 当b的传播机制为REQUIRED时，a和b事务都成功回滚！
	  * 3 当b的传播机制为NOT_SUPPORTED时，a成功回滚，b非事务方式运行成功提交！ 
	  */
}
