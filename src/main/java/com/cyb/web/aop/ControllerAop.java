package com.cyb.web.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cyb.web.example.annotation.MyController;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年12月6日
 */
@Aspect
@Component
public class ControllerAop {
	public static String TESTKEY = "USER_NAME_TEST";
	Log log = LogFactory.getLog(ControllerAop.class);
	final static  int i;
	static{
		i=2;
		/*HttpServletRequest request
		= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();*/
	}
	// 任何通知方法都可以将第一个参数定义为 org.aspectj.lang.JoinPoint类型
	@Pointcut("execution(* com.cyb.web..controller..*(..))")
	private void anyMethod() {
		
	}// 定义一个切入点

	
	@Before("anyMethod()")
	public void doAccessCheck(JoinPoint call) throws Exception {
		String clazz = call.getTarget().getClass().getName();
		// 获取目标对象上正在执行的方法名
		String methodName = call.getSignature().getName();
		List<Object> list = new ArrayList<Object>();
		for(int i=0;i<call.getArgs().length;i++){
			list.add(call.getArgs()[i]);
		}
		log.info(getControllerMethodDescription(call)+"前置通知:" + clazz + "类的" + methodName +" "+list+ "方法开始了...");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		log.info("登录信息"+session.getAttribute(TESTKEY));
	}

	@AfterReturning("anyMethod()")
	public void doAfter() {
		log.info("后置通知");
	}

	@AfterThrowing("anyMethod()")
	public void doAfterThrow() {
		log.info("例外通知");
	}

	@Around("anyMethod()")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		log.info("登录信息"+session.getAttribute(TESTKEY));
		Object object = pjp.proceed();// 执行该方法
		return object;
	}
	
	
	 public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {    
	        String targetName = joinPoint.getTarget().getClass().getName();    
	        String methodName = joinPoint.getSignature().getName();    
	        Object[] arguments = joinPoint.getArgs();    
	        Class<?> targetClass = Class.forName(targetName);//获取类    
	        Method[] methods = targetClass.getMethods();//获取方法    
	        String description = "";    
	         for (Method method : methods) {    
	             if (method.getName().equals(methodName)) {//如果是当前方法，则获取获取指定的注解    
	                @SuppressWarnings("rawtypes")
					Class[] clazzs = method.getParameterTypes();    
	                 if (clazzs.length == arguments.length) { 
	                	 try{
	                		 description = method.getAnnotation(MyController.class).description();
	                	 }catch(Exception e){
	                		 return "";
	                	 }
	                    break;    
	                }    
	            }    
	        }    
	         return description;    
	    } 
}
