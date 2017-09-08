package com.cyb.web.model.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cyb.web.base.dao.HibernateBaseDao;
import com.cyb.web.model.po.ChildPoExtend;

/**
 * 作者 : iechenyb
 * 功能描述: 说点啥
 * 创建时间: 2017年07月16日 13时03分26秒
 */
@Repository("childPo2Dao")
public class ChildPo2Dao extends HibernateBaseDao<ChildPoExtend>{
    /**
	 * 作者 : iechenyb
	 * 功能描述: 说点啥
	 * 创建时间: 2017年07月16日 13时03分26秒
	 */
    public List<ChildPoExtend> getList(){
       @SuppressWarnings("unchecked")
       List<ChildPoExtend> list = this.getSession().createQuery("from ChildPo2").list();
	   return list;
   }
}