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
@Table(name="t_sys_user")
public class User {
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月1日下午4:56:32</br>
	 */
	@Id
	@GenericGenerator(strategy = "uuid2", name = "user_uuid")
	@GeneratedValue(generator = "user_uuid")
	@Column(name="id",unique=true, nullable=false,length=50)
	private String id;  
	
	@Column(name="username",nullable=false,length=200)
    private String username;  
	
	@Column(name="empno",nullable=false,length=200)
    private String empno;  
	
	@Column(name="idcard",nullable=true,length=200)
    private String idcard; 
	
	@Column(name="email",nullable=true,length=200)
    private String email; 
	
	@Column(name="phone",nullable=true,length=200)
    private String phone; 
	
	@Column(name="password",nullable=false,length=36)
    private String password; 
	
    /*@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Role> roles = new HashSet<Role>(); */ 
    
    @Column(name="lastLoginTime",nullable=true)
    public long lastLoginTime;
    @Column(name="lastLoginIp",nullable=true,length=30)
    public String lastLoginIp;
    @Column(name="loginIp",nullable=true,length=30)
    public String loginIp;
    @Column(name="loginSum",nullable=true)
    public int loginSum;
    @Column(name="isEffect",nullable=true)
    public int isEffect;
    @Column(name="sex",nullable=true)
    public int sex;
    @Column(name="operateID",nullable=true)
    public String operateID;
    public User() {  
        super();  
    }  
 
   
    public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUsername() {  
        return username;  
    }  
  
    public void setUsername(String username) {  
        this.username = username;  
    }  
    
    public String getPassword() {  
        return password;  
    }  
    public void setPassword(String password) {  
        this.password = password;  
    }  
    /*public Set<Role> getRoles() {  
        return roles;  
    }  
  
    public void setRoles(Set<Role> roles) {  
        this.roles = roles;  
    }*/
	public long getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public int getLoginSum() {
		return loginSum;
	}
	public void setLoginSum(int loginSum) {
		this.loginSum = loginSum;
	}
	public int getIsEffect() {
		return isEffect;
	}
	public void setIsEffect(int isEffect) {
		this.isEffect = isEffect;
	}
	public String getOperateID() {
		return operateID;
	}
	public void setOperateID(String operateID) {
		this.operateID = operateID;
	}


	public String getEmpno() {
		return empno;
	}


	public void setEmpno(String empno) {
		this.empno = empno;
	}


	public String getIdcard() {
		return idcard;
	}


	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public int getSex() {
		return sex;
	}


	public void setSex(int sex) {
		this.sex = sex;
	}


	public String getLoginIp() {
		return loginIp;
	}


	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}  
	
}
