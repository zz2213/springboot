package com.msi_pc.secondhand;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * @Auther: msi-pc
 * @Date: 2019/4/3 20:48
 * @Description:
 */
public class UserActivity extends Activity {
    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_user);
    }
}
