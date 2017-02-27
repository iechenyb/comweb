package com.cyb.web.xtgl.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cyb.web.base.service.HibernateBaseService;
import com.cyb.web.xtgl.dao.MenuDao;
import com.cyb.web.xtgl.dao.UserDao;
import com.cyb.web.xtgl.po.Menu;
import com.cyb.web.xtgl.po.RoleMenu;
/**
 * 
 * 功能描述：菜单管理
 * 作者：iechenyb
 * 创建时间：2017年1月3日上午10:48:14
 */

@Service("menuService")
public class MenuService extends HibernateBaseService<Menu>{
	@Resource(name="menuDao")
	MenuDao dao ;
	
	@Resource(name="userDao")
	UserDao userDao ;
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月2日上午9:09:51</br>
	 */
	public List<Menu> getMenus(String pid){
		return dao.getMenus(pid);
	}
	public List<Map<String,Object>> getSysMenus() {
		return dao.getSysMenus();
	}
	public List<Map<String,Object>> getSysMenus1() {
		return dao.getSysMenus1();
	}
	public Map<String,Object> getParents(String leafId){
		return dao.getParents(leafId);
	}
	/**
	 * 
	 * 作者:iechenyb</br>
	 * 功能描述： 获取角色的菜单信息</br>
	 * 创建时间：2017年1月3日上午10:48:29</br>
	   @param leafIds 菜单的ids
	   @param roleId 角色id
	 */
	public void saveRoleMenus(String[] leafIds,String roleId){
		dao.deleteRoleMenu(roleId);//清除已分配的菜单权限
		//根据传递的所有叶子节点，找到所有的叶子及上层到根菜单信息 ；
		Map<String,Object> mapTreeFull = new HashMap<String, Object>();
		if(leafIds!=null){
			for(int i=0;i<leafIds.length;i++){
				mapTreeFull.putAll(dao.getParents(leafIds[i]));
			}
		}
		RoleMenu rm = null;
		//保存完整的菜单权限树
		Iterator<String> menus = mapTreeFull.keySet().iterator();
		while(menus.hasNext()){
			rm = new RoleMenu();
			rm.setMenuId(menus.next());
			rm.setRoleId(roleId);
			dao.save(rm);
			rm = null;
		}
	}
	
	public Map<String,Object> getUserMenus(String userId){
		List<RoleMenu> urs = dao.getUserMenus(userId);
		Map<String,Object> mapTreeFull = new HashMap<String, Object>();
		if(!CollectionUtils.isEmpty(urs)){
			for(RoleMenu rm:urs){
				mapTreeFull.putAll(dao.getParents(rm.getMenuId()));
			}
		}
		return mapTreeFull;
	}
	
	public Map<String,Object> getRoleMenus(String userId){
		List<RoleMenu> urs = dao.getRoleMenus(userId);
		Map<String,Object> mapTreeFull = new HashMap<String, Object>();
		if(!CollectionUtils.isEmpty(urs)){
			for(RoleMenu rm:urs){
				mapTreeFull.putAll(dao.getParents(rm.getMenuId()));
			}
		}
		return mapTreeFull;
	}
	public List<Map<String,Object>> getUserMenusTree(String userId) {
		return dao.getUserMenusTree(userId);
	}
	public List<Map<String,Object>> getUserMenusTree1(String userId) {
		return dao.getUserMenusTree1(userId);
	}
}
