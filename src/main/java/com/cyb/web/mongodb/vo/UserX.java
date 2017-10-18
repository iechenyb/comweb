package com.cyb.web.mongodb.vo;
import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年10月18日
 */
@Document  
public class UserX implements Serializable {  
    private static final long serialVersionUID = 1L;  
    private String id;  
    private String name;  
    private int age;  
    private String password;  
      
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public int getAge() {  
        return age;  
    }  
    public void setAge(int age) {  
        this.age = age;  
    }  
    public String getPassword() {  
        return password;  
    }  
    public void setPassword(String password) {  
        this.password = password;  
    }  
    public String getId() {  
        return id;  
    }  
    public void setId(String id) {  
        this.id = id;  
    }    
}  
