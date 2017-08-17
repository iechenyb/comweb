package com.cyb.web.model.service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyb.web.base.service.HibernateBaseService;
import com.cyb.web.model.dao.BigDecimalDao;
import com.cyb.web.model.po.MyBigDecimal;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月16日
 */
@Service
public class BigDecimalService extends HibernateBaseService<MyBigDecimal>{
	Log log = LogFactory.getLog(BigDecimalService.class);
	@Autowired
	BigDecimalDao dao;
}
