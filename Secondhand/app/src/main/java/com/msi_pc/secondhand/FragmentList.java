package com.msi_pc.secondhand;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.msi_pc.secondhand.fragment.FragmentElectronic;
import com.msi_pc.secondhand.fragment.FragmentLife;
import com.msi_pc.secondhand.fragment.FragmentStudy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/8 18:43
 * @Description:
 */
public class FragmentList extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener{
    private List<Fragment> list;
    private  View view;
    private  ViewPager viewPager;
    private Button button1,button2,button3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_list,container,false);
         initView();
        return view;
    }
    private void initView(){
        viewPager = (ViewPager)view.findViewById(R.id.viewpaper01);
        list=new ArrayList<>();
        button1=(Button)view.findViewById(R.id.frag01);
        button2=(Button)view.findViewById(R.id.frag02);
        button3=(Button)view.findViewById(R.id.frag03);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        list.add(new FragmentLife());
        list.add(new FragmentStudy());
        list.add(new FragmentElectronic());
        viewPager.setAdapter(new MyFragmentAdapter(getFragmentManager(),list));
        viewPager.setOnPageChangeListener(this);
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
//        initBtnListener();
//        switch (i){
//            case 0:
//                   button1.setBackgroundColor(Color.parseColor("#ff735c"));
//                     break;
//            case 1:
//                button2.setBackgroundColor(Color.parseColor("#ff735c"));
//                break;
//            case 2:
//                button3.setBackgroundColor(Color.parseColor("#ff735c"));
//                break;
//
//        }

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
    private void initBtnListener(){

        button1.setBackgroundResource(R.color.white);
        button2.setBackgroundResource(R.color.white);
        button3.setBackgroundResource(R.color.white);
    }
}
