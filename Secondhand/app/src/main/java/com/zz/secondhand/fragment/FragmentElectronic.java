package com.zz.secondhand.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.zz.secondhand.ProductActivity;
import com.zz.secondhand.R;
import com.zz.secondhand.adapter.MyAdapter;
import com.zz.secondhand.entity.Goods;

import java.util.ArrayList;

/**
 * @author Administrator
 * @title: FragmentElectronic
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/913:03
 */
public class FragmentElectronic extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_electrobic,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView listView = (ListView)getActivity().findViewById(R.id.electrobic_list);
        ArrayList<Goods> list = new ArrayList<Goods>();
        for (int i = 0; i < 21; i++) {
            list.add(new Goods("电子:"+i));
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
}
