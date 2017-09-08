package com.cyb.web.base.po;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月20日
 */
@MappedSuperclass
public class BasePo <ID extends Serializable> extends SuperAbstractPo {
	
	@Id
	@GenericGenerator(strategy="uuid",name="user_uuid")
	@GeneratedValue(generator="user_uuid")
	@Column(name="id",unique=true, nullable=false,length=50)
	private String id;   
	
  	@Column(nullable=true,columnDefinition="varchar(40) DEFAULT ''comment '操作员标识'")
    private String czyid;
  	
  	@Column(nullable=true,columnDefinition="varchar(50) DEFAULT ''comment '操作员名称'")
    private String czymc;
  	
  	@Column(nullable=true,columnDefinition="varchar(14) DEFAULT ''comment '操作时间'")
    private String czsj;
  	
	@Column(nullable=false,columnDefinition="int DEFAULT 0 comment '记录状态'")
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
