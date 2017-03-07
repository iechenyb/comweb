package com.cyb.web.xzzx.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="t_sys_file")
public class QHFile {
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月1日下午4:57:10</br>
	 */
	@Id
   	@GenericGenerator(strategy = "uuid2", name = "user_uuid")
   	@GeneratedValue(generator = "user_uuid")
   	@Column(name="id",unique=true, nullable=false,length=50)
	private String id; 
	@Column(name="realname",nullable=false,length=100)
    private String realName;  
	@Column(name="savename",nullable=false,length=100)
    private String saveName;  
	@Column(name="savepathname",nullable=false,length=100)
    private String savePathName;  
	@Column(name="downname",nullable=false,length=100)
    private String downName;  
	@Column(name="logoname",nullable=true,length=100)
    private String logoName; 
	@Column(name="logoSavename",nullable=true,length=100)
    private String logoSaveName; 
	@Column(name="fileType",nullable=false,length=50)//合同或软件类型
    private String fileType;  //FileType.id
	@Column(name="softstate",nullable=false)
    private int softState;  //0最新 1推荐
	@Column(name="fileType1",nullable=false)
    private int fileType1;  //0 资料 1 合同 2 软件
	@Column(name="fileType2",nullable=false)
    private int fileType2;  //pdf xls apk 等
	@Column(name="md5",nullable=false,length=100)
    private String md5;
	@Column(name="ordor",nullable=false)
    private long ordor;  //排序权重  数字越小，排名越靠前
	@Column(name="size",nullable=false)
    private long size;
	@Column(name="ssgs",nullable=false,length=100)
    private String ssgs;//香港公司 期货公司 金控公司 香港英文网站 代号组合
	@Column(name="description",nullable=true,length=200)
    private String description;
	@Column(name="createtime",nullable=true,length=20)
    private String createtime;
	@Column(name="dowNum")
    private long dowNum=0;
    public QHFile() {  
        super();  
    }  
    

    public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

    public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}


	public String getSaveName() {
		return saveName;
	}


	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}


	public String getDownName() {
		return downName;
	}


	public void setDownName(String downName) {
		this.downName = downName;
	}


	public String getLogoName() {
		return logoName;
	}


	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}


	public String getLogoSaveName() {
		return logoSaveName;
	}


	public void setLogoSaveName(String logoSaveName) {
		this.logoSaveName = logoSaveName;
	}


	public String getFileType() {
		return fileType;
	}


	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public int getSoftState() {
		return softState;
	}


	public void setSoftState(int softState) {
		this.softState = softState;
	}


	public int getFileType1() {
		return fileType1;
	}


	public void setFileType1(int fileType1) {
		this.fileType1 = fileType1;
	}


	public int getFileType2() {
		return fileType2;
	}


	public void setFileType2(int fileType2) {
		this.fileType2 = fileType2;
	}


	public String getMd5() {
		return md5;
	}


	public void setMd5(String md5) {
		this.md5 = md5;
	}


	public long getOrdor() {
		return ordor;
	}


	public void setOrdor(long ordor) {
		this.ordor = ordor;
	}


	public long getSize() {
		return size;
	}


	public void setSize(long size) {
		this.size = size;
	}


	public String getSsgs() {
		return ssgs;
	}


	public void setSsgs(String ssgs) {
		this.ssgs = ssgs;
	}


	public String getDescription() {  
        return description;  
    }  
  
    public void setDescription(String description) {  
        this.description = description;  
    }


	public String getSavePathName() {
		return savePathName;
	}


	public void setSavePathName(String savePathName) {
		this.savePathName = savePathName;
	}


	public String getCreatetime() {
		return createtime;
	}


	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}


	public long getDowNum() {
		return dowNum;
	}


	public void setDowNum(long dowNum) {
		this.dowNum = dowNum;
	}
    
}
