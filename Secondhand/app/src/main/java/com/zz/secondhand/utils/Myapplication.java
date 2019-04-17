package com.zz.secondhand.utils;

import android.app.Application;
import com.zz.secondhand.entity.User;

/**
 * @author Administrator
 * @title: Myapplication
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/1710:20
 */
public class Myapplication extends Application {
    private User loginUser = new User();

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }
}
