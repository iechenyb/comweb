package com.cyb.web.hibernate.service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月20日
 */

import com.cyb.web.base.dao.HibernateBaseDao3;
import com.cyb.web.base.po.BasePo;
import com.cyb.web.hibernate.dao.ExtendDao;
@Service
public class ExtendService extends HibernateBaseDao3<BasePo> {
	Log log = LogFactory.getLog(ExtendService.class);
	@Autowired
	ExtendDao dao;
}
