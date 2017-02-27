package com.cyb.web.utils;

import java.util.UUID;

public class UUIDUtils {
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月2日下午3:11:29</br>
	 */
	public static String uuid(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
