package com.cyb.redis;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisListCommands.Position;

import com.cyb.base.BaseUtils;
import com.cyb.mybatis.User;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月24日
 */
import com.cyb.web.redis.dao.RedisDao;

import net.sf.json.JSONObject;
public class RedisUtils extends BaseUtils{
	Log log = LogFactory.getLog(RedisUtils.class);
	@Autowired
	RedisDao dao;
	
	@Test
	public void  listStudy (){
		String key="list";
		User user= new User();
		user.setPassword("123");
		user.setUsername("iechenyb");
		dao.lpush(key,"1");
		dao.lpush(key,"1");
		dao.lpush(key,"1");//允许重复
		dao.lpush(key, JSONObject.fromObject(user).toString());
		dao.lpush(key, JSONObject.fromObject(user).toString());
		for(int i=0;i<dao.lLen("list");i++){
			String value = dao.lIndex("list", i);
			if(value.contains("username")){
				JSONObject obj = JSONObject.fromObject(value);
				//log.info(obj.get("username"));
			}
		}
		/*dao.lrem(key, 2, "1");//从key中移除两个值为1属性
		dao.lPop(key);
		Position where  = null;
		dao.linsert(key, where, "", "hahaha");
		dao.lRange(key, 1l, 5l);
		dao.lSet(key, 1, "hahaha");
		dao.lTrim(key, 1, 5);*/
		dao.lpush("test", "test");
		dao.del("test");
		dao.delete("test");
	}
	
	@Test
	public void  hashStudy (){
		String keyBase="com:cyb:model:";
		String key = keyBase+1;
		/*for(int i=0;i<10;i++){
			dao.hSet(keyBase+i, "name", "name"+i);
			dao.hSet(keyBase+i, "password", "password"+i);
		}*/
		log.info(dao.hLen(keyBase+0));//获取某个key的长度 字段个数
		log.info(dao.hGetAll(keyBase+0).keySet());
		Set<byte[]> it = dao.hGetAll(keyBase+0).keySet();
		Iterator<byte[]> itr = it.iterator(); 
		while(itr.hasNext()){
			log.info(new String(dao.hGet(keyBase+0,new String(itr.next()))));
		}
		
		log.info(dao.hExists(key, "name"));
		log.info(dao.hKeys(key));
		log.info(dao.hLen(key));
		log.info(dao.hVals(keyBase+1));
		log.info(dao.hDel(key, "name"));
	}
	
}
