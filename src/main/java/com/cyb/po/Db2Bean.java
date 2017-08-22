package com.cyb.po;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月22日
 */
@Entity
@Table(name="tb_newkiiik_model")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Db2Bean{
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
