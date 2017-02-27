package com.cyb.web.xtgl.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.cyb.web.base.dao.HibernateBaseDao;
import com.cyb.web.xtgl.po.Menu;
import com.cyb.web.xtgl.po.RoleMenu;

@Repository("menuDao")
public class MenuDao extends HibernateBaseDao<Object>{
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月2日上午9:09:37</br>
	 */
	public Menu queryMenu(String id){
		Object obj =  this.getSession().get(Menu.class, id);
		if(obj!=null){
			return (Menu)obj;
		}else{
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Menu> getMenus(String pid){
		Object obj = this.getSession().createQuery("from Menu where parentId=? order by ordor ").setString(0, pid).setCacheable(true).list();
		if(obj!=null){
			return (List<Menu>)obj;
		}else{
			return null;
		}
	}
	//-------------------------------liger tree 数据接口定义begin------------------------
	public List<Map<String,Object>> getSysMenus() {
		Map<String,Object> root = new LinkedHashMap<String, Object>();
		root.put("id", "menuroot");
		root.put("pid",  "-1");
		root.put("text", "系统菜单");
		root.put("isleaf", 0);
		List<Map<String,Object>> children = new ArrayList<Map<String,Object>>();
		@SuppressWarnings("unchecked")
		List<Menu> firstRoots = this.getSession().createQuery(" from Menu where parentId = 'menuroot' order by ordor").setCacheable(true).list();//查询一级菜单
		if(!CollectionUtils.isEmpty(firstRoots)){
			for(int i=0;i<firstRoots.size();i++){
				Map<String,Object> nodeTmp = new LinkedHashMap<String, Object>();
				nodeTmp.put("text",firstRoots.get(i).getMenuName());
				nodeTmp.put("pid",firstRoots.get(i).getParentId());
				nodeTmp.put("isleaf",firstRoots.get(i).getIsLeaf());
				nodeTmp.put("id",firstRoots.get(i).getId());
				nodeTmp.put("url",firstRoots.get(i).getUrl());
				nodeTmp.put("children", new ArrayList<Map<String,Object>>());
				children.add(nodeTmp);
			}
			buildTree(children);
		}
		if(children.size()>0){
			root.put("children",children);
		}
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		data.add(root);
		return data;
	}
	@SuppressWarnings("unchecked")
	public void buildTree(List<Map<String,Object>> children){
		for(Map<String,Object> child:children){
			List<Menu> nodes = this.getSession().createQuery(" from Menu where parentId = '"+child.get("id").toString()+"' order by ordor").setCacheable(true).list();//查询一级菜单
			if(!CollectionUtils.isEmpty(nodes)){//存在子节点
				List<Map<String,Object>> childs = (List<Map<String, Object>>)child.get("children");
				for(int j=0;j<nodes.size();j++){//获取所有的子节点
					Map<String,Object> nodeTmp = new LinkedHashMap<String, Object>();
					nodeTmp.put("text",nodes.get(j).getMenuName());
					nodeTmp.put("pid",nodes.get(j).getParentId());
					nodeTmp.put("id",nodes.get(j).getId());
					nodeTmp.put("url",nodes.get(j).getUrl());
					nodeTmp.put("isleaf",nodes.get(j).getIsLeaf());
					nodeTmp.put("children", new ArrayList<Map<String,Object>>());
					childs.add(nodeTmp);
				}
			    buildTree(childs);
			}else{//不存在子节点
				if("1".equals(child.get("isleaf").toString())){
					child.remove("children");
				}/*else{
					child.put("children",new HashMap<String, String>());
				}*/
			}
		}
	}
	public Map<String,Object> getParents(String leafId){
		Menu root = new Menu();
		root.setParentId("-1");
		root.setId("menuroot");
		root.setIsLeaf(0);
		root.setMenuName("系统菜单");
		Map<String, Object> rootM = new HashMap<String, Object>();
		rootM.put(root.getId(), root);
		if(!"menuroot".equals(leafId)){//除掉根节点
			Object obj =  this.getSession().get(Menu.class, leafId);
			if(obj!=null){
				Menu m = (Menu)obj;
				rootM.put(m.getId(), m);
				buildParents(rootM,m);
			}
		}
		return rootM;
	}
	
	public void buildParents(Map<String,Object> ps ,Menu menu){
		if(!"menuroot".equals(menu.getParentId())){//是否遍历到根节点
		    Object obj =  this.getSession().get(Menu.class, menu.getParentId());
			if(obj!=null){
				Menu m = (Menu)obj;	
				ps.put(m.getId(), m);
				buildParents(ps,m);
			}
		}
	}
	public void deleteRoleMenu(String roleId){
		String sql = "delete RoleMenu as rm where rm.roleId=?";
		this.getSession().createQuery(sql).setString(0,roleId).executeUpdate();
	}
	@SuppressWarnings("unchecked")
	public List<RoleMenu> getRoleMenus(String roleId){
		try{
			String sql = "select distinct rm from RoleMenu rm where roleId = ?";
			return this.getSession().createQuery(sql).setString(0, roleId).setCacheable(true).list();
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList<RoleMenu>();
		}
	}
	@SuppressWarnings("unchecked")
	public List<RoleMenu> getUserMenus(String userId){
		try{
			String sql = "select distinct rm from RoleMenu rm where roleId in (select roleId from UserRole where userId=?)";
			return this.getSession().createQuery(sql).setString(0, userId).setCacheable(true).list();
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList<RoleMenu>();
		}
	}
	
	
	
	public List<Map<String,Object>> getUserMenusTree(String userId) {
		Map<String,Object> root = new LinkedHashMap<String, Object>();
		root.put("id", "menuroot");
		root.put("pid",  "-1");
		root.put("text", "系统菜单");
		root.put("isleaf", 0);
		List<Map<String,Object>> children = new ArrayList<Map<String,Object>>();
		@SuppressWarnings("unchecked")
		List<Menu> firstRoots = this.getSession().createQuery("select distinct m from Menu m,RoleMenu rm"
				+ " where m.parentId = 'menuroot' "
				+ " and m.id = rm.menuId"
				+ " and rm.roleId in (select roleId from UserRole where userId=?) order by m.ordor")
				.setString(0, userId)
				.setCacheable(true)
				.list();
		if(!CollectionUtils.isEmpty(firstRoots)){
			for(int i=0;i<firstRoots.size();i++){
				Map<String,Object> nodeTmp = new LinkedHashMap<String, Object>();
				nodeTmp.put("text",firstRoots.get(i).getMenuName());
				nodeTmp.put("pid",firstRoots.get(i).getParentId());
				nodeTmp.put("isleaf",firstRoots.get(i).getIsLeaf());
				nodeTmp.put("id",firstRoots.get(i).getId());
				nodeTmp.put("url",firstRoots.get(i).getUrl());
				nodeTmp.put("children", new ArrayList<Map<String,Object>>());
				children.add(nodeTmp);
			}
			buildUserMenuTree(children,userId);
		}
		if(children.size()>0){
			root.put("children",children);
		}
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		data.add(root);
		return data;
	}
	@SuppressWarnings("unchecked")
	public void buildUserMenuTree(List<Map<String,Object>> children,String userId){
		for(Map<String,Object> child:children){
			List<Menu> nodes = this.getSession().createQuery("select distinct m from Menu m,RoleMenu rm"
					+ " where m.parentId = ? "
					+ " and m.id = rm.menuId"
					+ " and rm.roleId in (select roleId from UserRole where userId=?) order by m.ordor")
					.setString(0, child.get("id").toString())
					.setString(1, userId)
					.setCacheable(true)
					.list();
			if(!CollectionUtils.isEmpty(nodes)){//存在子节点
				List<Map<String,Object>> childs = (List<Map<String, Object>>)child.get("children");
				for(int j=0;j<nodes.size();j++){//获取所有的子节点
					Map<String,Object> nodeTmp = new LinkedHashMap<String, Object>();
					nodeTmp.put("text",nodes.get(j).getMenuName());
					nodeTmp.put("pid",nodes.get(j).getParentId());
					nodeTmp.put("id",nodes.get(j).getId());
					nodeTmp.put("url",nodes.get(j).getUrl());
					nodeTmp.put("isleaf",nodes.get(j).getIsLeaf());
					nodeTmp.put("children", new ArrayList<Map<String,Object>>());
					childs.add(nodeTmp);
				}
			    buildTree(childs);
			}else{
				if("1".equals(child.get("isleaf").toString())){
					child.remove("children");
				}
			}
		}
	}
	//-------------------------------liger tree 数据接口定义over------------------------
	//-------------------------------amazui tree 数据接口定义begin------------------------
	/**
	 * 系统菜单树
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年12月22日下午5:02:30</br>
	 */
	public List<Map<String,Object>> getUserMenusTree1(String userId) {
		Map<String,Object> root = new LinkedHashMap<String, Object>();
		root.put("id", "menuroot");
		root.put("pid",  "-1");
		root.put("title", "系统菜单");
		root.put("isleaf", 0);
		root.put("type", "folder");
		List<Map<String,Object>> children = new ArrayList<Map<String,Object>>();
		@SuppressWarnings("unchecked")
		List<Menu> firstRoots = this.getSession().createQuery("select distinct m from Menu m,RoleMenu rm"
				+ " where m.parentId = 'menuroot' "
				+ " and m.id = rm.menuId"
				+ " and rm.roleId in (select roleId from UserRole where userId=?) order by m.ordor")
				.setString(0, userId)
				.setCacheable(true)
				.list();
		if(!CollectionUtils.isEmpty(firstRoots)){
			for(int i=0;i<firstRoots.size();i++){
				Map<String,Object> nodeTmp = new LinkedHashMap<String, Object>();
				nodeTmp.put("title",firstRoots.get(i).getMenuName());
				nodeTmp.put("pid",firstRoots.get(i).getParentId());
				nodeTmp.put("isleaf",firstRoots.get(i).getIsLeaf());
				if(firstRoots.get(i).getIsLeaf()==0){//非叶子
					nodeTmp.put("type", "folder");
				}else{
					nodeTmp.put("type", "item");
				}
				nodeTmp.put("id",firstRoots.get(i).getId());
				nodeTmp.put("url",firstRoots.get(i).getUrl());
				nodeTmp.put("products", new ArrayList<Map<String,Object>>());
				children.add(nodeTmp);
			}
			buildUserMenuTree1(children,userId);
		}
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		root.put("products",children);
		data.add(root);//根节点不要展示
		return children;
	}
	@SuppressWarnings("unchecked")
	public void buildUserMenuTree1(List<Map<String,Object>> children,String userId){
		for(Map<String,Object> child:children){
			List<Menu> nodes = this.getSession().createQuery("select distinct m from Menu m,RoleMenu rm"
					+ " where m.parentId = ? "
					+ " and m.id = rm.menuId"
					+ " and rm.roleId in (select roleId from UserRole where userId=?) order by m.ordor")
					.setString(0, child.get("id").toString())
					.setString(1, userId)
					.setCacheable(true)
					.list();
			if(!CollectionUtils.isEmpty(nodes)){//存在子节点
				List<Map<String,Object>> childs = (List<Map<String, Object>>)child.get("products");
				for(int j=0;j<nodes.size();j++){//获取所有的子节点
					Map<String,Object> nodeTmp = new LinkedHashMap<String, Object>();
					nodeTmp.put("title",nodes.get(j).getMenuName());
					nodeTmp.put("pid",nodes.get(j).getParentId());
					nodeTmp.put("id",nodes.get(j).getId());
					nodeTmp.put("url",nodes.get(j).getUrl());
					nodeTmp.put("isleaf",nodes.get(j).getIsLeaf());
					if(nodes.get(j).getIsLeaf()==0){//非叶子
						nodeTmp.put("type", "folder");
					}else{
						nodeTmp.put("type", "item");
					}
					nodeTmp.put("products", new ArrayList<Map<String,Object>>());
					childs.add(nodeTmp);
				}
				buildUserMenuTree1(childs,userId);
			}
		}
	}
	@SuppressWarnings("unchecked")
	public void buildTree1(List<Map<String,Object>> children){
		for(Map<String,Object> child:children){
			List<Menu> nodes = this.getSession().createQuery(" from Menu where parentId = '"+child.get("id").toString()+"' order by ordor").setCacheable(true).list();//查询一级菜单
			if(!CollectionUtils.isEmpty(nodes)){//存在子节点
				List<Map<String,Object>> childs = (List<Map<String, Object>>)child.get("products");
				for(int j=0;j<nodes.size();j++){//获取所有的子节点
					Map<String,Object> nodeTmp = new LinkedHashMap<String, Object>();
					nodeTmp.put("title",nodes.get(j).getMenuName());
					nodeTmp.put("pid",nodes.get(j).getParentId());
					nodeTmp.put("id",nodes.get(j).getId());
					nodeTmp.put("url",nodes.get(j).getUrl());
					nodeTmp.put("isleaf",nodes.get(j).getIsLeaf());
					if(nodes.get(j).getIsLeaf()==0){//非叶子
						nodeTmp.put("type", "folder");
					}else{
						nodeTmp.put("type", "item");
					}
					nodeTmp.put("products", new ArrayList<Map<String,Object>>());
					childs.add(nodeTmp);
				}
			    buildTree1(childs);
			}
		}
	}
	
	public List<Map<String,Object>> getSysMenus1() {
		Map<String,Object> root = new LinkedHashMap<String, Object>();
		root.put("id", "menuroot");
		root.put("pid",  "-1");
		root.put("title", "系统菜单");
		root.put("isleaf", 0);
		root.put("type", "folder");
		List<Map<String,Object>> children = new ArrayList<Map<String,Object>>();
		@SuppressWarnings("unchecked")
		List<Menu> firstRoots = this.getSession().createQuery(" from Menu where parentId = 'menuroot' order by ordor").setCacheable(true).list();//查询一级菜单
		if(!CollectionUtils.isEmpty(firstRoots)){
			for(int i=0;i<firstRoots.size();i++){
				Map<String,Object> nodeTmp = new LinkedHashMap<String, Object>();
				nodeTmp.put("title",firstRoots.get(i).getMenuName());
				nodeTmp.put("pid",firstRoots.get(i).getParentId());
				nodeTmp.put("isleaf",firstRoots.get(i).getIsLeaf());
				if(firstRoots.get(i).getIsLeaf()==0){//非叶子
					nodeTmp.put("type", "folder");
				}else{
					nodeTmp.put("type", "item");
				}
				nodeTmp.put("id",firstRoots.get(i).getId());
				nodeTmp.put("url",firstRoots.get(i).getUrl());
				nodeTmp.put("products", new ArrayList<Map<String,Object>>());
				children.add(nodeTmp);
			}
			buildTree1(children);
		}
		root.put("products",children);
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		data.add(root);
		return data;
	}
	//-------------------------------amazui tree 数据接口定义结束------------------------
}
