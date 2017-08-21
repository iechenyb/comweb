package com.cyb.web.hibernate.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ManyToAny;

/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月20日
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="t_hibrnate_student2")
public class Student2{
	@Id
	private String id;
	@Column(name="sno")
	private String sno;    //学号  
	@Column(name="school")
    private String school;  //学校
	
	@ManyToOne
	@JoinColumn(name="clsNo",referencedColumnName="id")
    private Clss cls;//所在班级
	
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Clss getCls() {
		return cls;
	}
	public void setCls(Clss cls) {
		this.cls = cls;
	}
	
	
}
