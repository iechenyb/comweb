package com.cyb.web.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import com.cyb.web.constant.Contants;
import com.cyb.web.xtgl.po.User;
/**
 * 
 * 功能描述：
 * 作者：iechenyb
 * 创建时间：2016年11月16日下午3:56:08
 */
public class BaseController {
	private Log log = LogFactory.getLog(BaseController.class);
	public static Map<String,Object> msgMap = new HashMap<String, Object>();
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月14日下午4:32:53</br>
	 */
	
	public static final String SUCCESS = "1";

	// jsp 返回
	public static final String FAILURE = "0";

	// app端返回
	public static final String ERROR = "-1";

	// 返回json
	public static final String JSON_VIEW = "json/json";

	/**
	 * 跳转到到登录页面(jsp接口调用)
	 * 
	 */
	public ModelAndView toLoginView() {
		return new ModelAndView("login/login");
	}

    public User getUser(HttpServletRequest req){
    	User user = null ;
    	try{
    		user = (User) req.getSession().getAttribute(Contants.SSEIONUSERKEY);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return user;
    }
	public void setMsgMap(String type,String tip) {
		msgMap.put("zt", type);
		msgMap.put("msg", tip);
	}
	
	@ExceptionHandler
	@ResponseBody
    public Map<String, Object> doException(Exception e,HttpServletRequest request) throws Exception {
        if (e instanceof MaxUploadSizeExceededException) {
            long maxSize = ((MaxUploadSizeExceededException) e)
                    .getMaxUploadSize();
           setMsgMap(FAILURE, "上传文件太大，不能超过" + maxSize / 1024 + "k");
        }else if(e instanceof RuntimeException){
        	setMsgMap(FAILURE, "请求处理异常，请检查！");
        }else{
        	setMsgMap(FAILURE, "上传失败");
        }
        log.info("异常！"+e.toString());
        e.printStackTrace();
        return msgMap;
    }
}