package com.cyb.mongodb;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.cyb.base.SpringJunitBase;
import com.cyb.web.mongodb.service.MongoDbService;
import com.cyb.web.mongodb.vo.UserX;

public class MongodbTest extends SpringJunitBase{
	Log log = LogFactory.getLog(MongodbTest.class);
	@Autowired
	MongoDbService service;
	
	UserX user;
	String collectionName="my";
	String id = "id_cyb_02";
	
	@Before
	public void init(){
		user = new UserX();
		user.setAge(20);
		user.setName("iechenyb");
		user.setPassword("xxx-upd");
		user.setId(id);
		System.out.println("===========init==========");
	}
	@Test
	public void  saveTest (){
		//id变化，则是新记录 id不变，则不保存，继续持有原有记录。
		//service.saveUser(user,"my");
		long start = System.currentTimeMillis();
		for(int i=0;i<100000;i++){
			UserX user = new UserX();
			user.setAge(20+i);
			user.setName(new StringBuffer("iechenyb").append(i).toString());
			user.setPassword("111111");
			user.setId(new StringBuffer("id_cyb_").append(i).toString());
			service.saveUser(user,collectionName);
		}
		long end = System.currentTimeMillis();
		log.info("用时ms:"+(end-start));//32.831
	}
	
	@Test
	public void  updateBatchTest (){
		//id变化，则是新记录 id不变，则不保存，继续持有原有记录。
		//service.saveUser(user,"my");
		/*long start = System.currentTimeMillis();
		for(int i=0;i<100;i++){
			UserX user = new UserX();
			user.setName("xxxxxx");
			user.setPassword("999999");
			user.setId(new StringBuffer("id_cyb_").append(i).toString());
			service.updateUser(user,collectionName);
		}
		long end = System.currentTimeMillis();
		log.info("用时ms:"+(end-start));//35.831
		 */		
		Query query;
		Criteria cri = Criteria.where("age").in(22,23);
		cri = Criteria.where("name").is("xxxx").and("password").is("999999");//lte
		//将年龄大于等于22小于等于50的记录的密码更新成666666
		query = new Query(cri);//.and("age").gte(22));
		Update update = new Update().set("password", "666666").set("name", "chenyuanbao");//值改变，数据类型也改变。
		service.updateUser(query,update,collectionName);
		System.out.println("记录更新完成！");
	}
	
	@Test
	public void  updateTest (){
		Map<String,Object> user = new HashMap<String,Object>();
		user.put("id", id);
		user.put("age", 200);
		user.put("name", "chenyuanbao");
		user.put("password", 3635462);
		service.updateUser(user, collectionName);
	}
	
	@Test
	public void  removeTest (){
		//service.removeById(id, collectionName);
		user = new UserX();
		user.setId(id);//仅仅设置一个id
		service.removeByObject(user, collectionName);//也可以
	}
	
	@Test
	public void findTest(){
		Map<String,Object> user = new HashMap<String,Object>();
		user.put("password", "999999");
		//注意类型，整形当作字符串查找，则不能查找成功！
		List<UserX> list = service.findAll(user, collectionName);
		System.out.println("查找个数："+list.size());
		
		//查询年龄小于等于63的记录
		Query query = new Query(Criteria.where("age").lte(63));//63-20+1=44
		List<UserX> listAge = service.findAll(query, collectionName);
		System.out.println("按照年龄查找到记录数："+listAge.size());
		
		
	}
	
}
