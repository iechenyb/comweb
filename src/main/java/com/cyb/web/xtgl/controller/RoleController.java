package com.cyb.web.xtgl.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyb.web.base.controller.BaseController;
import com.cyb.web.xtgl.po.Role;
import com.cyb.web.xtgl.service.RoleService;
/**
 * 
 * 功能描述：角色管理
 * 作者：iechenyb
 * 创建时间：2017年1月3日上午10:46:00
 */
@Controller
@RequestMapping("role")
public class RoleController extends BaseController{
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月3日下午2:07:52</br>
	 */
    @Resource(name="roleService")
    RoleService service;
	/**
	 * 
	 * 作者:iechenyb</br>
	 * 功能描述： 新增角色</br>
	 * 创建时间：2017年1月3日上午10:46:11</br>
	   @param role
	   @return
	 */
    @ResponseBody
	@RequestMapping("addRole")
	public Map<String, Object> addRole(@RequestBody Role role){
		try{
			service.save(role);
			setMsgMap(SUCCESS, "角色信息增加成功！");
			msgMap.put("t", role);
		}catch(Exception e){
			setMsgMap(FAILURE,"角色信息增加失败！");
			e.printStackTrace();
		}
	   return msgMap;
	}
    /**
	 * 
	 * 作者:iechenyb</br>
	 * 功能描述： 删除角色</br>
	 * 创建时间：2017年1月3日上午10:46:11</br>
	   @param role
	   @return
	 */
	@ResponseBody
	@RequestMapping("delRole")
	public Map<String, Object> delRole(Role role){
		try{
			service.delete(role);
			setMsgMap(SUCCESS,"角色信息删除成功！");
		}catch(Exception e){
			setMsgMap(FAILURE, "角色信息删除失败！");
			e.printStackTrace();
		}
	   return msgMap;
	}
	/**
	 * 
	 * 作者:iechenyb</br>
	 * 功能描述： 更新角色</br>
	 * 创建时间：2017年1月3日上午10:46:11</br>
	   @param role
	   @return
	 */
	@ResponseBody
	@RequestMapping("updRole")
	public Map<String,Object> updRole(@RequestBody Role role){
		try{
			service.update(role);
			setMsgMap(SUCCESS, "角色信息更新成功！");
			msgMap.put("t", role);
		}catch(Exception e){
			setMsgMap(FAILURE, "角色信息更新失败！");
			e.printStackTrace();
		}
	   return msgMap;
	}
	/**
	 * 
	 * 作者:iechenyb</br>
	 * 功能描述： 角色信息列表</br>
	 * 创建时间：2017年1月3日上午10:46:11</br>
	   @param role
	   @return
	 */
	@ResponseBody
	@RequestMapping("list")
	public List<Role> list(){
		try{
			 List<Role> Roles = service.getRoles();
			 return Roles ;
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList<Role>();
		}
	 
	}
	/**
	 * 
	 * 作者:iechenyb</br>
	 * 功能描述： 获取角色</br>
	 * 创建时间：2017年1月3日上午10:46:11</br>
	   @param role
	   @return
	 */
	@ResponseBody
	@RequestMapping("getRole")
	public Role getOne(Role role){
		try{
			Role t = (Role) service.get(role.getId());
			return t;
		}catch(Exception e){
			return new Role();
		}	  
	}
	
	@ResponseBody
	@RequestMapping("sendList")
	public long[] t1(@RequestParam(value = "datas[]") long[] datas){
		return datas;
	}
}
