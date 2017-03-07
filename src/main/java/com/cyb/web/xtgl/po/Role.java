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
@Table(name="t_sys_role")
public class Role {
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
	@Column(name="rolename",nullable=false,length=100)
    private String rolename;  
	@Column(name="description",nullable=false,length=200)
    private String description;
    
    /*@ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="userid", referencedColumnName="userid",nullable=true)
    private User user;*/
    public Role() {  
        super();  
    }  
    

    public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getRolename() {  
        return rolename;  
    }  
  
    public void setRolename(String rolename) {  
        this.rolename = rolename;  
    }  
  
   
    public String getDescription() {  
        return description;  
    }  
  
    public void setDescription(String description) {  
        this.description = description;  
    }
	/*public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}  */
	
}
