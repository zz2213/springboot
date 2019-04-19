package com.zz.secondhand.utils;

import android.app.Application;
import com.zz.secondhand.entity.User;

/**
 * @author Administrator
 * @title: CustomAppliction
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/1913:56
 */
public class CustomAppliction extends Application {
    public User getMyUser() {
        return myUser;
    }

    public void setMyUser(User myUser) {
        this.myUser = myUser;
    }

    private User myUser;
    @Override
    public void onCreate() {
        super.onCreate();
        myUser=new User();
    }
}
