package com.cyb.web.xzzx.vo;

import org.springframework.web.multipart.MultipartFile;
public class QHFileVo {
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月1日下午4:57:10</br>
	 */
	private String id;
    private String realName;  
    private String saveName;  
    private String downName;  
    private String logoName; 
    private String logoSaveName; 
    private String fileType;  //FileType.id
    private int softState;  //0最新 1推荐
    private int fileType1;  //0 资料 1 合同 2 软件
    private int fileType2;  //pdf xls apk 等
    private String md5;
    private long ordor;  //排序权重  数字越小，排名越靠前
    private long size;
    private String ssgs;//香港公司 期货公司 金控公司 香港英文网站 代号组合
    private String description;
	public MultipartFile file;
	public MultipartFile logoFile;
	
    public QHFileVo() {  
        super();  
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


	public MultipartFile getFile() {
		return file;
	}


	public void setFile(MultipartFile file) {
		this.file = file;
	}


	public MultipartFile getLogoFile() {
		return logoFile;
	}


	public void setLogoFile(MultipartFile logoFile) {
		this.logoFile = logoFile;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
    
}
