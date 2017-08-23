package com.cyb.web.abstr.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 *作者 : iechenyb<br>
 *类描述: 抽象类可以没有任何方法<br>
 *创建时间: 2017年8月23日
 */
//@Component("abstractImpl")//可以不用组件化
public abstract class AbstractImpl implements IAbstract{
	Log log = LogFactory.getLog(AbstractImpl.class);
	public void print(String str){
		log.info("抽象类定义的方法："+str);
	}
	public abstract void println(String str);
}
