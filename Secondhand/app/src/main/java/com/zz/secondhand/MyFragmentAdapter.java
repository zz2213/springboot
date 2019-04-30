package com.zz.secondhand;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


/**
 * @author Administrator
 */
public class MyFragmentAdapter extends FragmentPagerAdapter

    {
        private String[] textViewArray = {"首页", "分类", "发布", "我的"};
        private List<Fragment> fragmentList;
        MyFragmentAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }
        @Override
        public Fragment getItem(int arg0) {
        return fragmentList.get(arg0);
    }

        @Override
        public int getCount() {
        return fragmentList.size();
    }//设置Item的数量

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return textViewArray[position];
        }
    }


