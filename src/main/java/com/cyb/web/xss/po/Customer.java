package com.cyb.web.xss.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="t_sys_customer")
public class Customer {
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月1日下午4:57:10</br>
	 */
	@Id
   	@GenericGenerator(strategy = "uuid2", name = "user_uuid")
   	@GeneratedValue(generator = "user_uuid")
   	@Column(name="id",unique=true, nullable=false,length=50)
	private String id; 
	@Column(name="name",nullable=false,length=30)
    private String name;  
	@Column(name="money",nullable=false)
    private long money;
    
    public Customer() {  
        super();  
    }  
    

    public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public long getMoney() {
		return money;
	}


	public void setMoney(long money) {
		this.money = money;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

}
