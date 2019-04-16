package com.zz.secondhand;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.zz.secondhand.entity.User;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/2 14:07
 * @Description:
 */
public class FragmentFind extends Fragment {
    private  View view;
    private User self;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_find,null);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        Button button = getActivity().findViewById(R.id.buy_want);
        Button button1 = getActivity().findViewById(R.id.goods);
        self =((MainActivity)getActivity()).getSelf();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),GoodsActivity.class);
                intent.putExtra("product_type","求购");
                intent.putExtra("user",self);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),GoodsActivity.class);
                intent.putExtra("product_type","商品");
                intent.putExtra("user",self);
                startActivity(intent);
            }
        });
    }
}
