package com.zz.secondhand.utils;

import com.zz.secondhand.entity.Token;
import com.zz.secondhand.entity.User;

import java.io.Serializable;

/**
 * @author Administrator
 * @title: ReturnMessage
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/2210:24
 */
public class ReturnMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    User user;
    Token token;
    String mess;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    @Override
    public String toString() {
        return "ReturnMessage{" +
                "user=" + user +
                ", token=" + token +
                ", mess='" + mess + '\'' +
                '}';
    }
}
