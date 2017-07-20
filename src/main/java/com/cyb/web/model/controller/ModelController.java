package com.cyb.web.model.controller;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyb.web.model.po.Model;
import com.cyb.web.model.service.ModelService;
import com.cyb.web.xtgl.po.User;
/**
 * 作者 : iechenyb
 * 功能描述: 说点啥
 * 创建时间: 2017年07月16日 13时03分26秒
 */
@Controller
@RequestMapping("model")
public class ModelController extends com.cyb.web.base.controller.BaseController {
   Log log = LogFactory.getLog(ModelController.class);
   @Resource(name="modelService")
   ModelService modelService;
   
    /**
    * 功能描述: 说点啥
    * 作者 : iechenyb
 	* 创建时间: 2017年07月16日 13时03分26秒
 	* 参数说明：
    * 返回值说明：
    */
   @ResponseBody
   @RequestMapping("add")
   public Map<String,Object> add(Model object,HttpServletRequest req){
       setMsgMap(SUCCESS, "信息添加成功");
	   try{
	       User user = getUser(req);
		   object.setCzyid(user.getId());
		   object.setCzymc(user.getUsername());
		   object.setCzsj("2017");
		   //modelService.save(object);
		   msgMap.put("t",  object);
	   }catch(Exception e){
		   e.printStackTrace();
		   setMsgMap(FAILURE, "信息添加失败！");
	   }
	   return msgMap;
   }
    /**
    * 功能描述: 更新方法
    * 作者 : iechenyb
 	* 创建时间: 2017年07月16日 13时03分26秒
 	* 参数说明：
    * 返回值说明：
    */
   @ResponseBody
   @RequestMapping("update")
   public Map<String,Object> update(Model object,HttpServletRequest req){
   	   setMsgMap(SUCCESS, "信息更新成功");
	   try{
	   	   User user = getUser(req);
		   object.setCzyid(user.getId());
		   object.setCzymc(user.getUsername());
		   object.setCzsj("");
		   //modelService.update(object);
		   msgMap.put("t",  object);
	   }catch(Exception e){
		   e.printStackTrace();
		   setMsgMap(FAILURE, "信息更新失败！");
	   }
	   return msgMap;
   }
  /**
    * 功能描述: 物理删除数据
    * 作者 : iechenyb
 	* 创建时间: 2017年07月16日 13时03分26秒
 	* 参数说明：
    * 返回值说明：
    */
   @ResponseBody
   @RequestMapping("del")
   public Map<String,Object> delete(String id ,HttpServletRequest req){
       setMsgMap(SUCCESS, "信息删除成功");
	   try{
		   Model object = new Model();
		   object.setId(id);
		  // modelService.delete(object);
	   }catch(Exception e){
		   e.printStackTrace();
		   setMsgMap(FAILURE, "信息删除失败！");
	   }
	   return msgMap;
   }
    /**
    * 功能描述: 逻辑删除数据
    * 作者 : iechenyb
 	* 创建时间: 2017年07月16日 13时03分26秒
 	* 参数说明：
    * 返回值说明：
    */
   @ResponseBody
   @RequestMapping("del1")
   public Map<String,Object> delete1(String id ,HttpServletRequest req){
       setMsgMap(SUCCESS, "信息删除成功");
	   try{ 
		   User user = getUser(req);
		   Model object = (Model) modelService.load(id);
		   object.setCzyid(user.getId());
		   object.setCzymc(user.getUsername());
		   object.setCzsj("");
		   object.setZt(-1);
		   //modelService.update(object);
		   msgMap.put("t",  object);
	   }catch(Exception e){
		   e.printStackTrace();
		   setMsgMap(FAILURE, "信息删除失败！");
	   }
	   return msgMap;
   }
    /**
    * 功能描述: 数据列表
    * 作者 : iechenyb
 	* 创建时间: 2017年07月16日 13时03分26秒
 	* 参数说明：
    * 返回值说明：
    */
   @ResponseBody
   @RequestMapping("list")
   public List<Model> list(){
	   return modelService.list();
   }
   
}