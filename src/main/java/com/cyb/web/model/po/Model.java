package com.cyb.web.model.po;  
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator; 
/**
 *作者: iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年07月16日 13时08分25秒
 */
@Entity
@Table(name="tb_jyfl")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Model {
 	@Id
	@GenericGenerator(strategy="uuids",name="user_uuid")
	@GeneratedValue(generator="user_uuid")
	@Column(name="id",unique=true, nullable=false,length=50)
	private String id;   
  	@Column(length=50,nullable=false,columnDefinition="交易所名称")
    private String jyjysmc;
  	@Column(length=8,nullable=false,columnDefinition="交易所标识")
    private String jysbs;
  	@Column(length=30,nullable=false,columnDefinition="合约名称")
    private String hymc;
  	@Column(length=20,nullable=false,columnDefinition="合约代码")
    private String hydm;
  	@Column(length=20,nullable=false,columnDefinition="开仓(手数)")
    private String kcss;
  	@Column(length=20,nullable=false,columnDefinition="开仓(金额)")
    private String kcje;
  	@Column(length=18,nullable=false,columnDefinition="平仓(手数)")
    private String pcss;
  	@Column(length=20,nullable=false,columnDefinition="开仓(金额)")
    private String pcje;
  	@Column(length=20,nullable=false,columnDefinition="平今仓(手数)")
    private String pjcss;
  	@Column(length=20,nullable=false,columnDefinition="平今仓(金额)")
    private String pjcje;
  	@Column(length=40,nullable=false,columnDefinition="操作员标识")
    private String czyid;
  	@Column(length=50,nullable=false,columnDefinition="(操作员名称)")
    private String czymc;
  	@Column(length=14,nullable=false,columnDefinition="操作时间")
    private String czsj;
    @Column
    private int zt;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}  
    public String getJyjysmc() {  
        return jyjysmc;  
    }  
    public void setJyjysmc(String jyjysmc) {  
        this.jyjysmc = jyjysmc;  
    }  
    public String getJysbs() {  
        return jysbs;  
    }  
    public void setJysbs(String jysbs) {  
        this.jysbs = jysbs;  
    }  
    public String getHymc() {  
        return hymc;  
    }  
    public void setHymc(String hymc) {  
        this.hymc = hymc;  
    }  
    public String getHydm() {  
        return hydm;  
    }  
    public void setHydm(String hydm) {  
        this.hydm = hydm;  
    }  
    public String getKcss() {  
        return kcss;  
    }  
    public void setKcss(String kcss) {  
        this.kcss = kcss;  
    }  
    public String getKcje() {  
        return kcje;  
    }  
    public void setKcje(String kcje) {  
        this.kcje = kcje;  
    }  
    public String getPcss() {  
        return pcss;  
    }  
    public void setPcss(String pcss) {  
        this.pcss = pcss;  
    }  
    public String getPcje() {  
        return pcje;  
    }  
    public void setPcje(String pcje) {  
        this.pcje = pcje;  
    }  
    public String getPjcss() {  
        return pjcss;  
    }  
    public void setPjcss(String pjcss) {  
        this.pjcss = pjcss;  
    }  
    public String getPjcje() {  
        return pjcje;  
    }  
    public void setPjcje(String pjcje) {  
        this.pjcje = pjcje;  
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