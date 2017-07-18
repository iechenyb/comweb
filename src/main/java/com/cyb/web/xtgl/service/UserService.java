package com.cyb.web.xtgl.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyb.web.base.service.HibernateBaseService;
import com.cyb.web.xtgl.dao.UserDao;
import com.cyb.web.xtgl.po.User;
import com.cyb.web.xtgl.po.UserRole;
/**
 * 
 * 功能描述：用户管理
 * 作者：iechenyb
 * 创建时间：2017年1月3日上午10:49:26
 */
@Service("userService")
public class UserService extends HibernateBaseService<User>{
	@Resource(name="userDao")
	UserDao dao ;
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月2日上午9:09:51</br>
	 */
	public List<User> getUsers(){
		return dao.getUsers();
	}
	/**
	 * 
	 * 作者:iechenyb</br>
	 * 功能描述： 保存用户角色</br>
	 * 创建时间：2017年1月3日上午10:49:43</br>
	   @param roleIds
	   @param userId
	 */
	
	public void saveUserRoles(String[] roleIds,String userId){
		dao.deleteUserRole(userId);//清除已经存在的用户角色
		UserRole ur = null;
		if(roleIds!=null&&userId!=null){
			for(int i=0;i<roleIds.length;i++){
				ur = new UserRole();
				ur.setRoleId(roleIds[i]);
				ur.setUserId(userId);
				this.dao.save(ur);//新增用户角色关系
				ur = null;
			}
		}
	}
	public List<UserRole> getUserRole(String userId){
		return dao.getUserRole(userId);
	}
	public User getUserByNameAndPwd(String username,String password){
		return this.dao.getUserByNameAndPwd(username, password);
	}
	public User getUserByName(String username){
		return this.dao.getUserByName(username);
	}
}
