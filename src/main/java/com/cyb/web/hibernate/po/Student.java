package com.cyb.web.hibernate.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月20日
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="t_hibrnate_student")
public class Student{
	@Id
	private String id;
	@Column(name="sno")
	private String sno;    //学号  
	@Column(name="school")
    private String school;  //学校
	@Column(name="clsNo")
    private String clsNo;//所在班级
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
	public String getClsNo() {
		return clsNo;
	}
	public void setClsNo(String clsNo) {
		this.clsNo = clsNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
