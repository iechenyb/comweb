package com.cyb.web.hibernate.prikey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.GenericGenerator;
/**
 *作者 : iechenyb<br>
 *类描述: 自增序列id<br>
 *创建时间: 2017年12月4日
 */
@MappedSuperclass
public class AssignKeyAware {
	Log log = LogFactory.getLog(AssignKeyAware.class);
	
	@Id  
	@GeneratedValue(generator = "xxx")    
	@GenericGenerator(name = "xxx", strategy = "assigned")  
	public String id;
}
