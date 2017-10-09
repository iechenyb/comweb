package com.cyb.jdbc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.cyb.base.SpringJunitBase;

import junit.framework.Assert;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年9月8日
 */
public class NamedJdbcTest extends SpringJunitBase {
	Log log = LogFactory.getLog(NamedJdbcTest.class);
	
	@Resource(name="namedJdbcTemplate")
	NamedParameterJdbcTemplate dao;
	
	@SuppressWarnings("unchecked")
	@Test
	public void insertTest(){
		
		HashMap<String,Object> param = new HashMap<String,Object>();
		//create table test(id varchar(10),num int)
		param.put("id", "5");
		param.put("num", 2);
		param.put("doubli",0);//1.23456789
		//param.put("cusid", "3");
		String sql="insert into test(id,num,doubli) "
				+ "values (:id,:num,:doubli)";
		/*SqlParameterSource ps=new BeanPropertySqlParameterSource(param);//从user中取出数据，与sql语句中一一对应将数据换进去  
		KeyHolder keyHolder=new GeneratedKeyHolder();  */
		//dao.update(sql, ps, keyHolder);  //map不可以，必须有get set方法
		//dao.update(sql, param);
		/*Collections.synchronizedMap(new HashMap<>());*/
		long s = System.currentTimeMillis();
		HashMap<String,Object> params[]=new HashMap[1000];
		for(int i=0;i<params.length;i++){
			params[i]=param;
		}
		
		/*params[0]=param;
		params[1]=param;
		params[2]=param;
		params[3]=param;
		params[4]=param;*/
		/*List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
		maps.add(param);maps.add(param);maps.add(param);
		params = (HashMap<String, Object>[]) maps.toArray();*/
		//dao.getJdbcOperations().batchUpdate(null);
		dao.batchUpdate(sql, params);
		long e = System.currentTimeMillis();
		log.info("组装完成！耗时"+(e-s)/1000);
	}
	@Test  
	public void testBatchUpdate3() {  
	    /*NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);  
	    String insertSql = "insert into test(name) values(:myName)";  
	    UserModel model = new UserModel();  
	    model.setMyName("name5");  
	    SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(new Object[] {model, model});  
	    namedParameterJdbcTemplate.batchUpdate(insertSql, params);  
	    Assert.assertEquals(2, dao.queryForInt("select count(*) from test"));*/  
	}  
	
	@Test  
	public void testBatchUpdate5() {  
	   /* SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);  
	    insert.withTableName("test");  
	    Map<String, Object> valueMap = new HashMap<String, Object>();  
	    valueMap.put("name", "name5");  
	    insert.executeBatch(new Map[] {valueMap, valueMap});  
	   Assert.assertEquals(2, jdbcTemplate.queryForInt("select count(*) from test"));  */
	}  
	@Test  
	public void testBatchUpdate1() {  
	    /*String insertSql = "insert into test(name) values('name5')";  
	    String[] batchSql = new String[] {insertSql, insertSql};  
	    dao.batchUpdate(batchSql);  
	    Assert.assertEquals(2, jdbcTemplate.queryForInt("select count(*) from test"));  */
	}  
}
