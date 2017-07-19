package com.cyb.web.constant;

/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月18日
 */
public enum Config {
	ENVIRONMENTAL("environmental"),DEV("dev"),TEST("test"),PRO("pro");
	public String value;
	private Config(String value){
		this.value = value;
	}
}
