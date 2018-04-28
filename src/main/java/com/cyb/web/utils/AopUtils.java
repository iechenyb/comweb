package com.cyb.web.utils;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年1月25日
 */
public class AopUtils {
	public Logger getLog(final JoinPoint joinPoint) {
        final Object target = joinPoint.getTarget();
        if (target != null) {
            return LoggerFactory.getLogger(target.getClass());
        }

        return LoggerFactory.getLogger(getClass());
    }
	
	public Method getMethod(final JoinPoint joinPoint) {
		Method currentMethod;
		try {
			Signature sig = joinPoint.getSignature();
	        MethodSignature msig = null;
	        if (!(sig instanceof MethodSignature)) {
	            throw new IllegalArgumentException("该注解只能用于方法");
	        }
	        msig = (MethodSignature) sig;
	        Object target = joinPoint.getTarget();
			currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
        return currentMethod;
	}
}
