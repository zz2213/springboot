package com.zz.secondhand;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.zz.secondhand.activity.MyGoodsActivity;
import com.zz.secondhand.activity.MyWantActivity;
import com.zz.secondhand.activity.OrderActivity;
import com.zz.secondhand.activity.SellerOrdActivity;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.utils.ImageUtil;
import com.zz.secondhand.utils.Myapplication;

import java.util.Objects;

import static android.app.Activity.RESULT_OK;


/**
 * @author Administrator
 * @Auther: msi-pc
 * @Date: 2019/4/2 13:58
 * @Description:
 */

@SuppressLint("ValidFragment")
public class FragmentMy extends Fragment {

    public User getSelf() {
        return self;
    }
    public void setSelf(User self) {
        this.self = self;
    }
    private User self;
    private Myapplication myapplication;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.fragment_my, null);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        myapplication =(Myapplication) getActivity().getApplication();
        LinearLayout home = Objects.requireNonNull(getActivity()).findViewById(R.id.layout_my);
        LinearLayout myOrder = getActivity().findViewById(R.id.my_order);
        TextView myorder1= getActivity().findViewById(R.id.btn1);
        TextView mywant= getActivity().findViewById(R.id.btn2);
        TextView mygoods= getActivity().findViewById(R.id.btn3);
        TextView btnSellerOrd = getActivity().findViewById(R.id.btn_seller_ord);
        TextView name= getActivity().findViewById(R.id.name);
        Button buttonCan = getActivity().findViewById(R.id.my_cancel_btn);
        ImageView headPhoto = getActivity().findViewById(R.id.myhead);
        self =((MainActivity)getActivity()).getSelf();
        name.setText(self.getName());

        Bitmap head = ImageUtil.bytes2bitmap(self.getImage());
        if (head !=null){
            Drawable drawable = new BitmapDrawable(getResources(), head);
            headPhoto.setImageDrawable(drawable);
        }else {
            Toast.makeText(getContext(), "头像为空", Toast.LENGTH_SHORT).show();
        }
        buttonCan .setOnClickListener(v -> {
            SharedPreferences userToken =getActivity().getSharedPreferences("userToken",0);
            SharedPreferences.Editor editor = userToken.edit();
            editor.remove("token");
            editor.apply();
            Intent intent = new Intent(getActivity(), Login.class);
            startActivity(intent);


        });
        btnSellerOrd .setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SellerOrdActivity.class);
            intent.putExtra("user",self);
            startActivity(intent);
        });
        home.setOnClickListener(v -> {

            Intent intent = new Intent(getActivity(),UserActivity.class);
            intent.putExtra("user",self);
            startActivityForResult(intent,1);
        });
        myorder1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), OrderActivity.class);
            intent.putExtra("user",self);
            startActivity(intent);
        });
        mywant.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MyWantActivity.class);
            intent.putExtra("user",self);
            startActivity(intent);
        });
        mygoods.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MyGoodsActivity.class);
            intent.putExtra("user",self);
            startActivity(intent);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            self= (User) data.getSerializableExtra("userresult");
        }


    }
}
