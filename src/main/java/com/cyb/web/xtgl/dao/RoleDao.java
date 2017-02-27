package com.cyb.web.xtgl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cyb.web.base.dao.HibernateBaseDao;
import com.cyb.web.xtgl.po.Role;

@Repository("roleDao")
public class RoleDao extends HibernateBaseDao<Object>{
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月2日上午9:09:37</br>
	 */
	
	@SuppressWarnings("unchecked")
	public List<Role> getRoles(){
		Object obj = this.getSession().createQuery("from Role ").setCacheable(true).list();
		if(obj!=null){
			return (List<Role>)obj;
		}else{
			return null;
		}
	}
}
