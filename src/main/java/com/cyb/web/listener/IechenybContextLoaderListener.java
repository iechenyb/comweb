package com.cyb.web.listener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年1月8日
 */
public class IechenybContextLoaderListener extends ContextLoaderListener{
	Log log = LogFactory.getLog(IechenybContextLoaderListener.class);
	 @Override  
	    protected ContextLoader createContextLoader() {  
	        return new IechenybContextLoader();  
	    } 
}
