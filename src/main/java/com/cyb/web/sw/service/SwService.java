package com.cyb.web.sw.service;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.cyb.web.base.service.HibernateBaseService;
import com.cyb.web.sw.dao.SwDao;
import com.cyb.web.sw.po.Sw;

@Service("swService")
public class SwService extends HibernateBaseService<Sw>{
	Log log = LogFactory.getLog(SwService.class);
	@Resource(name="swDao")
	SwDao swDao;
	@SuppressWarnings("unused")
	public void updateSw(String ex){
		Sw sw1= swDao.list().get(0);
		Sw sw2= swDao.list().get(1);
		log.info("更新前:"+swDao.list().toString());
		sw1.setMoney(sw1.getMoney()-100);
		sw2.setMoney(sw2.getMoney()+100);
		swDao.update(sw1);
		if("throw".equals(ex)){
			int a=1/0;
		}
		swDao.update(sw2);
		log.info("更新后1-100，2+100:"+swDao.list().toString());
	}
	public List<Sw> getList(){
		return swDao.list();
	  }
}
