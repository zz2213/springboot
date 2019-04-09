package com.msi_pc.secondhand.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.msi_pc.secondhand.*;
import com.msi_pc.secondhand.adapter.MyAdapter;
import com.msi_pc.secondhand.entity.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @title: FragmentLife
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/913:02
 */
public class FragmentLife  extends Fragment
       {
           private  View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_life,container,false);
        return view;
    }

           @Override
           public void onActivityCreated(@Nullable Bundle savedInstanceState) {
               super.onActivityCreated(savedInstanceState);
               ListView listView = (ListView)getActivity().findViewById(R.id.life_list);
               ArrayList<Goods> list = new ArrayList<Goods>();
               for (int i = 0; i < 21; i++) {
                   list.add(new Goods("测试:"+i));
               }
               MyAdapter adapter = new MyAdapter(getContext(), R.layout.item_goods,list);
               listView.setAdapter(adapter);

           }

           @Override
           public void onStart() {


               super.onStart();
           }

           @Override
           public void onResume() {

               super.onResume();

           }
       }
