package com.cyb.web.quartz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyb.web.quartz.MyJob;
/**
 * 定时任务更新管理
 * @author iechenyb
 *
 */
@Controller
@RequestMapping("job")
public class JobController {
  @Autowired
  MyJob job;
  /**
   * 
   *作者 : iechenyb<br>
   *方法描述: 说点啥<br>
   *创建时间: 2017年7月15日hj12
   *@return
   */
  @RequestMapping("infor")
  @ResponseBody
  public boolean info(){
	  try {
		job.infor();
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
  }
  /**
   *  
   *作者 : iechenyb<br>
   *方法描述: 说点啥<br>
   *创建时间: 2017年7月15日hj12
   *@param sec
   *@return
   */
  @RequestMapping("setSec")
  @ResponseBody
  public boolean resetTime(String sec){
	  try {
		job.resetJob("0/"+sec+" * * * * ?");
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
  }
  /**
   * 
   *作者 : iechenyb<br>
   *方法描述: 说点啥<br>
   *创建时间: 2017年7月15日hj12
   *@param sec
   *@return
   */
  @RequestMapping("removeJob")
  @ResponseBody
  public boolean remove(String sec){
	  try {
		job.removeJob();
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
  }
  /**
   * 
   *作者 : iechenyb<br>
   *方法描述: 说点啥<br>
   *创建时间: 2017年7月15日hj12
   *@return
   */
  @RequestMapping("addJob")
  @ResponseBody
  public boolean addJob(){
	  try {
		job.addJob("0/5 * * * * ?");
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
  }
}
