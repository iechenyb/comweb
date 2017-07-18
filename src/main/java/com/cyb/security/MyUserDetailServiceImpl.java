package com.cyb.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cyb.jiami.MD5Util;
import com.cyb.web.xtgl.dao.RoleDao;
import com.cyb.web.xtgl.dao.UserDao;
import com.cyb.web.xtgl.po.Role;
import com.cyb.web.xtgl.po.UserRole;

//@Service("myUserDetailServiceImpl")
public class MyUserDetailServiceImpl implements UserDetailsService {
	@Resource(name="userDao")
	UserDao userDao;
	@Resource(name="roleDao")
	RoleDao roleDao;
	// 登录验证
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.err.println("-----------MyUserDetailServiceImpl loadUserByUsername ----------- "+username);
		com.cyb.web.xtgl.po.User user = userDao.getUserByName(username);
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);
		String password = "";
		try {
			 password = MD5Util.md5Encode(user.getPassword()+"{"+user.getUsername()+"}");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(password);
		//password + "{" + salt.toString() + "}";
		// 封装成spring security的user
		User userdetail = new User(
				user.getUsername(), 
				password,
				true, 
				true, 
				true,
				true, 
				grantedAuths	//用户的权限
			);
		return userdetail;
	}

	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(com.cyb.web.xtgl.po.User user) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		List<UserRole> urs = userDao.getUserRole(user.getId());
		for(UserRole ur:urs){
			Role role = (Role) roleDao.load(Role.class, ur.getRoleId());
			authSet.add(new SimpleGrantedAuthority("ROLE_"+role.getRolename().toUpperCase()));
		}
		return authSet;
	}
}