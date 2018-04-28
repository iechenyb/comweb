package com.cyb.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年1月10日
 */
public class ControllerAOP {
	Log log = LogFactory.getLog(ControllerAOP.class);
	private static final Logger logger = LoggerFactory.getLogger(ControllerAOP.class);
	protected Logger getLog(final JoinPoint joinPoint) {
        final Object target = joinPoint.getTarget();
        if (target != null) {
            return LoggerFactory.getLogger(target.getClass());
        }

        return LoggerFactory.getLogger(getClass());
    }
	public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
		long startTime = System.currentTimeMillis();
		ResultBean<?> result;
		try {
			result = (ResultBean<?>) pjp.proceed();
			logger.info(pjp.getSignature() + " use time:" + (System.currentTimeMillis() - startTime));
		} catch (Throwable e) {
			result = handlerException(pjp, e);
		}
		return result;
	}

	private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
		ResultBean<Object> result = new ResultBean<Object>();
		// 已知异常
		if (e instanceof CheckException) {
			result.setMsg(e.getLocalizedMessage());
			result.setCode(ResultBean.FAIL);
		} else {
			logger.error(pjp.getSignature() + " error ", e);
			result.setMsg(e.toString());
			result.setCode(ResultBean.FAIL);
			// 未知异常是应该重点关注的，这里可以做其他操作，如通知邮件，单独写到某个文件等等。
		}
		Logger log = getLog(pjp);
		if(log.isDebugEnabled()){
			log.debug("异常出现了！");
		}
		return result;
	}
}
/**
 * <aop:aspectj-autoproxy /> <beans:bean id="controllerAop" class=
 * "xxx.common.aop.ControllerAOP" />
 * 
 * <aop:config> <aop:aspect id="myAop" ref="controllerAop"> <aop:pointcut id=
 * "target" expression=
 * "execution(public xxx.common.beans.ResultBean *(..))" /> <aop:around method=
 * "handlerControllerMethod" pointcut-ref="target" /> </aop:aspect>
 * </aop:config>
 */
