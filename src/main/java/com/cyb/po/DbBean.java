package com.cyb.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cyb.web.base.po.BasePo;

/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月22日
 */
@Entity
@Table(name="tb_kiiik_model")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DbBean extends BasePo<Serializable>{
}
