package com.cyb.web.sw.service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class BusinessServiceImplB extends HibernateBaseService<Sw>{
	Log log = LogFactory.getLog(BusinessServiceImplB.class);
	 @Transactional(propagation = Propagation.REQUIRES_NEW,
			 isolation=Isolation.DEFAULT,
			 rollbackFor=Exception.class)    
	public String doB(){
		 Sw sw = new Sw();
		 sw.setId("2");
		 sw.setCardNo("1111");
		 sw.setMoney(100);
		 return "success";
	}
	
}
