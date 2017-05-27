package com.cyb.web.quartz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyb.web.quartz.MyJob;

@Controller
@RequestMapping("job")

public class JobController {
  @Autowired
  MyJob job;
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
