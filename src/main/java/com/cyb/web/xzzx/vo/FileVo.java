package com.cyb.web.xzzx.vo;

import org.springframework.web.multipart.MultipartFile;
public class FileVo {
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月1日下午4:57:10</br>
	 */
	private String id;
    private String title;  
    private String desc;
    private String picStr;
	public MultipartFile file1;
	public MultipartFile file2;
	
    public FileVo() {  
        super();  
    }  
    
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public MultipartFile getFile1() {
		return file1;
	}

	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}

	public MultipartFile getFile2() {
		return file2;
	}

	public void setFile2(MultipartFile file2) {
		this.file2 = file2;
	}

	public String getPicStr() {
		return picStr;
	}

	public void setPicStr(String picStr) {
		this.picStr = picStr;
	}
    
}
