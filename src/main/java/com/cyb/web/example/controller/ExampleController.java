package com.cyb.web.example.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cyb.web.aop.ControllerAop;
import com.cyb.web.example.annotation.MyController;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年12月6日
 *http://localhost:8088/comweb/example/hello/123.do
 */
@RequestMapping("example")
@Controller
public class ExampleController {
	Log log = LogFactory.getLog(ExampleController.class);
	
	@ResponseBody
	@RequestMapping("hello/{name}")
	@MyController(description="字符串测试方法")
	public String hello(@PathVariable String name){
		return "Hello "+name+"!";
	}
	
	@ResponseBody
	@RequestMapping("user/{name}")
	@MyController(description="自定义对象方法")
	public User user(@PathVariable String name){
		return new User(name);
	}
	
	@RequestMapping("page")
	@MyController(description="页面跳转方法")
	public ModelAndView page(){
		ModelAndView view = new ModelAndView();
		view.setViewName("index");
		return view;
	}
	
	@ResponseBody
	@RequestMapping("map/{name}")
	@MyController(description="返回map方法")
	public Map<String,Object> map(@PathVariable String name){
		Map<String,Object> ret = new HashMap<String, Object>();
		ret.put("name", name);
		return ret;
	}
	
	@ResponseBody
	@RequestMapping("login/{name}")
	@MyController(description="用户登录方法")
	public String login(@PathVariable String name,HttpServletRequest req){
		req.getSession().setAttribute(ControllerAop.TESTKEY, name);
		return "Hello "+name+"，登录成功!";
	}
	
	@ResponseBody
	@RequestMapping("exit")
	@MyController(description="用户退出方法")
	public String exit(HttpServletRequest req){
		req.getSession().removeAttribute(ControllerAop.TESTKEY);
		Object name = req.getSession().getAttribute(ControllerAop.TESTKEY);
		return "你已经退出 "+name+"!";
	}
}

class User{
	private String name;
    public User(String name){
    	this.name = name;
    }
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
