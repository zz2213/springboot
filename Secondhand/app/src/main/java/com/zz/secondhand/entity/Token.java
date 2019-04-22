package com.zz.secondhand.entity;

import java.io.Serializable;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/20 12:11
 * @Description:
 */
public class Token implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tokenData;
    private Integer userId;

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

    @Override
    public String toString() {
        return "Token{" +
                "tokenData='" + tokenData + '\'' +
                ", userId=" + userId +
                '}';
    }
}
