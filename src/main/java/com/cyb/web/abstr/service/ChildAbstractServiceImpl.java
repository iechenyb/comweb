package com.cyb.web.abstr.service;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.cyb.web.abstr.dao.AbstractImpl;
/**
 *作者 : iechenyb<br>
 *类描述: 抽象类注入测试<br>
 *创建时间: 2017年8月23日
 */
@Service("childAbstractServiceImpl")
public class ChildAbstractServiceImpl{
	
	Log log = LogFactory.getLog(ChildAbstractServiceImpl.class);
	
	@Resource(name="childAbstractDaoImpl")
	private AbstractImpl dao;
     
	public void show(String content){
		dao.print(content);
		dao.println(content);
		dao.printAny(content);
	}
}
