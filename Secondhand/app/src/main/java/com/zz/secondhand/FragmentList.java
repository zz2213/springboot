package com.zz.secondhand;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.fragment.FragmentElectronic;
import com.zz.secondhand.fragment.FragmentLife;
import com.zz.secondhand.fragment.FragmentStudy;
import com.zz.secondhand.fragment.IndexViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/8 18:43
 * @Description:
 */
public class FragmentList extends Fragment implements IndexViewPager.OnPageChangeListener, View.OnClickListener{
    private List<Fragment> list;
    private  View view;
    private  IndexViewPager viewPager;
    private Button button1,button2,button3;

    public User getUser() {
        return user;
    }

    User user;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_list,container,false);
         initView();
        return view;
    }
    private void initView(){
        viewPager = (IndexViewPager) view.findViewById(R.id.viewpaper01);
        list=new ArrayList<>();
        button1=(Button)view.findViewById(R.id.frag01);
        button2=(Button)view.findViewById(R.id.frag02);
        button3=(Button)view.findViewById(R.id.frag03);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

   FragmentLife fragmentLife = new FragmentLife();
   FragmentElectronic fragmentElectronic = new FragmentElectronic();
   FragmentStudy fragmentStudy = new FragmentStudy();
        System.out.println(user.toString());
        fragmentLife.setUser(user);
        fragmentElectronic.setUser(user);
        fragmentStudy.setUser(user);
        list.add(fragmentLife);
        list.add(fragmentElectronic);
        list.add(fragmentStudy);
        viewPager.setScanScroll(false);

        viewPager.setAdapter(new MyFragmentAdapter(getFragmentManager(),list));
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {
    /*    initBtnListener();
        switch (i){
            case 0:
                   button1.setBackgroundColor(Color.parseColor("#ff735c"));
                     break;
            case 1:
                button2.setBackgroundColor(Color.parseColor("#ff735c"));
                break;
            case 2:
                button3.setBackgroundColor(Color.parseColor("#ff735c"));
                break;

        }*/

    }

    @Override
    public void onClick(View v) {
        initBtnListener();
        switch (v.getId()){
            case R.id.frag01:
                button1.setBackgroundColor(Color.parseColor("#ff735c"));
                viewPager.setCurrentItem(0);
                break;
            case R.id.frag02:
                button2.setBackgroundColor(Color.parseColor("#ff735c"));
                viewPager.setCurrentItem(1);
                break;
            case R.id.frag03:
                button3.setBackgroundColor(Color.parseColor("#ff735c"));
                viewPager.setCurrentItem(2);
                break;
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        user = ((MainActivity) context).getSelf();
        System.out.println("userID"+user.getId());
    }

    private void initBtnListener(){

        button1.setBackgroundResource(R.color.white);
        button2.setBackgroundResource(R.color.white);
        button3.setBackgroundResource(R.color.white);
    }

}
