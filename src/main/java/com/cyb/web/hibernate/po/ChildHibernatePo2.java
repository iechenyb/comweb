package com.cyb.web.hibernate.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cyb.web.base.po.BasePo;
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="t_hibrnate_child2")
public class ChildHibernatePo2 extends BasePo{
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月1日下午4:57:10</br>
	 */
	@Column(name="cardNo",nullable=false,length=30)
    private String cardNo;  
	
	@Column(name="money",nullable=false)
    private long money;
	
    public ChildHibernatePo2() {  
        super();  
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
    
}
