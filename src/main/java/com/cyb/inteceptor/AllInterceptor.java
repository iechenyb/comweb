package com.cyb.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import com.cyb.web.utils.RequestUtils;

public class AllInterceptor extends HandlerInterceptorAdapter{
	Log log = LogFactory.getLog(AllInterceptor.class);
    private static final String[] IGNORE_URL = {"/login.jsp", "/regedit.jsp"};
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        for (String str : IGNORE_URL) {
            if (url.contains(str)) {
            }
        }
        log.info(RequestUtils.systemLog(request));
        return true;
    }
    
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndViewResolver modelAndView) throws Exception {
    	log.info("spring inteceptor postHandle");
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    	log.info("spring inteceptor afterCompletion");
    }
}
