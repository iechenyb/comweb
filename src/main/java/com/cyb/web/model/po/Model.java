package com.cyb.web.model.po;  
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy; 
/**
 *作者: iechenyb<br>
 *类描述: 测试手动设置主键<br>
 *创建时间: 2017年07月16日 13时08分25秒
 */
@Entity
@Table(name="tb_model2")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Model{
 	@Id
	@Column(name="id",unique=true, nullable=false,length=50)
	private String id;   
  	@Column(nullable=true,columnDefinition="varchar(40) DEFAULT ''comment '操作员标识'")
    private String czyid;
  	@Column(nullable=true,columnDefinition="varchar(50) DEFAULT ''comment '操作员名称'")
    private String czymc;
  	@Column(nullable=true,columnDefinition="varchar(14) DEFAULT ''comment '操作时间'")
    private String czsj;
    @Column
    private int zt;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}  
    public String getCzyid() {  
        return czyid;  
    }  
    public void setCzyid(String czyid) {  
        this.czyid = czyid;  
    }  
    public String getCzymc() {  
        return czymc;  
    }  
    public void setCzymc(String czymc) {  
        this.czymc = czymc;  
    }  
    public String getCzsj() {  
        return czsj;  
    }  
    public void setCzsj(String czsj) {  
        this.czsj = czsj;  
    }  
    public int getZt() {  
        return zt;  
    }  
    public void setZt(int zt) {  
        this.zt = zt;  
    }  
 }