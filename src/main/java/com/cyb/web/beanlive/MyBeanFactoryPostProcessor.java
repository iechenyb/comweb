package com.cyb.web.beanlive;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: bean工厂后置处理逻辑:可以修改bean的属性<br>
 * 创建时间: 2017年8月22日
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	Log log = LogFactory.getLog(MyBeanFactoryPostProcessor.class);

	public MyBeanFactoryPostProcessor() {
		super();
		log.info("这是BeanFactoryPostProcessor实现类构造器！！");
	}

	public void postProcessBeanFactory(ConfigurableListableBeanFactory arg0) throws BeansException {
		log.info("BeanFactoryPostProcessor调用postProcessBeanFactory方法");
		BeanDefinition bd = arg0.getBeanDefinition("person");
		bd.getPropertyValues().addPropertyValue("phone", 110);
	}
}
