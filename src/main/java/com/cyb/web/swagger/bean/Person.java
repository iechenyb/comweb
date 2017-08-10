package com.cyb.web.swagger.bean;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
@ApiModel(value = "用户实体类")
public class Person {
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
    @ApiModelProperty(value = "登录用户名")
    private String name;
    @ApiModelProperty(value = "年龄")
    private int age;
}
