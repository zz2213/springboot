package com.msi_pc.secondhand.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.msi_pc.secondhand.R;
import com.msi_pc.secondhand.adapter.MyAdapter;
import com.msi_pc.secondhand.entity.Goods;

import java.util.ArrayList;

/**
 * @author Administrator
 * @title: FragmentStudy
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/913:03
 */
public class FragmentStudy extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_study,null);
        return view;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView listView = (ListView)getActivity().findViewById(R.id.study_list);
        ArrayList<Goods> list = new ArrayList<Goods>();
        for (int i = 0; i < 21; i++) {
            list.add(new Goods("学习:"+i));
        }
        MyAdapter adapter = new MyAdapter(getContext(), R.layout.item_goods,list);
        listView.setAdapter(adapter);

    }
}
