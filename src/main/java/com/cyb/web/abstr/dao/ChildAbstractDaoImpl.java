package com.cyb.web.abstr.dao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月23日
 */
@Repository("childAbstractDaoImpl")
public class ChildAbstractDaoImpl extends AbstractImpl{
	Log log = LogFactory.getLog(ChildAbstractDaoImpl.class);
	
	@Override
	public void println(String str) {
		log.info(str);
	}

	@Override
	public void printAny(String str) {
		log.info(str);
	}
	
}
