package com.cyb.stock;
import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.app.stock.DrawCodesUtils;
import com.app.stock.RealQutoes;
import com.app.stock.Stock;
import com.cyb.base.SpringJunitBase;
import com.cyb.file.ObjectFileUtils;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月25日
 */
import com.cyb.web.redis.dao.RedisDao;

import net.sf.json.JSONObject;
public class StockInitTest extends SpringJunitBase{
	String base="com.cyb:";
	Log log = LogFactory.getLog(StockInitTest.class);
	@Autowired
	RedisDao dao;
	@SuppressWarnings("unchecked")
	@Test
	public void initStock2Redis(){
		String path1 = "d:/data/shstocks.data";
		String path2 = "d:/data/szstocks.data";
		List<Stock> shS = (List<Stock>) ObjectFileUtils.readObjectFromFile(path1);
		List<Stock> shZ = (List<Stock>) ObjectFileUtils.readObjectFromFile(path2);
	    dao.del(base+"code:sh");
	    dao.del(base+"code:sz");
		for(Stock s:shS){
	    	dao.lpush(base+"code:sh", JSONObject.fromObject(s).toString());
	    }
	    for(Stock s:shZ){
	    	dao.lpush(base+"code:sz", JSONObject.fromObject(s).toString());
	    }
	}
	@Test
	public void readStock2Redis(){
		for(int i=0;i<dao.lLen(base+"code:sh");i++){
			String value = dao.lIndex(base+"code:sh", i);
			System.out.println(value);
		}
		for(int i=0;i<dao.lLen(base+"code:sz");i++){
			String value = dao.lIndex(base+"code:sz", i);
			System.out.println(value);
		}
	}
	
	@Test
	public void saveRealQutoes2Redis() throws IOException{
		for(int i=0;i<dao.lLen(base+"code:sh");i++){//
			String codeStr = dao.lIndex(base+"code:sh", i);
			Stock s= JSON.parseObject(codeStr, Stock.class);
			String code=s.getExchange()+s.getCode(); 
			RealQutoes rq = DrawCodesUtils.getQutoes(code);
			dao.hSet(base+"realQutoes:"+s.getExchange(), code, JSONObject.fromObject(rq).toString());
		}
	}
	@Test
	public void saveMinuteQutoes2Redis() throws IOException{
		for(int i=0;i<dao.lLen(base+"code:sh");i++){
			String codeStr = dao.lIndex(base+"code:sh", i);
			Stock s= JSON.parseObject(codeStr, Stock.class);
			String code=s.getExchange()+s.getCode(); 
			RealQutoes rq = DrawCodesUtils.getQutoes(code);
			dao.lpush(base+"minuteQutoes:"+code,JSONObject.fromObject(rq).toString());
		}
	}
}
