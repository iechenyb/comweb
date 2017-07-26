package com.cyb.web.stock.service;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.app.stock.RealQutoes;
import com.app.stock.Stock;
import com.cyb.file.ObjectFileUtils;
import com.cyb.page.Pagination;
import com.cyb.web.constant.Contants;
import com.cyb.web.redis.dao.RedisDao;
import com.cyb.web.stock.constant.StockContant;
import com.cyb.web.stock.utils.QutoesUtils;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月26日
 */
@Service
public class StockService {
	Log log = LogFactory.getLog(StockService.class);
	@Autowired
	RedisDao dao;
	public Map<String,Object> getPrice(String code){
		String key = StockContant.base+"minuteQutoesHash:"+code;
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
		return data;
	}
	static 	List<Stock> shStock;
	static 	List<Stock> szStock;
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> codes(Integer cur,String type,Map<String,Object> msgMap){
		if(cur==null||cur<1){
			cur=1;
		}
		if(shStock==null){
		  shStock = (List<Stock>) ObjectFileUtils.readObjectFromFile(Contants.WEBPATH+"WEB-INF/classes/stock/shstocks.data");
		  szStock = (List<Stock>) ObjectFileUtils.readObjectFromFile(Contants.WEBPATH+"WEB-INF/classes/stock/szstocks.data");
		}
		Pagination page = null;
		if(type.equals("sh")){
			page = new Pagination(cur, 36, shStock.size());
			msgMap.put("sh", shStock.subList(page.getOffset(), cur*page.getPageSize()));
		}else{
			page = new Pagination(cur, 36, szStock.size());
			msgMap.put("sz", szStock.subList(page.getOffset(), cur*page.getPageSize()));
		}
		msgMap.put("page", page);
		return msgMap;
	}
}
