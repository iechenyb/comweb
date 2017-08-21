package com.cyb.web.hibernate.dao;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.cyb.web.base.dao.HibernateBaseDao;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月20日
 */
@Repository
public class ExtendDao extends HibernateBaseDao<Object>{
	Log log = LogFactory.getLog(ExtendDao.class);
	public List testHql(String hql){
		return this.getSession().createQuery(hql).list();
	}
}
