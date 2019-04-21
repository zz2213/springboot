package com.zz.secondhand.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/20 12:10
 * @Description:
 */
public class Token implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private  String tokenData;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTokenData() {
        return tokenData;
    }

    public void setTokenData(String tokenData) {
        this.tokenData = tokenData;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
