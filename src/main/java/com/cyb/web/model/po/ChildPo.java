package com.cyb.web.model.po;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cyb.web.base.po.BasePo;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月20日
 */
@Entity
@Table(name="tb_study_child")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ChildPo extends BasePo<String>{
	
	@Column(nullable=true,columnDefinition="varchar(40) DEFAULT ''comment '名称'")
    private String childName;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}
	
}
