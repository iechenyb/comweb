package com.cyb.redis;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.cyb.base.SpringJunitBase;
import com.cyb.collection.common.CollectionFactory;
import com.cyb.collection.po.User;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月24日
 */
import com.cyb.web.redis.dao.RedisDao;
import com.cyb.web.redis.dao.UserRedisDao;

import net.sf.json.JSONObject;
public class RedisTest extends SpringJunitBase{
	Log log = LogFactory.getLog(RedisTest.class);
	@Autowired
	RedisDao dao;
	@Autowired
	UserRedisDao dao2;
	
	@Test
	public void  listStudy (){
		String keyBase = "com:cyb:list";
		String key=keyBase+":number";
		User user= new User();
		user.setPwd("123");
		user.setName("iechenyb");
		dao.lpush(key,"1");
		dao.lpush(key,"1");
		dao.lpush(key,"1");//允许重复
		dao.lpush(key, JSONObject.fromObject(user).toString());
		dao.lpush(key, JSONObject.fromObject(user).toString());
		dao.rPop(key);//弹出队头的元素
		dao.lPop(key);//弹出队尾的元素
		for(int i=0;i<dao.lLen("list");i++){
			String value = dao.lIndex("list", i);
			if(value.contains("username")){
				JSONObject obj = JSONObject.fromObject(value);
				log.info(obj.get("username"));
			}
		}
		/*dao.lrem(key, 2, "1");//从key中移除两个值为1属性
		dao.lPop(key);
		Position where  = null;
		dao.linsert(key, where, "", "hahaha");
		dao.lRange(key, 1l, 5l);
		dao.lSet(key, 1, "hahaha");
		dao.lTrim(key, 1, 5);*/
		/*dao.lpush("test", "test");
		dao.del("test");
		dao.delete("test");*/
		System.out.println(dao.ttl(key, 0));//-1代表永久有效Redis TTL 命令以秒为单位返回 key 的剩余过期时间
	}
	
	@Test
	public void  hashStudy (){
		/*for(int i=0;i<10;i++){
			dao.hSet(keyBase+i, "name", "name"+i);
			dao.hSet(keyBase+i, "password", "password"+i);
		}*/
		String keyBase = "com:cyb:hset";
		String key=keyBase+":iechenyb";
		dao.hSet(key, "name", "iechenyb");
		dao.hSet(key, "age", "20");
		dao.hSet("某个赛季某日某个类型", "资金账号", "成绩值{json}");
		log.info(new String(dao.hGet(key, "age")));
		dao.hSet(key, "age", "200");
		dao.del("某个赛季某日某个类型");
		log.info(dao.hLen(key));//获取某个key的长度 字段个数
		log.info(dao.hGetAll(key).keySet());
		Set<byte[]> it = dao.hGetAll(key).keySet();
		Iterator<byte[]> itr = it.iterator(); 
		while(itr.hasNext()){
			log.info(new String(dao.hGet(key,new String(itr.next()))));
		}
		log.info(dao.hExists(key, "name"));
		log.info(dao.hKeys(key));
		log.info(dao.hLen(key));
		log.info(dao.hVals(key));
		//log.info(dao.hDel(key, "name"));
	}
	
	@Test
	public void  mhashStudy (){
		String keyBase = "com:cyb:mhash:";
		HashMap<byte[], byte[]> data = new HashMap<>();
		HashMap<String, String> map = new HashMap<>();
		CollectionFactory.build(10);
		try {
			map.put("江苏", "无锡");
			data.put("中国".getBytes(), map.toString().getBytes());
			data.put("中国".getBytes(), map.toString().getBytes());//不可重复
			data.put("亚洲".getBytes(), JSONObject.fromObject(map).toString().getBytes());
			data.put("江苏".getBytes(), "无锡".getBytes());
			data.put("iechenyb".getBytes(), JSONObject.fromObject(CollectionFactory.getUser()).toString().getBytes());
			dao.hMSet(keyBase + "地球", data);
			List<String> lst =  dao.hMGet(keyBase + "地球", "中国".getBytes());
			log.info(lst.get(0));
			lst = dao.hMGet(keyBase + "地球", "亚洲".getBytes());
			log.info(lst.get(0));
			@SuppressWarnings("unchecked")
			Map<String,String> obj2 = JSON.parseObject(lst.get(0), Map.class);
			log.info(obj2.get("江苏"));
			lst =  dao.hMGet(keyBase + "地球", "iechenyb".getBytes());
			log.info(lst.get(0));
			User obj = (User) JSONObject.toBean(JSONObject.fromObject(lst.get(0)), User.class);
			log.info(obj.getPwd());
		} finally {
			
		}
	}
	@Test
	public void testOOD(){/*
		User user = new User();
		user.setId(1);
		user.setName("chenyb");
		List<User> userList = new ArrayList<User>();
		dao2.setCacheObject("ood:obj:user", user.toString());
		userList.add(user);
		//dao2.setCacheList("ood:obj:userList", userList);//只能存储string list和set
		Map<String,User> map = new HashMap<String,User>();
		map.put("1", user);
		dao2.setCacheMap("ood:map", map);
		User t = (User)dao2.getCacheMap("ood:map").get("1");
		System.out.println(t.getName());
		Map<Integer,User> map1 = new HashMap<Integer,User>();
		map1.put(1, user);	
		dao2.setCacheIntegerMap("ood:map:int", map1);
		User t1 = (User)dao2.getCacheMap("ood:map:int").get(1);
		System.out.println(t1.getName());
		
		Set<User> userSet = new HashSet<User>();
		userSet.add(user);
		dao2.setCacheSet("ood:set", userSet);
		System.out.println(dao2.getCacheSet("ood:set"));*/
	}
	
}
