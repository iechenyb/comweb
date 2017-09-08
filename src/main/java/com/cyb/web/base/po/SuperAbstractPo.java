package com.cyb.web.base.po;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年9月5日
 */
@MappedSuperclass
public abstract class SuperAbstractPo {
	@Column(nullable=true,columnDefinition="varchar(40) DEFAULT ''comment 'test super'")
    private String superabsid1;

	public String getSuperabsid1() {
		return superabsid1;
	}

	public void setSuperabsid1(String superabsid1) {
		this.superabsid1 = superabsid1;
	}
}
