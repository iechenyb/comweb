package com.cyb.web.base.service;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cyb.web.base.dao.HibernateBaseDao3;


@Component("baseService3")
public class HibernateBaseService3<BasePo> {
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年10月19日下午3:33:32</br>
	 */
	@Resource(name="baseDao3")
	HibernateBaseDao3<BasePo> baseDao;
	
	public Class<BasePo> clazz;
	
	@SuppressWarnings("unchecked")
	public HibernateBaseService3() {
		Type t = getClass().getGenericSuperclass();
        if(t instanceof ParameterizedType){
            Type[] p = ((ParameterizedType)t).getActualTypeArguments();
            clazz = (Class<BasePo>)p[0];
            System.out.println("泛型类型dao："+clazz);
        }
	}
	
	
	/**
	 * 
	 * @作者:iechenyb
	 * @功能描述：持久化一个实体类
	 * @创建时间：2016年12月27日下午3:07:33
	 */
	public void save(BasePo t){
		baseDao.save(t);
	}
	
	public void update(BasePo t){
		baseDao.update(t);
	}
	
	public void delete(BasePo t){
		baseDao.delete(t);
	}
	
	public List<BasePo> list(){
		return baseDao.list();
	}
	public BasePo get(String id){
		return baseDao.get(clazz, id);
	}
	public List<BasePo> getAll(){
		return baseDao.getAll();
	}
	public List<BasePo> getAll(Class<?> cls){
		return baseDao.getAll(cls);
	}
	public List<BasePo> getAll(String entityName){
		return baseDao.getAll(entityName);
	}
	public BasePo load(String id){
		return baseDao.load(clazz, id);
	}
	public void evict(Object t){
		this.baseDao.evict(t);
	}
}
