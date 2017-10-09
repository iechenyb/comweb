package com.cyb.web.model.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cyb.web.base.dao.HibernateBaseDao;
import com.cyb.web.model.po.ModelExtend;

/**
 * 作者 : iechenyb
 * 功能描述: 说点啥
 * 创建时间: 2017年07月16日 13时03分26秒
 */
@Repository("modelDao2")
public class Model2Dao extends HibernateBaseDao<ModelExtend>{
    /**
	 * 作者 : iechenyb
	 * 功能描述: 说点啥
	 * 创建时间: 2017年07月16日 13时03分26秒
	 */
    public List<ModelExtend> getList(){
       @SuppressWarnings("unchecked")
       List<ModelExtend> list = this.getSession().createQuery("from ModelExtend").list();
	   return list;
   }
}