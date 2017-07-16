package com.cyb.web.model.dao;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cyb.collection.CollectionFactory;
import com.cyb.web.base.dao.HibernateBaseDao;

/**
 * 作者 : iechenyb
 * 功能描述: 说点啥
 * 创建时间: 2017年07月16日 13时03分26秒
 */
@Repository("modelDao")
public class ModelDao extends HibernateBaseDao<Object>{
    /**
	 * 作者 : iechenyb
	 * 功能描述: 说点啥
	 * 创建时间: 2017年07月16日 13时03分26秒
	 */
    public List<?> getList(){
       CollectionFactory.build(20);
       List<?> list = CollectionFactory.getList();
	   return list;
   }
    public static void main(String[] args) {
		
	}
}