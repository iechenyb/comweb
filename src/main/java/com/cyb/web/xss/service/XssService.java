package com.cyb.web.xss.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.cyb.web.base.service.HibernateBaseService;
import com.cyb.web.xss.dao.XssDao;
import com.cyb.web.xss.po.Customer;

@Service("xssService")
public class XssService extends HibernateBaseService<Customer>{
	Log log = LogFactory.getLog(XssService.class);
	@Resource(name="xssDao")
	XssDao xssDao;
	
	public List<Customer> getList(){
		return xssDao.list();
	}
	public List<?> query(String name ,String type){
		if("jdbc".equals(type)){
			return xssDao.getList(name);
		}else if("hibernate".equals(type)){
			return xssDao.getListHiber(name);
		}else if("hsql1".equals(type)){
			return xssDao.getListHiberSQL1(name);
		}else if("hsql2".equals(type)){
			return xssDao.getListHiberSQL2(name);
		}else{
			return new ArrayList();
		}
	}
}
