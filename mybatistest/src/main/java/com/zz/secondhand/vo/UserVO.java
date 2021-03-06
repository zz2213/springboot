package com.zz.secondhand.vo;

import java.io.Serializable;

/**
 * @author Administrator
 * @title: UserVO
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/2414:53
 */
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;

    private String name;

    private String realname;

    private String password;

    private Long number;

    private String school;

    private String image;

    private String qq;

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
        this.name = name;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
