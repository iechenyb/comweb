package com.cyb.web.model.dao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.cyb.web.base.dao.HibernateBaseDao;
import com.cyb.web.model.po.MyBigDecimal;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月16日
 */
@Repository
public class BigDecimalDao extends HibernateBaseDao<MyBigDecimal>{
	Log log = LogFactory.getLog(BigDecimalDao.class);
	
}
