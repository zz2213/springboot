package com.zz.secondhand.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import com.alibaba.fastjson.JSON;
import com.cniupay.pay.CNiuPay;
import com.zz.secondhand.entity.Token;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author Administrator
 * @title: Myapplication
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/1710:20
 */
public class Myapplication extends Application {

    private Token token;

    private ArrayList<Fragment>  fragmentArrayList;
    private ArrayList<Activity>  activityArrayList;

    public ArrayList<Fragment> getFragmentArrayList() {
        return fragmentArrayList;
    }

    public void setFragmentArrayList(ArrayList<Fragment> fragmentArrayList) {
        this.fragmentArrayList = fragmentArrayList;
    }

    public ArrayList<Activity> getActivityArrayList() {
        return activityArrayList;
    }

    public void setActivityArrayList(ArrayList<Activity> activityArrayList) {
        this.activityArrayList = activityArrayList;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences userToken=getSharedPreferences("userToken",0);
        String tokenResult=userToken.getString("token","");
        setToken(JSON.parseObject(tokenResult,Token.class));
        CNiuPay.getInstance(getApplicationContext()).init("051842ac02ade5024be2ea108cf5ff68c66d49b128e6475c904c4f34b87e5c7d");
        registerActivityLifecycleCallbacks(lifecycleCallbacks);
    }

    private ActivityLifecycleCallbacks lifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            fixViewMutiClickInShortTime(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    };

    /**
     * @description: 防止短时间内多次点击，弹出多个activity 或者 dialog ，等操作
     * @param activity
     */
    private void fixViewMutiClickInShortTime(final Activity activity) {
        activity.getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(() -> proxyOnlick(activity.getWindow().getDecorView(),5));
    }

    private void proxyOnlick(View view, int recycledContainerDeep) {
        if (view.getVisibility() == View.VISIBLE) {
            if (view instanceof ViewGroup) {
                boolean existAncestorRecycle = recycledContainerDeep > 0;
                ViewGroup p = (ViewGroup) view;
                if (!(p instanceof AbsListView || p instanceof ListView) || existAncestorRecycle) {
                    getClickListenerForView(view);
                    if (existAncestorRecycle) {
                        recycledContainerDeep++;
                    }
                } else {
                    recycledContainerDeep = 1;
                }
                int childCount = p.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View child = p.getChildAt(i);
                    proxyOnlick(child, recycledContainerDeep);
                }
            } else {
                getClickListenerForView(view);
            }
        }
    }

    /**
     * 通过反射  查找到view 的clicklistener
     * @param view
     */
    private static void getClickListenerForView(View view) {
        try {
            Class viewClazz = Class.forName("android.view.View");
            //事件监听器都是这个实例保存的
            Method listenerInfoMethod = viewClazz.getDeclaredMethod("getListenerInfo");
            if (!listenerInfoMethod.isAccessible()) {
                listenerInfoMethod.setAccessible(true);
            }
            Object listenerInfoObj = listenerInfoMethod.invoke(view);

            @SuppressLint("PrivateApi") Class listenerInfoClazz = Class.forName("android.view.View$ListenerInfo");

            Field onClickListenerField = listenerInfoClazz.getDeclaredField("mOnClickListener");

            if (null != onClickListenerField) {
                if (!onClickListenerField.isAccessible()) {
                    onClickListenerField.setAccessible(true);
                }
                View.OnClickListener mOnClickListener = (View.OnClickListener) onClickListenerField.get(listenerInfoObj);
                if (!(mOnClickListener instanceof ProxyOnclickListener)) {
                    //自定义代理事件监听器
                    View.OnClickListener onClickListenerProxy = new ProxyOnclickListener(mOnClickListener);
                    //更换
                    onClickListenerField.set(listenerInfoObj, onClickListenerProxy);
                }else{
                    Log.e("OnClickListenerProxy", "setted proxy listener ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//自定义的代理事件监听器


    static class ProxyOnclickListener implements View.OnClickListener {
        private View.OnClickListener onclick;

        private int minClickDelayTime = 500;

        private long lastClickTime = 0;

        public ProxyOnclickListener(View.OnClickListener onclick) {
            this.onclick = onclick;
        }

        @Override
        public void onClick(View v) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastClickTime > minClickDelayTime) {
                lastClickTime = currentTime;
                Log.e("OnClickListenerProxy", "OnClickListenerProxy"+this);
                if (onclick != null) {
                    onclick.onClick(v);
                }
            }
        }
    }

}
