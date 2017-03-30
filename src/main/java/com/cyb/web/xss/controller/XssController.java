package com.cyb.web.xss.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyb.web.base.controller.BaseController;
import com.cyb.web.xss.po.Customer;
import com.cyb.web.xss.service.XssService;
/**
 *  SELECT * FROM users WHERE name = 'a' OR 't'='t';  username = a' or 't'='t
	SELECT * FROM users WHERE name = 'a';DROP TABLE users;SELECT * FROM DATA WHERE name LIKE '%';
	SELECT * FROM DATA WHERE id = 1;DROP TABLE users; 
 * @author DHUser
 * http://localhost:8088/web/xss/query.do?name=chenyb1' or 1 = '1 &type=jdbc
 * name=chenyb1' or 1 = '1
 * name=chenyb';delete * from t_sys_customer where name='chenyb1
 */
@Controller
@RequestMapping("xss")
public class XssController extends BaseController{
	@Resource(name="xssService")
	XssService xssService;
	
	@RequestMapping("init")
	@ResponseBody
	public int init(){
		Customer c = new Customer();
		for(int i=0;i<10;i++){
			 c = new Customer();
			 c.setName("chenyb"+i);
			 c.setMoney(1+i);
			 xssService.save(c);
		}
		return 1;
	}
	@RequestMapping("show")
	@ResponseBody
	public List<Customer> showAll(){
		return xssService.getList();
	}
	@RequestMapping("query")
	@ResponseBody
	public List<?> query(String name,String type){
		return xssService.query(name, type);
	}	
}
