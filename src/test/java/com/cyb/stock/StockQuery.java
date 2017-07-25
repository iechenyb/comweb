package com.cyb.stock;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cyb.base.BaseUtils;
import com.cyb.web.redis.dao.RedisDao;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月25日
 */
public class StockQuery  extends BaseUtils{
	String base="com.cyb:";
	Log log = LogFactory.getLog(StockQuery.class);
	@Autowired
	RedisDao dao;
	@Test
	public void queryMinQutoes(){
		for(int i=0;i<dao.lLen(base+"code:sh:sh000001");i++){
			System.out.println(dao.lIndex(base+"code:sh:sh000001",i));
		}
	}
}
