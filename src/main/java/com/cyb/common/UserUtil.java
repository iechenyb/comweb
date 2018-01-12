package com.cyb.common;

import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.MDC;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥http://blog.didispace.com/cxy-wsm-zml-6/<br>
 * 创建时间: 2018年1月10日
 */
public class UserUtil {
	Log log = LogFactory.getLog(UserUtil.class);
	private final static ThreadLocal<String> tlUser = new ThreadLocal<String>();

	private final static ThreadLocal<Locale> tlLocale = new ThreadLocal<Locale>() {
		protected Locale initialValue() {
			// 语言的默认值
			return Locale.CHINESE;
		};
	};

	public static final String KEY_LANG = "lang";

	public static final String KEY_USER = "user";

	public static void setUser(String userid) {
		tlUser.set(userid);
		// 把用户信息放到log4j
		MDC.put(KEY_USER, userid);
	}

	public static String getUser() {
		return tlUser.get();
	}

	public static void setLocale(String locale) {
		setLocale(new Locale(locale));
	}

	public static void setLocale(Locale locale) {
		tlLocale.set(locale);
	}

	public static Locale getLocale() {
		return tlLocale.get();
	}

	public static void clearAllUserInfo() {
		tlUser.remove();
		tlLocale.remove();
		MDC.remove(KEY_USER);
	}
}
