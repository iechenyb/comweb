package com.cyb.web.base.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
@Component("baseDao")
public class HibernateBaseDao<T> {
	@Resource(name="jdbcTemplate")
	public JdbcTemplate jdbcTemplate;
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年10月19日下午3:17:28</br>
	 */
	Log log = LogFactory.getLog(HibernateBaseDao.class);
	@Resource(name="sessionFactory")
	public SessionFactory sessionFactory;

	protected Class<T> clazz;

	/**
	 * 通过反射泛型获取Class类型,getGenericSuperclass()方法获取对象的泛型的父类类型信息，
	 * getActualTypeArguments()[0]方法得到T的真实类型
	 * 
	 */
	@SuppressWarnings("unchecked")
	public HibernateBaseDao() {
		Type t = getClass().getGenericSuperclass();
        if(t instanceof ParameterizedType){
            Type[] p = ((ParameterizedType)t).getActualTypeArguments();
            clazz = (Class<T>)p[0];
        }
	}
	
	public Session getSession(){
			Session session = null ;
			try {
				session = sessionFactory.getCurrentSession();
				if(session==null){
					session = sessionFactory.openSession();
				}
			} catch (Exception e) {
				return sessionFactory.openSession();
			}
			return session;
	}
	
	public void close(Session session){
		if(session!=null){
			session.flush();
			session.close();
		}
	}
	public void save(T t){
		 this.getSession().save(t);
		 log.debug("保存成功！");
	}
	  
	 public void delete(T t){
		 this.getSession().delete(t);
		 log.debug("删除成功！");
	 }
	 
	 public void update(T t){
		 this.getSession().update(t);
		 log.debug("更新成功！");
	 }
	 
	@SuppressWarnings("unchecked")
	public T get(Class<?> clazz,String id) {
		T entity = null;
		try {
			entity = (T) this.getSession().get(clazz, id);
		} catch (HibernateException sqle) {
			entity = null;
			log.error(sqle.toString());
		}
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public T load(Class<?> clazz,String id) {
		T entity = null;
		try {
			entity = (T) this.getSession().load(clazz, id);
		} catch (HibernateException sqle) {
			entity = null;
			log.error(sqle.toString());
		}
		return entity;
	 }
	public void evict(Object t){
	   this.getSession().merge(t);
	   this.getSession().evict(t);
	}
}
