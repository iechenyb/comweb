package com.cyb.web.model.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cyb.web.base.dao.GenericDao;
import com.cyb.web.base.dao.HibernateBaseDao;
import com.cyb.web.model.po.Model;

/**
 * 作者 : iechenyb
 * 功能描述: 说点啥
 * 创建时间: 2017年07月16日 13时03分26秒
 */
@Repository("modelDao")
public class ModelDao extends HibernateBaseDao<Model> implements GenericDao<Model>{
    /**
	 * 作者 : iechenyb
	 * 功能描述: 说点啥
	 * 创建时间: 2017年07月16日 13时03分26秒
	 */
    public List<Model> getList(){
       @SuppressWarnings("unchecked")
       List<Model> list = this.getSession().
       createQuery("from Model where id= ?").setCacheable(false).
       setString(0, "402890535d5e7239015d5e723ec20000").list();
	   return list;
   }
}