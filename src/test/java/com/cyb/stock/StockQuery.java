package com.cyb.stock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.app.stock.RealQutoes;
import com.cyb.base.SpringJunitBase;
import com.cyb.web.redis.dao.RedisDao;
import com.cyb.web.stock.constant.StockContant;
import com.cyb.web.stock.utils.QutoesUtils;

import net.sf.json.JSONObject;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月25日
 */
public class StockQuery  extends SpringJunitBase{
	String base=StockContant.base;
	Log log = LogFactory.getLog(StockQuery.class);
	@Autowired
	RedisDao dao;
	@Test
	public void queryMinQutoes(){
		String key = base+"minuteQutoesHash:sh000001";
		System.out.println();
		Map<String,String> times = QutoesUtils.spiltTimeMap(1);
		String tmp = new String(dao.hGet(key, "09:40"));
		RealQutoes rqtmp = JSON.parseObject(tmp, RealQutoes.class);
		Iterator<String> it = times.keySet().iterator(); 
		while(it.hasNext()){
			try{
				String time = it.next();
				String json = new String(dao.hGet(key, time));
				RealQutoes rq = JSON.parseObject(json, RealQutoes.class);
				times.put(time, rq.getPrice());
			}catch(Exception e){
				
			}
		}
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("x", times.keySet());
		data.put("y", times.values());
		data.putAll(QutoesUtils.calSection(Double.valueOf(rqtmp.getHigh()), Double.valueOf(rqtmp.getLow()), Double.valueOf(rqtmp.getPreclose())));
		System.out.println(JSONObject.fromObject(data));
	}
	
	@Test
	public void queryKLine(){
		String key = base+"minuteQutoesHash:sh000001";
		System.out.println();
		Map<String,String> times = QutoesUtils.spiltTimeMap(1);
		Map<String,Object> data = new LinkedHashMap<String,Object>();
		Iterator<String> it = times.keySet().iterator(); 
		List<String> values ;// 开盘，收盘，最低，最高
		while(it.hasNext()){
			try{
				values = new ArrayList<String>();
				String time = it.next();
				String json = new String(dao.hGet(key, time));
				RealQutoes rq = JSON.parseObject(json, RealQutoes.class);
				values.add(rq.getOpen());
				values.add(rq.getClose());
				values.add(rq.getLow());
				values.add(rq.getHigh());
				data.put(time, values);
				values=null;
			}catch(Exception e){
				
			}
		}
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("x", times.keySet());
		ret.put("y", data.values());
		System.out.println(JSONObject.fromObject(ret));
	}
}
