package com.zz.secondhand.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * @author Administrator
 * @title: ViewpagerAdapter
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/3015:49
 */
public class ViewpagerAdapter extends FragmentPagerAdapter {
    private List<ImageView> images;
    private ViewPager viewPager;
    /**
     * 构造方法，传入图片列表和ViewPager实例
     * @param images
     * @param viewPager
     */

    public ViewpagerAdapter(FragmentManager fm,List<ImageView> images, ViewPager viewPager){
        super(fm);
        this.images  = images;
        this.viewPager = viewPager;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public Fragment getItem(int i) {
        return null;
    }

    /**
     * 初始化一个条目
     * @param container
     * @param position 当前需要加载条目的索引
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 把position对应位置的ImageView添加到ViewPager中
        ImageView iv = images.get(position % images.size());
        viewPager.addView(iv);
        // 把当前添加ImageView返回回去.
        return iv;
    }
    /**
     * 销毁一个条目
     * position 就是当前需要被销毁的条目的索引
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 把ImageView从ViewPager中移除掉
        viewPager.removeView(images.get(position % images.size()));

    }
}
