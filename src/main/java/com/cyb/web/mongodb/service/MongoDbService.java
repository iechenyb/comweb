package com.cyb.web.mongodb.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年10月18日
 */

import com.cyb.web.mongodb.dao.UserMGDao;
import com.cyb.web.mongodb.dao.UserXDao;
import com.cyb.web.mongodb.vo.UserX;
//@Service
public class MongoDbService {
	Log log = LogFactory.getLog(MongoDbService.class);
	@Autowired
	UserMGDao dao;
	
	@Autowired
	UserXDao daoX;
	
	public void saveUser(UserX user,String collectionName){
		daoX.insert(user,collectionName);
	}
	public void updateUser(Map<String,Object> user,String collectionName){
		daoX.update(user,collectionName);
	}
	public void updateUser(UserX user,String collectionName){
		daoX.update(user,collectionName);
	}
	public void updateUser(Query query,Update update,String collectionName){
		daoX.update(query,update,collectionName);
	}
	public void removeByObject(UserX user,String collectionName){
		daoX.remove(user, collectionName);
	}
	
	public void removeById(String id,String collectionName){
		Map<String,Object> user = new HashMap<String,Object>();
		user.put("id", id);
		daoX.remove(user, collectionName);
	}
	
	public List<UserX> findAll(Map<String,Object> user,String collectionName){
		return daoX.findAll(user, collectionName);
	}
	public List<UserX> findAll(Query query,String collectionName){
		return daoX.findAll(query, collectionName);
	}
}
