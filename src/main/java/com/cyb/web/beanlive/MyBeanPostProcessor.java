package com.cyb.web.beanlive;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月22日
 */
public class MyBeanPostProcessor implements BeanPostProcessor{
	Log log = LogFactory.getLog(MyBeanPostProcessor.class);
	public MyBeanPostProcessor() {
        super();
        log.info("这是BeanPostProcessor实现类构造器！！");
    }
    //单元测试的时候：参数是调用bean的实例对象com.cyb.inter.SpringBeanLive@5e4aaf28和com.cyb.inter.SpringBeanLive
    @Override
    public Object postProcessAfterInitialization(Object arg0, String arg1)
            throws BeansException {
       log.info(arg0+",BeanPostProcessor接口方法postProcessAfterInitialization对属性进行更改！"+arg1);
        return arg0;
    }

    @Override
    public Object postProcessBeforeInitialization(Object arg0, String arg1)
            throws BeansException {
        log.info(arg0+",BeanPostProcessor接口方法postProcessBeforeInitialization对属性进行更改！"+arg1);
        return arg0;
    }
}
