package com.cyb.web.model.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cyb.web.base.dao.HibernateBaseDao;
import com.cyb.web.model.po.Model;
import com.cyb.web.model.po.Model2;

/**
 * 作者 : iechenyb
 * 功能描述: 说点啥
 * 创建时间: 2017年07月16日 13时03分26秒
 */
@Repository("modelDao2")
public class Model2Dao extends HibernateBaseDao<Model2>{
    /**
	 * 作者 : iechenyb
	 * 功能描述: 说点啥
	 * 创建时间: 2017年07月16日 13时03分26秒
	 */
    public List<Model2> getList(){
       @SuppressWarnings("unchecked")
       List<Model2> list = this.getSession().createQuery("from Model2").list();
	   return list;
   }
}