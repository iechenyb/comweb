package com.cyb.common;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年1月10日
 */
public class CheckUtil {
	Log log = LogFactory.getLog(CheckUtil.class);
	 private static MessageSource resources;

	  public static void setResources(MessageSource resources) {
	    CheckUtil.resources = resources;
	  }

	  public static void check(boolean condition, String msgKey, Object... args) {
	    if (!condition) {
	      fail(msgKey, args);
	    }
	  }

	  public static void notEmpty(String str, String msgKey, Object... args) {
	    if (str == null || str.isEmpty()) {
	      fail(msgKey, args);
	    }
	  }

	  public static void notNull(Object obj, String msgKey, Object... args) {
	    if (obj == null) {
	      fail(msgKey, args);
	    }
	  }

	  private static void fail(String msgKey, Object... args) {
	    throw new CheckException(resources.getMessage(msgKey, args, UserUtil.getLocale()));
	  }
}
/*
 * <!-- 国际化 -->
<bean id="messageSource"
  class="org.springframework.context.support.ResourceBundleMessageSource">
  <property name="basenames">
    <list>
      <value>format</value>
      <value>exceptions</value>
      <value>windows</value>
    </list>
  </property>
</bean>

<bean
  class="org.springframework.beans.factory.config.MethodInvokingFactoryBean">
  <property name="staticMethod" value="plm.common.utils.CheckUtil.setResources" />
  <!-- 这里配置参数 -->
  <property name="arguments" ref="messageSource">
  </property>
</bean>
 * 
 */
