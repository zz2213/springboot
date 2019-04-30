package com.zz.secondhand;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.zz.secondhand.entity.User;

import java.util.Objects;

/**
 * @author msi-pc
 * @Date: 2019/4/2 14:07
 * @Description:
 */
public class FragmentFind extends Fragment {
    private User self;
    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_find, null);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        Button button = Objects.requireNonNull(getActivity()).findViewById(R.id.buy_want);
        Button button1 = getActivity().findViewById(R.id.goods);
        self =((MainActivity)getActivity()).getSelf();
        button.setOnClickListener(v -> {
            Toast.makeText(getContext(),"暂不支持",Toast.LENGTH_SHORT).show();
            /*Intent intent = new Intent(getActivity(),GoodsActivity.class);
            intent.putExtra("productType","求购");
            intent.putExtra("user",self);
            startActivity(intent);*/
        });
        button1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),GoodsActivity.class);
            intent.putExtra("productType","商品");
            intent.putExtra("user",self);
            startActivity(intent);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
