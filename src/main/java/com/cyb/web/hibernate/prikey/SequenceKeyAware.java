package com.cyb.web.hibernate.prikey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 自增序列id<br>
 *创建时间: 2017年12月4日
 */
@MappedSuperclass
public class SequenceKeyAware {
	Log log = LogFactory.getLog(SequenceKeyAware.class);
	
	@Id  
	@SequenceGenerator(name = "T_USER_GENERATOR", sequenceName = "SEQ_USER_USERID_TEST",allocationSize = 100)  
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_USER_GENERATOR")  
	public String id;
}
