package com.cyb.web.stock.controller;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.app.stock.RealQutoes;
import com.app.stock.Stock;
import com.cyb.file.ObjectFileUtils;
import com.cyb.page.Pagination;
import com.cyb.web.base.controller.BaseController;
import com.cyb.web.constant.Contants;
import com.cyb.web.redis.dao.RedisDao;
import com.cyb.web.stock.constant.StockContant;
import com.cyb.web.stock.utils.QutoesUtils;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月26日
 */
@Controller
@RequestMapping("restfull/stock")
public class StockController extends BaseController{
	Log log = LogFactory.getLog(StockController.class);
	
	@Autowired
	RedisDao dao;
	
	@ResponseBody
	@RequestMapping("prices/line")
	public Map<String,Object> prices(String code){
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
	@ResponseBody
	@RequestMapping("codes")
	public Map<String,Object> codes(Integer cur){
		if(cur==null){
			cur=1;
		}
		if(shStock==null){
		  shStock = (List<Stock>) ObjectFileUtils.readObjectFromFile(Contants.WEBPATH+"WEB-INF/classes/stock/shstocks.data");
		  szStock = (List<Stock>) ObjectFileUtils.readObjectFromFile(Contants.WEBPATH+"WEB-INF/classes/stock/szstocks.data");
		}
		Pagination page = new Pagination(cur, 12, shStock.size());
		msgMap.put("sz", szStock.subList(0, 12));
		msgMap.put("sh", shStock.subList(0, 12));
		msgMap.put("page", page);
		return msgMap;
	}
	
}
