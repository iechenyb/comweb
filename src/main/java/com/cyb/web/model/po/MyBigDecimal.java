package com.cyb.web.model.po;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;  
  
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月16日
 */
@Entity  
@Table(name = "my_big_decimal") 
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MyBigDecimal {
	@Id  
    int id;  
    @Column(precision = 23, scale = 6) 
    BigDecimal xiaoShu;  
   
    public int getId() {  
        return id;  
    }  
  
    public void setId(int id) {  
        this.id = id;  
    }  
  
     
    public BigDecimal getXiaoShu() {  
        return xiaoShu;  
    }  
  
    public void setXiaoShu(BigDecimal xiaoShu) {  
        this.xiaoShu = xiaoShu;  
    }  
}
