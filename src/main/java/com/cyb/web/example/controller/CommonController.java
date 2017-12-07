package com.cyb.web.example.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyb.web.example.po.Role;

/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年12月7日
 */
@RequestMapping("common")
@Controller
public class CommonController {
	
	static List<Role>  roles = new ArrayList<Role>();
	
	@ResponseBody
	@RequestMapping("infor")
	public int infoRoles(){
		return roles.size();
	}
	
	@ResponseBody
	@RequestMapping("init")
	public String initRoleList(int num){
		for(int i=0;i<num;i++){
			Role role = new Role(i,"role"+i,"desc"+i);
			roles.add(role);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("roles")
	public Map<String,Object> getRoleList(int start,int limit){
		//if(roles.size()==0){initRoleList(100);}
		System.out.println("分页信息："+start+"->"+limit);
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("total", roles.size());
		if((start+limit)>=roles.size()){
			ret.put("data",roles.subList(start, roles.size()));
		}else{
			ret.put("data",roles.subList(start, start+limit));
		}
		return ret;
	}
	
	
	@ResponseBody
	@RequestMapping("addRole")
	public String  addRole(String name,String desc){
		roles.add(new Role(roles.size()+1,name,desc));
		return "增加成功！";
	}
	
	
	public boolean hasRole(String ids,int curId){
		boolean has = false;
		String[] id = ids.split(",");
		for(int i=0;i<id.length;i++){
			if(curId==Integer.valueOf(id[i])){
				System.out.println("delete id is"+curId);
				has = true;
				break;
			}
		}
		return has;
	}
	
	@ResponseBody
	@RequestMapping("delRole")
	public String  delRole(String ids){
		Iterator<Role> iter = roles.iterator();
		while(iter.hasNext()){
			if(hasRole(ids,iter.next().getId())){
				iter.remove();
			}
		}
		return "删除成功！";
	}
}
