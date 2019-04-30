package com.zz.secondhand;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.alibaba.fastjson.JSON;
import com.zz.secondhand.entity.Token;
import com.zz.secondhand.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class MainActivity extends FragmentActivity implements
        ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {

    private boolean isExit;
    private FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;
    private Class[] fragmentArray = {FragmentHome.class, FragmentList.class, FragmentFind.class, FragmentMy.class};
    private int[] imageViewArray = {R.drawable.tab_home_btn, R.drawable.tab_find_btn, R.drawable.tab_find_btn, R.drawable.tab_my_btn};
    private String[] textViewArray = {"首页", "分类", "发布", "我的"};
    private List<Fragment> list = new ArrayList<>();
    private ViewPager vp;
    private  User self;

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
        SharedPreferences userToken=getSharedPreferences("userToken",0);
        Token token = JSON.parseObject(userToken.getString("token", "default"), Token.class);
        //初始化控件
        initView();
        //初始化页面
        initPage();
        onPageSelected(0);

    }

    private void initView() {
        vp = findViewById(R.id.pager);

        /*实现OnPageChangeListener接口,目的是监听Tab选项卡的变化，然后通知ViewPager适配器切换界面*/
        /*简单来说,是为了让ViewPager滑动的时候能够带着底部菜单联动*/

//设置页面切换时的监听器
        vp.addOnPageChangeListener(this);
        //加载布局管理器
        layoutInflater = LayoutInflater.from(this);

        /*实例化FragmentTabHost对象并进行绑定*/

        //绑定tahost
        mTabHost = findViewById(android.R.id.tabhost);
        //绑定viewpager
        mTabHost.setup(this, getSupportFragmentManager(), R.id.pager);

        /*实现setOnTabChangedListener接口,目的是为监听界面切换），然后实现TabHost里面图片文字的选中状态切换*/
        /*简单来说,是为了当点击下面菜单时,上面的ViewPager能滑动到对应的Fragment*/
        mTabHost.setOnTabChangedListener(this);

        int count = textViewArray.length;

        /*新建Tabspec选项卡并设置Tab菜单栏的内容和绑定对应的Fragment*/
        for (int i = 0; i < count; i++) {
            // 给每个Tab按钮设置标签、图标和文字
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(textViewArray[i])
                    .setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中，并绑定Fragment
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            mTabHost.setTag(i);
            //设置Tab被选中的时候颜色改变
            mTabHost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.drawable.main_tab_background);
        }
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

        //绑定Fragment适配器
        vp.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), list));
        vp.setOffscreenPageLimit(3);
        mTabHost.getTabWidget().setDividerDrawable(null);
    }

    private View getTabItemView(int i) {
        //将xml布局转换为view对象
        @SuppressLint("InflateParams") View view = layoutInflater.inflate(R.layout.tab_content, null);
        //利用view对象，找到布局中的组件,并设置内容，然后返回视图
        ImageView mImageView = view
                .findViewById(R.id.tab_imageview);
        TextView mTextView = view.findViewById(R.id.tab_textview);
        mImageView.setBackgroundResource(imageViewArray[i]);
        mTextView.setText(textViewArray[i]);
        return view;
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        //arg0是表示你当前选中的页面位置Postion，这事件是在你页面跳转完毕的时候调用的。
        TabWidget widget = mTabHost.getTabWidget();
        int oldFocusability = widget.getDescendantFocusability();
        //设置View覆盖子类控件而直接获得焦点
        widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        //根据位置Postion设置当前的Tab
        mTabHost.setCurrentTab(arg0);
        //设置取消分割线
        widget.setDescendantFocusability(oldFocusability);

    }

    @Override
    public void onTabChanged(String tabId) {
        //Tab改变的时候调用
        int position = mTabHost.getCurrentTab();
        //把选中的Tab的位置赋给适配器，让它控制页面切换
        vp.setCurrentItem(position);
    }

}
