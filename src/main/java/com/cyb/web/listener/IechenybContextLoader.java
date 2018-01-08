package com.cyb.web.listener;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年1月8日
 */
public class IechenybContextLoader extends ContextLoader {
	Log log = LogFactory.getLog(IechenybContextLoader.class);

	@Override
	protected void customizeContext(ServletContext servletContext,
			ConfigurableWebApplicationContext applicationContext) {
		XmlWebApplicationContext context = (XmlWebApplicationContext) applicationContext;
		context.setAllowBeanDefinitionOverriding(false);
	}
}
