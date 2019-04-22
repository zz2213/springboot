package com.zz.secondhand;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zz.secondhand.activity.MyGoodsActivity;
import com.zz.secondhand.activity.MyWantActivity;
import com.zz.secondhand.activity.OrderActivity;
import com.zz.secondhand.activity.SellerOrdActivity;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.utils.ImageUtil;

import static android.app.Activity.RESULT_OK;


/**
 * @author Administrator
 * @Auther: msi-pc
 * @Date: 2019/4/2 13:58
 * @Description:
 */

@SuppressLint("ValidFragment")
public class FragmentMy extends Fragment {
    private ImageView head_photo;
    private Bitmap head;

    public User getSelf() {
        return self;
    }

    public void setSelf(User self) {
        this.self = self;
    }

    public User self;


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
        TextView btn_seller_ord=(TextView)getActivity().findViewById(R.id.btn_seller_ord);
        TextView name=(TextView)getActivity().findViewById(R.id.name);
        Button button_can=(Button)getActivity().findViewById(R.id.my_cancel_btn);
        head_photo=(ImageView)getActivity().findViewById(R.id.myhead);
        self =((MainActivity)getActivity()).getSelf();
        name.setText(self.getName());

        head =ImageUtil.Bytes2Bitmap(self.getImage()) ;
        if (head!=null){
            Drawable drawable = new BitmapDrawable(getResources(),head);
            head_photo.setImageDrawable(drawable);
        }else {
            System.out.println(getActivity()+"头像为空");
        }
        button_can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences userToken =getActivity().getSharedPreferences("userToken",0);
                SharedPreferences.Editor editor = userToken.edit();
                editor.remove("token");
                editor.commit();
                Intent intent = new Intent(getActivity(), Login.class);
                getActivity().finish();
                startActivity(intent);


            }
        });
        btn_seller_ord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SellerOrdActivity.class);
                intent.putExtra("user",self);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),UserActivity.class);
                intent.putExtra("user",self);
                startActivityForResult(intent,1);
            }
        });
        myorder1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("user",self);
                startActivity(intent);
            }
        });
        mywant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyWantActivity.class);
                intent.putExtra("user",self);
                startActivity(intent);
            }
        });
        mygoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyGoodsActivity.class);
                intent.putExtra("user",self);
                startActivity(intent);
            }
        });
    }
   public void setself(User user){
        this.self=user;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
            if(self==null){
                System.out.println("1111111111111111111111");
            }
            self= (User) data.getSerializableExtra("userresult");

            System.out.println(self.toString());
        }


    }
}
