package com.zz.secondhand.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Admin {
    private Integer id;

    @NotEmpty(message="用户名不能为空！")
    private String name;

    @Size(min=6,max=10,message = "密码长度必须6到10位")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}