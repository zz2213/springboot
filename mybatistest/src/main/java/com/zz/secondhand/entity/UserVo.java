package com.zz.secondhand.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Administrator
 * @title: UserVo
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/238:45
 */
public class UserVo {
    private Long id;

    @NotEmpty(message="用户名不能为空！")
    private String userName;

    private String nickName;

    @Size(min=6,max=10,message = "密码长度必须6到10位")
    private String password;

    private Integer age;

    private Integer sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
