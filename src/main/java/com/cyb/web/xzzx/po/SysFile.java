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
public class SysFile {
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
	@Column(name="title",nullable=true)
    private String title;  
	@Column(name="content",nullable=true,length=500)
    private String content;  
	@Column(name="time",nullable=true)
    private String time;  
	@Column(name="size",nullable=true)
    private long size; 
	@Column(name="fjname",nullable=true)
    private String fjname;  
    public SysFile() {  
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

	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public long getSize() {
		return size;
	}


	public void setSize(long size) {
		this.size = size;
	}


	public String getFjname() {
		return fjname;
	}


	public void setFjname(String fjname) {
		this.fjname = fjname;
	}   
}
