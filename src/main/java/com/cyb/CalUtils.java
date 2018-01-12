package com.cyb;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.druid.filter.config.ConfigTools;
import com.cyb.redis.TB_A;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年11月7日
 */
public class CalUtils {
	Log log = LogFactory.getLog(CalUtils.class);
	public static void main(String[] args) {
		try {
			TB_A a;
			System.out.println("加密："+ConfigTools.encrypt("123"));
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	public static TB_A calLjdwjz(TB_A cur,TB_A pre){
		cur.setSrqy(pre.getDrqy());//计算当日的上日权益
		cur.setDrjyk(cur.getDrqy()-cur.getSrqy()-cur.getDrjrj());//计算当日净盈亏
		cur.setDrljjrj(pre.getDrljjrj()+cur.getDrjrj());
		//计算截至当日累计净入金=前n-1日累计净入金+当日最大净入金
		cur.setJzdrljjrj(pre.getDrljjrj()+cur.getDrzdjrj());
		//计算最大累计净入金=前n日大于大于0的最大值
		double zdljjrj = getMax(getMax(cur.getJzdrljjrj(),pre.getJzdrljjrj()),0);
		cur.setZdljjrj(zdljjrj);//保存最大累计净入金
		//计算单位份数开始
		if(cur.getDrzdjrj()>0&&cur.getJzdrljjrj()==cur.getZdljjrj()){
			if(pre.getLjdwjz()>0.00000001){
				cur.setDrdwfs((cur.getDrzdjrj()+cur.getSrqy())/pre.getLjdwjz());
			}else{
				cur.setDrdwfs(pre.getDrdwfs());
			}
		}else if(cur.getDrzdjrj()==0||(cur.getDrzdjrj()>0&&cur.getJzdrljjrj()<cur.getZdljjrj())){
			cur.setDrdwfs(pre.getDrdwfs());
		}
		//计算单位份数结束
		
		//计算当日单位出金开始
	   if(cur.getZdljjrj()==cur.getDrjrj()&&cur.getJzdrljjrj()==cur.getDrzdjrj()){
		  cur.setDrdwcj(0);
		  cur.setDrljdwcj(0);
		  if(cur.getDrdwfs()>0.0000001){
			  cur.setDrdwjz(cur.getDrqy()/cur.getDrdwfs());
		  }
	   }else if(cur.getZdljjrj()>cur.getDrjrj()&&cur.getJzdrljjrj()==cur.getDrzdjrj()){
		   if(cur.getDrdwfs()>0.0000001){
			   cur.setDrdwcj((cur.getDrzdjrj()-cur.getDrjrj())/cur.getDrdwfs());
		   }
		  cur.setDrljdwcj((cur.getDrdwcj()));
		  cur.setDrdwjz((cur.getDrqy()-cur.getDrjrj()+cur.getDrzdjrj())/cur.getDrdwfs() - cur.getDrdwcj());
       }else if(cur.getJzdrljjrj()<cur.getZdljjrj()){
    	   if(cur.getDrdwfs()>0.0000001){
			  cur.setDrdwcj(-cur.getDrjrj()/cur.getDrdwfs());
			  cur.setDrljdwcj(pre.getJzdrljjrj()+cur.getDrdwcj());
			  cur.setDrdwjz(pre.getDrdwjz()-cur.getDrdwcj()+cur.getDrjyk()/cur.getDrdwfs());
    	   }
       }else{//所有条件均不满足
		   cur.setDrljdwcj(pre.getDrljdwcj());
		   cur.setDrdwcj(0);
		   cur.setDrdwjz(1);
	   }
		//计算当日单位出金结束
	    cur.setLjdwjz(cur.getDrdwjz()+cur.getDrljdwcj()); //计算累计单位净值);
	    cur.setLjsyl(cur.getLjdwjz()-1);//计算累计收益率
		return cur;
	}
	
	public static double getMax(double val1,double val2){
		return val1>val2?val1:val2;
	}
	
}
