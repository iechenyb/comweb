package com.cyb.web.xtgl.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cyb.web.base.controller.BaseController;
import com.cyb.web.constant.Contants;
import com.cyb.web.utils.MD5Util;
import com.cyb.web.xtgl.po.User;
import com.cyb.web.xtgl.po.UserRole;
import com.cyb.web.xtgl.service.UserService;
import com.cyb.web.xtgl.vo.UserVo;
/**
 * 
 * 功能描述：用户管理
 * 作者：iechenyb
 * 创建时间：2017年1月3日上午10:43:34
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {
	private Log log = LogFactory.getLog(UserController.class);
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月2日上午9:08:29</br>
	 */
	@Resource(name="userService")
	UserService service;
	/**
	 * 
	 * 作者:iechenyb</br>
	 * 功能描述： 新增用户信息</br>
	 * 创建时间：2017年1月3日上午10:43:45</br>
	   @param user
	   @return
	 */
	@ResponseBody
	@RequestMapping("addUser")
	public Map<String, Object> addUser(@RequestBody User user){
		try{
			user.setPassword(MD5Util.md5Encode(Contants.DEFAULTPASSWORD));
			service.save(user);
			setMsgMap(SUCCESS,"用户信息增加成功！");
			msgMap.put("t", user);
		}catch(Exception e){
			setMsgMap(FAILURE, "用户信息增加失败！");
			e.printStackTrace();
		}
	   return msgMap;
	}
	/**
	 * 
	 * 作者:iechenyb</br>
	 * 功能描述： 删除用户信息</br>
	 * 创建时间：2017年1月3日上午10:43:45</br>
	   @param user
	   @return
	 */
	@ResponseBody
	@RequestMapping("delUser")
	public Map<String, Object> delUser(User User){
		try{
			service.delete(User);
			setMsgMap(SUCCESS, "用户信息删除成功！");
		}catch(Exception e){
			setMsgMap(FAILURE, "用户信息删除失败！");
			e.printStackTrace();
		}
	   return msgMap;
	}
	/**
	 * 
	 * 作者:iechenyb</br>
	 * 功能描述： 更新用户信息</br>
	 * 创建时间：2017年1月3日上午10:43:45</br>
	   @param user
	   @return
	 */
	@ResponseBody
	@RequestMapping("updUser")
	public Map<String,Object> updUser(@RequestBody User user){
		try{
			service.update(user);
			setMsgMap(SUCCESS, "用户信息更新成功！");
			msgMap.put("t", user);
		}catch(Exception e){
			setMsgMap(FAILURE, "用户信息更新失败！");
			e.printStackTrace();
		}
	   return msgMap;
	}
	/**
	 * 
	 * 作者:iechenyb</br>
	 * 功能描述： 更新用户密码</br>
	 * 创建时间：2017年1月3日上午10:43:45</br>
	   @param user
	   @return
	 */
	@ResponseBody
	@RequestMapping("updPwd")
	public Map<String,Object> updPassword(@RequestBody User user){
		try{
			User obj = (User) service.get(user.getId());
			obj.setPassword(MD5Util.md5Encode(Contants.DEFAULTPASSWORD));
			setMsgMap(SUCCESS, "用户密码重置成功！");
			service.update(obj);
		}catch(Exception e){
			setMsgMap(FAILURE, "用户密码重置失败！");
			e.printStackTrace();
		}
	   return msgMap;
	}
	/**
	 * 
	 * 作者:iechenyb</br>
	 * 功能描述： 获取用户信息</br>
	 * 创建时间：2017年1月3日上午10:43:45</br>
	   @param user
	   @return
	 */
	@ResponseBody
	@RequestMapping("list")
	public List<User> list(){
		try{
			 List<User> users = service.getUsers();
			 /*if(CollectionUtils.isNotEmpty(users)){
				 for(User u:users){
					 if(StringUtils.isNotEmpty(u.getIdcard())){
					   StringBuilder idcard = new StringBuilder(u.getIdcard());
					    u.setIdcard(idcard.replace(6, 14, "********").toString());
					 }
					 if(StringUtils.isNotEmpty(u.getPhone())){
					   StringBuilder phone = new StringBuilder(u.getPhone());
					   u.setPhone(phone.replace(3, 7, "****").toString());
					 }
				 }
			 }*/
			 return users ;
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList<User>();
		}
	}
	/**
	 * 获取用户名称
	 * 作者:iechenyb</br>
	 * 功能描述： TODO</br>
	 * 创建时间：2017年1月3日上午10:44:39</br>
	   @return
	 */
	@ResponseBody
	@RequestMapping("getNames")
	public List<UserVo> getNames(){
		try{
			 List<User> users = service.getUsers();
			 List<UserVo> users0 = new ArrayList<UserVo>();
			 if(CollectionUtils.isNotEmpty(users)){
				 UserVo vo = null;
				 for(User tmp:users){
					 vo = new UserVo();
					 vo.setId(tmp.getId());
					 vo.setUsername(tmp.getUsername());
					 users0.add(vo);
				 }
			 }
			 return users0 ;
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList<UserVo>();
		}
	 
	}
	/**
	 * 
	 * 作者:iechenyb</br>
	 * 功能描述： 获取用户信息</br>
	 * 创建时间：2017年1月3日上午10:44:57</br>
	   @param User
	   @return
	 */
	@ResponseBody
	@RequestMapping("getUser")
	public User getOne(User User){
		try{
			User t = (User) service.get(User.getId());
			return t;
		}catch(Exception e){
			return new User();
		}	  
	}
	/**
	 * 
	 * 作者:iechenyb</br>
	 * 功能描述： 用户登录</br>
	 * 创建时间：2017年1月3日上午10:45:12</br>
	   @param username
	   @param password
	   @param req
	   @return
	 */
	@ResponseBody
	@RequestMapping("login")
	public JSONObject login(String username,String password,HttpServletRequest req){
		Map<String,Object> res = new HashMap<String,Object>();
		User user = service.getUserByNameAndPwd(username, password);
		if(user!=null){
			req.getSession().setAttribute(Contants.SSEIONUSERKEY, user);
			req.getSession().setAttribute("username", user.getUsername());
			res.put("zt", 1);
			res.put("msg", "登录成功！");
			user.setLoginSum(user.getLoginSum()+1);
			String ip = req.getRemoteAddr();
			if(user.getLastLoginIp()!=ip){
				user.setLastLoginIp(user.getLoginIp());
				user.setLoginIp(ip);
			}
			service.update(user);
			log.info("["+username+"]登录成功，操作IP地址为["+ip+"]");
		}else{
			res.put("zt", 0);
			res.put("msg", "用户名或者密码错误，请重新输入！");
		}
	  return JSONObject.fromObject(res);
	}
	/**
	 * 
	 * 作者:iechenyb</br>
	 * 功能描述： 保存用户角色</br>
	 * 创建时间：2017年1月3日上午10:45:23</br>
	   @param roleIds
	   @param userId
	   @return
	 */
	@ResponseBody
	@RequestMapping("saveUserRole")
	public Map<String, Object> saveUserRole(@RequestParam(value = "roleIds[]") String[] roleIds,@RequestParam(value = "userId") String userId){
		try{
			service.saveUserRoles(roleIds, userId);
			setMsgMap(SUCCESS, "用户角色信息更新成功！");
		}catch(Exception e){
			setMsgMap(FAILURE, "用户角色信息更新失败！");
			e.printStackTrace();
		}
	   return msgMap;
	}
	/**
	 * 
	 * 作者:iechenyb</br>
	 * 功能描述： 获取用户角色</br>
	 * 创建时间：2017年1月3日上午10:45:35</br>
	   @param userId
	   @return
	 */
	@ResponseBody
	@RequestMapping("getUserRole")
	public List<UserRole> getUserRole(String userId){
		return service.getUserRole(userId);
	}
	
	@ResponseBody
	@RequestMapping("exit")
	public ModelAndView exitWeb(HttpServletRequest req){
		ModelAndView view = new ModelAndView();
		HttpSession session = req.getSession();
		log.info("["+session.getAttribute("username")+"]退出成功，操作IP地址为["+req.getRemoteAddr()+"]");
		session.removeAttribute(Contants.SSEIONUSERKEY);
		session.removeAttribute("username");
		view.setViewName("login/login");
		return view;
	}
}
