package com.cyb.web.xtgl.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.cyb.web.base.dao.HibernateBaseDao;
import com.cyb.web.utils.MD5Util;
import com.cyb.web.xtgl.controller.UserController;
import com.cyb.web.xtgl.po.User;
import com.cyb.web.xtgl.po.UserRole;

@Repository("userDao")
public class UserDao extends HibernateBaseDao<Object>{
	private Log log = LogFactory.getLog(UserDao.class);
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月2日上午9:09:37</br>
	 */
	public User queryUser(String id){
		Object obj =  this.getSession().load(User.class, id);
		if(obj!=null){
			return (User)obj;
		}else{
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<User> getUsers(){
		Object obj = this.getSession().createQuery("from User ").setCacheable(true).list();
		if(obj!=null){
			return (List<User>)obj;
		}else{
			return null;
		}
	}
	public void deleteUserRole(String userId){
		String sql = "delete UserRole as ur where ur.userId=?";
		this.getSession().createQuery(sql).setString(0,userId).executeUpdate();
	}
	@SuppressWarnings("unchecked")
	public List<UserRole> getUserRole(String userId){
		try{
			String sql = "from UserRole as ur where ur.userId=?";
			return this.getSession().createQuery(sql).setString(0,userId).setCacheable(true).list();
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList<UserRole>();
		}
	}
	public User getUserByNameAndPwd(String username,String password){
		String sql = "from User where empno=? and password = ? and isEffect = 1";
		try{
			return (User) this.getSession().createQuery(sql)
					.setCacheable(true)
					.setString(0, username)
					.setString(1, MD5Util.md5Encode(password)).list().get(0);
		}catch(Exception e){
			log.info(e.toString());
			return null;
		}
	}
	public User getUserByName(String username){
		String sql = "from User where empno=? and isEffect = 1";
		try{
			return (User) this.getSession().createQuery(sql)
					.setCacheable(true)
					.setString(0, username).list().get(0);
		}catch(Exception e){
			log.info(e.toString());
			return null;
		}
	}
}
