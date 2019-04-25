package com.zz.secondhand.utils;

import android.app.Application;
import com.cniupay.pay.CNiuPay;
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
        CNiuPay.getInstance(getApplicationContext()).init("051842ac02ade5024be2ea108cf5ff68c66d49b128e6475c904c4f34b87e5c7d");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        super.onCreate();
        myUser=new User();
    }
}
