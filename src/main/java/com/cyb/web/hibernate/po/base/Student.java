package com.cyb.web.hibernate.po.base;

/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月20日
 */
public class Student extends HibernateBasePo{
	private String sno;    //学号  
    private String school;  //学校  
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
	public static void main(String[] args) {
		HibernateBasePo po = new Student();
	}
    
}
