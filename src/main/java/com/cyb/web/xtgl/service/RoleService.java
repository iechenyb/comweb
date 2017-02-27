package com.cyb.web.xtgl.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyb.web.base.service.HibernateBaseService;
import com.cyb.web.xtgl.dao.RoleDao;
import com.cyb.web.xtgl.po.Role;
/**
 * 
 * 功能描述：角色管理
 * 作者：iechenyb
 * 创建时间：2017年1月3日上午10:49:11
 */
@Service("roleService")
public class RoleService extends HibernateBaseService<Role>{
	@Resource(name="roleDao")
	RoleDao dao ;
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月2日上午9:09:51</br>
	 */
	public List<Role> getRoles(){
		return dao.getRoles();
	}
}
