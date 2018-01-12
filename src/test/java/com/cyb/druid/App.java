package com.cyb.druid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.druid.filter.config.ConfigTools;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年1月9日
 */
public class App {
	Log log = LogFactory.getLog(App.class);
	public static void main(String[] args) throws Exception {
		String res = ConfigTools.encrypt("123werwer!");
		System.out.println("加密："+res);
		System.out.println("揭秘："+ConfigTools.decrypt(res));
	}
}
