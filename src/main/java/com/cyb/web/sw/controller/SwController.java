package com.cyb.web.sw.controller;

import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyb.web.base.controller.BaseController;
import com.cyb.web.sw.po.Sw;
import com.cyb.web.sw.service.SwService;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
@EnableSwagger
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
	@RequestMapping(value="/get",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "根据id获取用户对象", httpMethod = "GET", 
	notes = "随便说点啥")
	@ApiResponses(value = {  
	          @ApiResponse(code = 400, message = "No Name Provided")  
	 })
	public String get(
			@RequestParam(value = "id", required = true) @ApiParam(value = "唯一id") Integer id  ){
        System.out.println("get"+id);
        return ("hello:"+id);
    }
}
