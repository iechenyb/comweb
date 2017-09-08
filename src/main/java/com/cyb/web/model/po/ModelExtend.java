package com.cyb.web.model.po;  
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cyb.web.base.po.BasePo; 
/**
 *作者: iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年07月16日 13时08分25秒
 */
@Entity
@Table(name="tb_model1")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ModelExtend extends BasePo<Serializable>{
 	
 }