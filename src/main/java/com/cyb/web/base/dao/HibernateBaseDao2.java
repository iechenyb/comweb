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
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import com.cyb.web.base.po.Page;
@Component("baseDao2")
public class HibernateBaseDao2<T> extends HibernateDaoSupport {
	@Resource(name="jdbcTemplate")
	public JdbcTemplate jdbcTemplate;
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年10月19日下午3:17:28</br>
	 */
	Log log = LogFactory.getLog(HibernateBaseDao2.class);
	@Resource  
    public void setSessionFacotry(SessionFactory sessionFacotry) {  
        super.setSessionFactory(sessionFacotry);  
    } 

	protected Class<T> clazz;

	/**
	 * 通过反射泛型获取Class类型,getGenericSuperclass()方法获取对象的泛型的父类类型信息，
	 * getActualTypeArguments()[0]方法得到T的真实类型
	 * 
	 */
	@SuppressWarnings("unchecked")
	public HibernateBaseDao2() {
		Type t = getClass().getGenericSuperclass();
        if(t instanceof ParameterizedType){
            Type[] p = ((ParameterizedType)t).getActualTypeArguments();
            clazz = (Class<T>)p[0];
        }
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
	@SuppressWarnings("unchecked")
	public List<T> getAll(){
		Object obj = this.getSession()
				.createQuery("from "+clazz.getSimpleName()+" order by id")
				.setCacheable(true).list();
		if(obj!=null){
			return (List<T>)obj;
		}else{
			return new ArrayList<T>();
		}
	}
	@SuppressWarnings("unchecked")
	public List<T> getAll(String entityName){
		Object obj = this.getSession()
				.createQuery("from "+entityName+" order by id")
				.setCacheable(true).list();
		if(obj!=null){
			return (List<T>)obj;
		}else{
			return new ArrayList<T>();
		}
	}
	public void evict(Object t){
	   this.getSession().merge(t);
	   this.getSession().evict(t);
	}
	
    public List<?> showPage(String queryHql, String queryCountHql, int firstResult, int maxResult) throws Exception  
    {  
        List<Object> list = new ArrayList<Object>();  
        try  
        {  
            Session session = this.getSession();  
            list.add(session.createQuery(queryHql).setFirstResult(firstResult).setMaxResults(maxResult).list());  
            list.add(session.createQuery(queryCountHql).setFirstResult(firstResult).uniqueResult());  
        }  
        catch (Exception e)  
        {  
            throw new RuntimeException(e);  
        }  
        return list;  
    }  
  
    @SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T> showPage(String queryHql, String queryCountHql, Page<T> page) throws Exception  
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
  
    public List<?> showPage(String queryHql, DetachedCriteria criteria, int firstResult, int maxResult)  
            throws Exception  
    {  
        List<Object> list = new ArrayList<Object>();  
        try  
        {  
            Session session = this.getSession();  
            list.add(this.getHibernateTemplate().findByCriteria(criteria, firstResult, maxResult));  
            list.add(session.createQuery(queryHql).setMaxResults(1).uniqueResult());  
        }  
        catch (Exception e)  
        {  
            throw new RuntimeException(e);  
        }  
        return list;  
    }  
  
    @SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T> showPage(String queryHql, DetachedCriteria criteria, Page<T> page) throws Exception  
    {  
        try  
        {  
            Session session = this.getSession();  
            page.setList(this.getHibernateTemplate().findByCriteria(criteria, page.getCunrrentPage(),  
                    page.getPageSize()));  
            page.setTotalCount(Integer.parseInt(session.createQuery(queryHql).setMaxResults(1).uniqueResult()  
                    .toString()));  
        }  
        catch (Exception e)  
        {  
            throw new RuntimeException(e);  
        }  
        return page.getList();  
    }  
}
