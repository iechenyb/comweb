package com.cyb.web.mongodb.dao;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年10月18日
 */
public interface MongoBase<T> {
	   //添加  
    public void insert(T object,String collectionName);    
    //根据条件查找  
    public T findOne(Map<String,Object> params,String collectionName);    
    //查找所有  
    public List<T> findAll(Map<String,Object> params,String collectionName);    
    //修改  
    public void update(Map<String,Object> params,String collectionName);   
    //修改  
    public void update(Criteria criteria,Update update,String collectionName); 
    //修改  
    public void update(Query query,Update update,String collectionName);  
    //创建集合  
    public void createCollection(String collectionName);  
    //根据条件删除  
    public void remove(Map<String,Object> params,String collectionName);
    
    public List<T> findAll(Query query,String collectionName);
}
