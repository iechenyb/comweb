package com.cyb.web.hibernate.po.m2o;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table; 
/**
 *作者 : iechenyb<br>
 *类描述:订单信息<br>
 *创建时间: 2017年8月21日
 */
@Entity  
@Table(name="ORDERS")  
public class Order implements Serializable{  
  
    private static final long serialVersionUID = 1L;  
    public Order(){}  
  
    public Order(String orderNumber,Customer1 customer){  
        this.orderNumber =orderNumber;  
        this.customer=customer;  
    }  
    @Id  
    @GeneratedValue  
    @Column(name="ID")  
    private Long id;  
      
    @Column(name="ORDER_NUMBER",length=15)  
    private String orderNumber;  
    //订单表中存在顾客的id信息，多对一  
    @ManyToOne(fetch = FetchType.EAGER)  
    @JoinColumn(name="CUSTOMER_ID", nullable = false)  
    private Customer1 customer;  
  
    public Long getId() {  
        return id;  
    }  
  
    public void setId(Long id) {  
        this.id = id;  
    }  
  
    public String getOrderNumber() {  
        return orderNumber;  
    }  
  
    public void setOrderNumber(String orderNumber) {  
        this.orderNumber = orderNumber;  
    }  
  
    public Customer1 getCustomer1() {  
        return customer;  
    }  
  
    public void setCustomer(Customer1 customer) {  
        this.customer = customer;  
    }  
}  
