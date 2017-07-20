package com.cyb.web.base.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cyb.web.base.po.Page;
@Component("baseDao3")
public class HibernateBaseDao3<BasePo>{
	
	@Resource(name="jdbcTemplate")
	public JdbcTemplate jdbcTemplate;
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年10月19日下午3:17:28</br>
	 */
	Log log = LogFactory.getLog(HibernateBaseDao3.class);
	
	@Resource(name="sessionFactory")
	public SessionFactory sessionFactory;

	public Class<BasePo> clazz;

	/**
	 * 通过反射泛型获取Class类型,getGenericSuperclass()方法获取对象的泛型的父类类型信息，
	 * getActualTypeArguments()[0]方法得到T的真实类型
	 * 
	 */
	@SuppressWarnings("unchecked")
	public HibernateBaseDao3() {
		Type t = getClass().getGenericSuperclass();
        if(t instanceof ParameterizedType){
            Type[] p = ((ParameterizedType)t).getActualTypeArguments();
            clazz = (Class<BasePo>)p[0];
            System.out.println("泛型类型dao："+clazz);
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
	public void save(BasePo t){
		 this.getSession().save(t);
		 log.debug("保存成功！");
	}
	  
	 public void delete(BasePo t){
		 this.getSession().delete(t);
		 log.debug("删除成功！");
	 }
	 
	 public void update(BasePo t){
		 this.getSession().update(t);
		 log.debug("更新成功！");
	 }
	 
	@SuppressWarnings("unchecked")
	public BasePo get(Class<?> clazz,String id) {
		BasePo entity = null;
		try {
			entity = (BasePo) this.getSession().get(clazz, id);
		} catch (HibernateException sqle) {
			entity = null;
			log.error(sqle.toString());
		}
		return entity;
	}
	@SuppressWarnings("unchecked")
	public List<BasePo> list(){
		return this.getSession().createCriteria(clazz).list();
	}
	
	@SuppressWarnings("unchecked")
	public BasePo load(Class<?> clazz,String id) {
		BasePo entity = null;
		try {
			entity = (BasePo) this.getSession().load(clazz, id);
		} catch (HibernateException sqle) {
			entity = null;
			log.error(sqle.toString());
		}
		return entity;
	 }
	@SuppressWarnings("unchecked")
	public List<BasePo> getAll(){
		Object obj = this.getSession()
				.createQuery("from "+clazz.getSimpleName()+" order by id")
				.setCacheable(true).list();
		if(obj!=null){
			return (List<BasePo>)obj;
		}else{
			return new ArrayList<BasePo>();
		}
	}
	@SuppressWarnings("unchecked")
	public List<BasePo> getAll(String entityName){
		Object obj = this.getSession()
				.createQuery("from "+entityName+" order by id")
				.setCacheable(true).list();
		if(obj!=null){
			return (List<BasePo>)obj;
		}else{
			return new ArrayList<BasePo>();
		}
	}
	@SuppressWarnings("unchecked")
	public List<BasePo> getAll(Class<?> cls){
		Object obj = this.getSession()
				.createQuery("from "+cls.getSimpleName()+" order by id")
				.setCacheable(true).list();
		if(obj!=null){
			return (List<BasePo>)obj;
		}else{
			return new ArrayList<BasePo>();
		}
	}
	public void evict(Object t){
	   this.getSession().merge(t);
	   this.getSession().evict(t);
	}
  
    @SuppressWarnings({ "unchecked", "hiding" })
	public <BasePo> List<BasePo> showPage(String queryHql, String queryCountHql, Page<BasePo> page) throws Exception  
    {  
        try  
        {  
            Session session = this.getSession();  
            page.setList(session.createQuery(queryHql).setFirstResult(page.getCunrrentPage())  
                    .setMaxResults(page.getPageSize()).list());  
            page.setTotalCount(Integer.parseInt(session.createQuery(queryCountHql).setMaxResults(1).uniqueResult()  
                    .toString()));  
        }  
        catch (Exception e)  
        {  
            throw new RuntimeException(e);  
        }  
        return page.getList();  
    }  
}
