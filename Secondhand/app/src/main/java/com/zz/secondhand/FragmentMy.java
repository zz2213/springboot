package com.zz.secondhand;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zz.secondhand.activity.OrderActivity;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/2 13:58
 * @Description:
 */
public class FragmentMy extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my, null);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        LinearLayout home = (LinearLayout) getActivity().findViewById(R.id.layout_my);
        LinearLayout myOrder = (LinearLayout) getActivity().findViewById(R.id.my_order);
        TextView myorder1=(TextView)getActivity().findViewById(R.id.btn1);
        TextView mywant=(TextView)getActivity().findViewById(R.id.btn2);
        TextView mygoods=(TextView)getActivity().findViewById(R.id.btn3);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),UserActivity.class);
                startActivity(intent);
            }
        });
//        myOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), OrderActivity.class);
//                startActivity(intent);
//            }
//        });
        myorder1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                startActivity(intent);
            }
        });
        mywant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                startActivity(intent);
            }
        });
        mygoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                startActivity(intent);
            }
        });
    }
}
