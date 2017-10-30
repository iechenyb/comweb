package com.cyb.web.beanlive;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年8月22日
 */

public class Person implements 
BeanFactoryAware,
BeanNameAware,
InitializingBean,
DisposableBean {
	Log log = LogFactory.getLog(Person.class);
	private String name;
	private String address;
	private int phone;
    private String initMethod="defualt";
	private BeanFactory beanFactory;
	private String beanName;

	public Person() {
		log.info("【构造器】调用Person的构造器实例化");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		log.info("【注入属性】注入属性name");
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		log.info("【注入属性】注入属性address");
		this.address = address;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		log.info("【注入属性】注入属性phone");
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Person [address=" + address + ", name=" + name + ", phone=" + phone + "]-"+initMethod;
	}

	// 这是BeanFactoryAware接口方法
	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		log.info("【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()");
		this.beanFactory = arg0;
	}

	// 这是BeanNameAware接口方法
	@Override
	public void setBeanName(String arg0) {
		log.info("【BeanNameAware接口】调用BeanNameAware.setBeanName()"+arg0);
		this.beanName = arg0;
	}

	// 这是InitializingBean接口方法
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
	}

	// 这是DiposibleBean接口方法
	@Override
	public void destroy() throws Exception {
		log.info("【DiposibleBean接口】调用DiposibleBean.destory()");
	}

	// 通过<bean>的init-method属性指定的初始化方法
	public void myInit() {
		this.initMethod="init-method modify";
		log.info("【init-method】调用<bean>的init-method属性指定的初始化方法");
	}

	// 通过<bean>的destroy-method属性指定的初始化方法
	public void myDestory() {
		log.info("【destroy-method】调用<bean>的destroy-method属性指定的初始化方法");
	}

	public String getInitMethod() {
		return initMethod;
	}

	public void setInitMethod(String initMethod) {
		this.initMethod = initMethod;
	}
	
}
