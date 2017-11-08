package com.cyb.web.mongodb.dao;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.cyb.web.mongodb.vo.UserX;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年10月18日
 */
//@Repository("userMGDaoImpl")  
public class UserXDaoImpl implements UserXDao {  
      
    @Resource  
    private MongoTemplate mongoTemplate;  
  
    @Override  
    public void insert(UserX object,String collectionName) {  
        mongoTemplate.insert(object, collectionName);  
    }  
  
    @Override  
    public UserX findOne(Map<String,Object> params,String collectionName) {  
         return mongoTemplate.findOne(new Query(
        		 Criteria.where("id").is(params.get("id"))),
        		 UserX.class,collectionName);    
    }  
  
    @Override  
    public List<UserX> findAll(Map<String,Object> params,String collectionName) {  
        List<UserX> result = mongoTemplate.find(
        		new Query(Criteria.where("password").is(params.get("password"))),
        		UserX.class,collectionName);  
        return result;  
    }  
    @Override  
    public List<UserX> findAll(Query query,String collectionName) {  
        List<UserX> result = mongoTemplate.find(
        		query,UserX.class,collectionName);  
        return result;  
    }  
  
    @Override  
    public void update(Map<String,Object> params,String collectionName) {  
        mongoTemplate.upsert(
        		new Query(Criteria.where("id").is(params.get("id"))), 
        		new Update().set("name", params.get("name")),
        		UserX.class,collectionName);  
    }  

    @Override  
    public void update(UserX user,String collectionName) {  
        mongoTemplate.upsert(
        		new Query(Criteria.where("id").is(user.getId())), 
        		new Update().set("name", user.getName()).set("password", user.getPassword()),
        		UserX.class,collectionName);  
    } 
  
    @Override  
    public void createCollection(String name) {  
        mongoTemplate.createCollection(name);  
    }  
  
  
    @Override  
    public void remove(Map<String, Object> params,String collectionName) {  
        mongoTemplate.remove(
        		new Query(Criteria.where("id").is(params.get("id"))),
        		UserX.class,collectionName);  
    }  
    
    @Override  
    public void remove(UserX object,String collectionName) {  
        mongoTemplate.remove(object,collectionName);
    }

	@Override
	public void update(Criteria criteria,Update update,String collectionName) {
		   mongoTemplate.upsert(
	        		new Query(criteria), 
	        		update,
	        		UserX.class,collectionName); 
	}

	@Override
	public void update(Query query, Update update, String collectionName) {
		   mongoTemplate.updateMulti(
	        		query, 
	        		update,
	        		UserX.class,collectionName); 
	}  
}
