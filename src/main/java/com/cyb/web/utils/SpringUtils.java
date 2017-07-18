package com.cyb.web.utils;

import com.cyb.web.constant.Contants;

public class SpringUtils {
 public static Object getBean(String id){
	 return Contants.springContext.getBean(id);
 }
}
