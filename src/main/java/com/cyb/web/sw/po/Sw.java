package com.cyb.web.sw.po;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DecimalDV;
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="t_sys_sw")
public class Sw {
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
	@Column(name="cardNo",nullable=false,length=30)
    private String cardNo;  
	@Column(name="money",nullable=false)
    private long money;
    @Column(name="d")
    private double d;
    public Sw() {  
        super();  
    }  
    

    public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCardNo() {
		return cardNo;
	}


	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}


	public long getMoney() {
		return money;
	}


	public void setMoney(long money) {
		this.money = money;
	}


	public double getD() {
		return d;
	}


	public void setD(double d) {
		this.d = d;
	}

    
}
