package com.cyb.web.sw.controller;

import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyb.web.base.controller.BaseController;
import com.cyb.web.sw.po.Sw;
import com.cyb.web.sw.service.SwService;

@Controller
@RequestMapping("sw")
public class SwController extends BaseController{
	@Resource(name="swService")
	SwService swService;
	@RequestMapping("add")
	@ResponseBody
	public int add(String no,long num){
		Sw sw = new Sw();
		sw.setCardNo(no);
		sw.setMoney(num);
		swService.save(sw);
		return 1;
	}
	@RequestMapping("del")
	@ResponseBody
	public int del(String id){
		Sw sw = new Sw();
		sw.setId(id);
		swService.delete(sw);
		return 1;
	}
	@RequestMapping("show")
	@ResponseBody
	public JSONArray show(){
		return JSONArray.fromObject(swService.getList());
	}
	@RequestMapping("testSw")
	@ResponseBody
	public Map<String, Object> updateSw(String ex){
		try{
			swService.updateSw(ex);
			setMsgMap(SUCCESS, "转账成功！");
			msgMap.put("data", swService.getList());
		}catch(Exception e){
			e.printStackTrace();
			setMsgMap(FAILURE, "转账异常！");
			return msgMap;
		}
		return msgMap;
	}
}
