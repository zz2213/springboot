package com.zz.secondhand.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.zz.secondhand.*;
import com.zz.secondhand.adapter.MyAdapter;
import com.zz.secondhand.entity.Goods;

import java.util.ArrayList;

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
                   list.add(new Goods("生活:"+i));
               }
               MyAdapter adapter = new MyAdapter(getContext(), R.layout.item_goods,list);
               listView.setAdapter(adapter);
               listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       String data = list.get(position).getTitle();
                       Intent intent = new Intent(getActivity(), ProductActivity.class);
                       intent.putExtra("extra_data",data);
                       startActivity(intent);
                   }
               });

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