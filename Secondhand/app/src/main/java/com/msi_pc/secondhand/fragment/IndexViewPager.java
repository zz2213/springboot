package com.msi_pc.secondhand.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author Administrator
 * @title: IndexViewPager
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/915:00
 */
public class IndexViewPager extends ViewPager {


    private boolean isCanScroll=true;

    public IndexViewPager(@NonNull Context context) {
        super(context);
    }

    public IndexViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    /**
     * 设置其是否能滑动换页
     * @param isCanScroll false 不能换页， true 可以滑动换页
     */
    public void setScanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isCanScroll&&super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isCanScroll&&super.onTouchEvent(ev);
    }
}
