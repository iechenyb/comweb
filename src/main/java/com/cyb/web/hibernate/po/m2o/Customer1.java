package com.cyb.web.hibernate.po.m2o;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *作者 : iechenyb<br>
 *类描述: 顾客信息<br>
 *创建时间: 2017年8月21日
 */
@Entity  
@Table  
public class Customer1 implements Serializable{  
      
    private static final long serialVersionUID = 1L;  
  
    public Customer1(){}  
    public Customer1(String name){  
        this.name=name;  
    }  
    @Id  
    @GeneratedValue  
    @Column(name="ID")  
    private Long id;  
      
    @Column(name="NAME",length=15)  
    private String name;  
  
    public Long getId() {  
        return id;  
    }  
  
    public void setId(Long id) {  
        this.id = id;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
}  
