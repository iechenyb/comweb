package com.cyb.web.xtgl.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="t_sys_menu")
public class Menu {
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月3日下午1:24:22</br>
	 */
	@Id
	@GenericGenerator(strategy = "uuid2", name = "user_uuid")
	@GeneratedValue(generator = "user_uuid")
	@Column(name="id",unique=true, nullable=false,length=50)
	private String id;
	@Column(name="menuName",nullable=true,length=20)
	private String menuName;//类型名称
	@Column(name="url",nullable=true,length=200)
	private String url;//类型编号
	@Column(name="parentId",nullable=true,length=50)
	private String parentId;//父类型编号
	@Column(name="isLeaf",nullable=true)
	private int isLeaf;//是否叶子节点
	@Column(name="menuDesc",nullable=true,length=200)
	private String menuDesc;//类型描述
	@Column(name="createTime",nullable=true)
	private long createTime;//创建时间
	@Column(name="cretatePerson",nullable=true,length=50)
	private String cretatePerson;//创建人员
	@Column(name="modifyTime",nullable=true)
	private long modifyTime;//修改时间
	@Column(name="modifyPerson",nullable=true,length=50)
	private String modifyPerson;//修改人员
	@Column(name="ordor",nullable=true)
	private int ordor ;//菜单排序
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public int getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getMenuDesc() {
		return menuDesc;
	}
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public String getCretatePerson() {
		return cretatePerson;
	}
	public void setCretatePerson(String cretatePerson) {
		this.cretatePerson = cretatePerson;
	}
	public long getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(long modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getModifyPerson() {
		return modifyPerson;
	}
	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}
	public int getOrdor() {
		return ordor;
	}
	public void setOrdor(int ordor) {
		this.ordor = ordor;
	}
	
}
