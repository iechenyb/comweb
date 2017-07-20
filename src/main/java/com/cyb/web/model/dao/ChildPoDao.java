package com.cyb.web.model.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cyb.web.base.dao.HibernateBaseDao;
import com.cyb.web.model.po.ChildPo;

/**
 * 作者 : iechenyb
 * 功能描述: 说点啥
 * 创建时间: 2017年07月16日 13时03分26秒
 */
@Repository("childPoDao")
public class ChildPoDao extends HibernateBaseDao<ChildPo>{
    /**
	 * 作者 : iechenyb
	 * 功能描述: 说点啥
	 * 创建时间: 2017年07月16日 13时03分26秒
	 */
    public List<ChildPo> getList(){
       @SuppressWarnings("unchecked")
       List<ChildPo> list = this.getSession().createQuery("from ChildPo").list();
	   return list;
   }
}