package com.cyb.web.xss.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.cyb.web.base.dao.HibernateBaseDao;
import com.cyb.web.xss.po.Customer;
import com.cyb.web.xtgl.po.Menu;

@Repository("xssDao")
public class XssDao extends HibernateBaseDao<Customer> {
  public List<Customer> getList(){
	return this.getAll();
  }
  //拼接sql，不安全
  public List<Map<String,Object>> getList(String name){
	  String sql = "select * from t_sys_customer where name='"+name+"'";
	  List<Map<String,Object>> list = this.jdbcTemplate.queryForList(sql);
	  if(CollectionUtils.isEmpty(list)){
		   return new ArrayList<Map<String,Object>>();
	  }else{
		  return list;
	  }
  }
    @SuppressWarnings("unchecked")
	public List<Customer> getListHiber(String name){
		  Object obj = this.getSession().createQuery("from Customer where name=? ").setString(0, name).setCacheable(true).list();
			if(obj!=null){
				return (List<Customer>)obj;
			}else{
				return new ArrayList<Customer>() ;
			}
	}
    @SuppressWarnings("unchecked")
   	public List<Customer> getListHiberSQL1(String name){
    	 String sql = "select * from t_sys_customer where name='"+name+"'";
   		 Object obj = this.getSession().createSQLQuery(sql).setCacheable(true).list();
   			if(obj!=null){
   				return (List<Customer>)obj;
   			}else{
   				return new ArrayList<Customer>() ;
   			}
   	}
    @SuppressWarnings("unchecked")
   	public List<Customer> getListHiberSQL2(String name){
    	String sql = "select * from t_sys_customer where name= ?";
   		Object obj = this.getSession().createSQLQuery(sql).setString(0, name).setCacheable(true).list();
		if(obj!=null){
			return (List<Customer>)obj;
		}else{
			return new ArrayList<Customer>() ;
		}
   	}
}
