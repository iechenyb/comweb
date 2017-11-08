package com.cyb.redis;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cyb.base.SpringJunitBase;
/**
 *作者 : iechenyb<br>
 *类描述: 效率统计分析<br>
 *创建时间: 2017年7月24日
 */
import com.cyb.web.redis.dao.RedisDao;
public class RedisCommon extends SpringJunitBase{
	Log log = LogFactory.getLog(RedisCommon.class);
	@Autowired
	RedisDao dao;
	
	String keyBasePerson = "Lhug:rolling:dhqh:persons";
	//读list效率分析
	@Test
	public void  readListTime(){
		long s=System.currentTimeMillis();
		int num = 1*10000;//1w次29秒29ms
		for(int i=0;i<num;i++){
			dao.lIndex(keyBasePerson, i);
		}
		long e=System.currentTimeMillis();
		System.out.println((e-s)/1000+"."+(e-s)/1000);
	}
	
	@Test
	public void  hashStudy (){}
	
	@Test
	public void  mhashStudy (){}
	
	@Test
	public void testOOD(){}
	
}
