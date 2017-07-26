package com.cyb.web.stock.controller;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyb.web.base.controller.BaseController;
import com.cyb.web.stock.service.StockService;
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
	StockService service;
	
	@ResponseBody
	@RequestMapping("prices/line")
	public Map<String,Object> prices(String code){
		return service.getPrice(code);
	}
	
	@ResponseBody
	@RequestMapping("codes")
	public Map<String,Object> codes(Integer cur,String type){
		return service.codes(cur, type, msgMap);
	}
	
}
