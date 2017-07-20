package com.cyb.web.base.service;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cyb.web.base.dao.HibernateBaseDao;
@Component("baseService")
public class HibernateBaseService<T> {
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年10月19日下午3:33:32</br>
	 */
	@Resource(name="baseDao")
	HibernateBaseDao<T> baseDao;
	
	public Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public HibernateBaseService() {
        Type t = getClass().getGenericSuperclass();
        if(t instanceof ParameterizedType){
            Type[] p = ((ParameterizedType)t).getActualTypeArguments();
            clazz = (Class<T>)p[0];
            System.out.println("泛型类型service："+clazz);
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
	public T get(String id){
		return baseDao.get(clazz, id);
	}
	@SuppressWarnings("unchecked")
	public List<T> getListOrderById(){
		return (List<T>) baseDao.getSession().createCriteria(clazz).list();
	}
	@SuppressWarnings("unchecked")
	public List<T> getAll(){
		Object obj = baseDao.getSession()
				.createQuery("from "+clazz.getSimpleName()+" order by id desc")
				.setCacheable(true).list();
		if(obj!=null){
			return (List<T>)obj;
		}else{
			return new ArrayList<T>();
		}
	}
	public List<T> list(Class<T> cls){
		return baseDao.list(cls);
	}
	public List<T> list(String entityName){
		return baseDao.list(entityName);
	}
	public T load(String id){
		return baseDao.load(clazz, id);
	}
	public void evict(Object t){
		this.baseDao.evict(t);
	}
}
