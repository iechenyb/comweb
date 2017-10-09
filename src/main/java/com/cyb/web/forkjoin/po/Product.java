package com.cyb.web.forkjoin.po;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CollectionId;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年10月9日
 */
@Entity
@Table(name="tb_fj_product")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product {
	@Id
	@Column
	private String id;
	
	@Column
	private String name;
	@Column
    private double price;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	} 
}
