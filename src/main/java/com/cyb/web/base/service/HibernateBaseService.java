package com.cyb.web.base.service;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cyb.web.base.dao.HibernateBaseDao;
import com.cyb.web.model.po.Model;


@Component("baseService")
public class HibernateBaseService<T> {
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年10月19日下午3:33:32</br>
	 */
	@Resource(name="baseDao")
	HibernateBaseDao<T> baseDao;
	protected Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public HibernateBaseService() {
        Type t = getClass().getGenericSuperclass();
        if(t instanceof ParameterizedType){
            Type[] p = ((ParameterizedType)t).getActualTypeArguments();
            clazz = (Class<T>)p[0];
        }
	}
	/**
	 * 
	 * @作者:iechenyb
	 * @功能描述：持久化一个实体类
	 * @创建时间：2016年12月27日下午3:07:33
	 */
	public void save(T t){
		baseDao.save(t);
	}
	
	public void update(T t){
		baseDao.update(t);
	}
	
	public void delete(T t){
		baseDao.delete(t);
	}
	
	public List<T> list(){
		return baseDao.list();
	}
	public Object get(String id){
		return baseDao.get(clazz, id);
	}
	public Object getAll(){
		return baseDao.getAll();
	}
	public Object getAll(String entityName){
		return baseDao.getAll(entityName);
	}
	public Object load(String id){
		return baseDao.load(clazz, id);
	}
	public void evict(Object t){
		   this.baseDao.evict(t);
	}
}
