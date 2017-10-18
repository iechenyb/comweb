package com.cyb.web.mongodb.dao;
import com.cyb.web.mongodb.vo.UserX;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年10月18日
 */
public interface UserXDao extends MongoBase<UserX>{
	public void remove(UserX object,String collectionName);
	public void update(UserX user,String collectionName);
}
