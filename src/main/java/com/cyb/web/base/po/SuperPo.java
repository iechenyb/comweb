package com.cyb.web.base.po;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年9月5日
 */
@MappedSuperclass
public class SuperPo {
	@Column(nullable=true,columnDefinition="varchar(40) DEFAULT ''comment 'test super'")
    private String superid;

	public String getSuperid() {
		return superid;
	}

	public void setSuperid(String superid) {
		this.superid = superid;
	}
	
}
