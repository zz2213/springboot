package com.zz.secondhand;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.utils.Myapplication;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class MainActivity extends FragmentActivity{

    private List<Fragment> list = new ArrayList<>();
    private ViewPager vp;
    private  User self;
    private TabLayout tabLayout;

    public MainActivity() {
    }

    User getSelf() {
        return self;
    }

    public void setSelf(User self) {
        this.self = self;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        self =(User) intent.getSerializableExtra("user");
        vp=findViewById(R.id.viewPager);
         tabLayout=findViewById(R.id.tablayout);
        initPage();
    }
    private void initPage() {
        FragmentHome fragmentHome = new FragmentHome();
        FragmentList fragmentList = new FragmentList();
        FragmentFind fragmentFind = new FragmentFind();
        FragmentMy fragmentMy = new FragmentMy();

        list.add(fragmentHome);
        list.add(fragmentList);
        list.add(fragmentFind);
        list.add(fragmentMy);
        vp.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), list));
        vp.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(vp);
    }



}
