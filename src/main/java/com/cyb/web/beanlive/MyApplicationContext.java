package com.cyb.web.beanlive;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月22日
 */
	public class MyApplicationContext implements BeanNameAware,ApplicationContextAware{
		Log log = LogFactory.getLog(MyApplicationContext.class);
		private String beanName;

	    //注入的beanName即为MyApplicationContext在BeanFactory配置中的名字（根据情况是id或者name）
	    public void setBeanName(String beanName) {
	        this.beanName = beanName;
	        System.out.println("MyApplicationContext beanName:"+beanName);
	    }

	    @Override
	    public void setApplicationContext(ApplicationContext context)
	            throws BeansException {
	        //通过重写的接口方法，获取spring容器实例context，进而获取容器中相关bean资源
	        System.out.println(context.getBean(this.beanName).hashCode());

	    }
}
