package com.cyb.web.hibernate.prikey;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.GenericGenerator;
/**
 *作者 : iechenyb<br>
 *类描述:uuid主键  uuid -> guid - uuid.hex><br>
 *创建时间: 2017年12月4日
 */
@MappedSuperclass
public class UUIDKeyAware {
	Log log = LogFactory.getLog(UUIDKeyAware.class);
	@Id
	@GenericGenerator(strategy="uuid",name="uuid")
	@Column(name="id",unique=true,nullable=false,length=32)
	public String Id;
}
