package com.cyb.web.xtgl.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyb.web.base.controller.BaseController;
import com.cyb.web.constant.Contants;
import com.cyb.web.xtgl.po.Menu;
import com.cyb.web.xtgl.po.User;
import com.cyb.web.xtgl.service.MenuService;
/**
 * 系统管理-菜单管理
 * 功能描述：
 * 作者：iechenyb
 * 创建时间：2016年12月23日上午9:35:48
 */
@Controller
@RequestMapping("menu")
public class MenuController extends BaseController{
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月3日下午2:08:06</br>
	 */
	@Resource(name="menuService")
	MenuService service;
	/**
	 * 新增菜单
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年12月23日上午9:36:06</br>
	 */
	@ResponseBody
	@RequestMapping("addMenu")
	public Map<String, Object> addMenu(@RequestBody Menu menu){
		try{
			service.save(menu);
			msgMap.put("t", menu);
			setMsgMap(SUCCESS, "菜单信息增加成功！");
		}catch(Exception e){
			setMsgMap(FAILURE, "菜单信息增加失败！");
			e.printStackTrace();
		}
	   return msgMap;
	}
	/**
	 * 删除菜单
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年12月23日上午9:36:17</br>
	 */
	@ResponseBody
	@RequestMapping("delMenu")
	public Map<String, Object> delMenu(Menu menu){
		try{
			service.delete(menu);
			setMsgMap(SUCCESS,  "菜单信息删除成功！");
			msgMap.put("t", menu);
		}catch(Exception e){
			setMsgMap(FAILURE, "菜单信息删除失败！");
			e.printStackTrace();
		}
	   return msgMap;
	}
	/**
	 * 更新菜单
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年12月23日上午9:36:28</br>
	 */
	@ResponseBody
	@RequestMapping("updMenu")
	public Map<String,Object> updMenu(@RequestBody Menu menu){
		try{
			service.update(menu);
			msgMap.put(SUCCESS, menu);
			msgMap.put("t", menu);
			setMsgMap(SUCCESS,  "菜单信息更新成功！");
		}catch(Exception e){
			setMsgMap(FAILURE, "菜单信息更新失败！");
			e.printStackTrace();
		}
	   return msgMap;
	}
	/**
	 * 获取子菜单信息
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年12月23日上午9:36:41</br>
	 */
	@ResponseBody
	@RequestMapping("list")
	public List<Menu> list(String pid){
		try{
			 List<Menu> menus = service.getMenus(pid);
			 return menus ;
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList<Menu>();
		}
	 
	}
	/**
	 * 获取菜单详情
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年12月23日上午9:37:00</br>
	 */
	@ResponseBody
	@RequestMapping("getMenu")
	public Menu getOne(Menu Menu){
		try{
			Menu t = (Menu) service.get(Menu.getId());
			return t;
		}catch(Exception e){
			return new Menu();
		}	  
	}
	/**
	 * 系统编辑菜单树改造，采用ligerui编写
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年12月22日下午4:49:39</br>
	 */
	@ResponseBody
	@RequestMapping("getMenuTree")
	public List<Map<String, Object>> getMenuTree(){
		return service.getSysMenus();
	}
	/**
	 * 系统编辑菜单树改造，采用amazeui编写
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年12月22日下午4:49:39</br>
	 */
	@ResponseBody
	@RequestMapping("getMenuTree1")
	public List<Map<String, Object>> getMenuTree1(){
		return service.getSysMenus1();
	}
	@ResponseBody
	@RequestMapping("getParents")
	public Map<String,Object> getMenuTree(String id){
		return service.getParents(id);
	}
	
	@ResponseBody
	@RequestMapping("saveRoleMenu")
	public Map<String, Object> saveRM(@RequestParam(value = "leafIds[]") String[] leafIds,String roleId){
		try{
			service.saveRoleMenus(leafIds, roleId);
			setMsgMap(SUCCESS,  "角色权限更新成功！");
		}catch(Exception e){
			setMsgMap(FAILURE,"角色权限更新失败！");
			e.printStackTrace();
		}
		return msgMap;
	}
	/**
	 * 
	 * 作者:iechenyb</br>
	 * 功能描述： 获取用户菜单</br>
	 * 创建时间：2017年1月3日上午10:47:40</br>
	   @param userId
	   @return
	 */
	@ResponseBody
	@RequestMapping("getUserMenu")
	public Set<String> getAssignedUserMenu(String userId){
		Map<String,Object> data = service.getUserMenus(userId);
		return data.keySet();
	}
	/**
	 * 
	 * 作者:iechenyb</br>
	 * 功能描述： 获取角色菜单</br>
	 * 创建时间：2017年1月3日上午10:47:27</br>
	   @param roleId
	   @return
	 */
	@ResponseBody
	@RequestMapping("getRoleMenu")
	public Set<String> getAssignedRoleMenu(String roleId){
		Map<String,Object> data = service.getRoleMenus(roleId);
		return data.keySet();
	}
	//ligerui页面树接口
	@ResponseBody
	@RequestMapping("getUserMenusTree")
	public List<Map<String,Object>> getUserMenusTree(HttpServletRequest req) {
		User user = (User) req.getSession().getAttribute(Contants.SSEIONUSERKEY);
		return service.getUserMenusTree(user.getId());
	}
	//amazeui页面树接口
	@ResponseBody
	@RequestMapping("getUserMenusTree1")
	public List<Map<String,Object>> getUserMenusTree1(HttpServletRequest req) {
		User user = (User) req.getSession().getAttribute(Contants.SSEIONUSERKEY);
		return service.getUserMenusTree1(user.getId());
	}
}
