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
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="t_sys_user_role")
public class UserRole {
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月3日下午1:31:07</br>
	 */
	@Id
	@GenericGenerator(strategy = "uuid2", name = "user_uuid")
	@GeneratedValue(generator = "user_uuid")
	@Column(name="id",unique=true, nullable=false,length=50)
	private String id;  
	@Column(name="userid",length=50,nullable=false)
	private String userId; 
	@Column(name="roleid",length=50,nullable=false)
	private String roleId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	} 
	
}
