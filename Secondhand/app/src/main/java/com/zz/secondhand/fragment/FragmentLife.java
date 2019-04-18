package com.zz.secondhand.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.alibaba.fastjson.JSON;
import com.zz.secondhand.*;
import com.zz.secondhand.activity.ProductViewActivity;
import com.zz.secondhand.adapter.MyAdapter;
import com.zz.secondhand.entity.Goods;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.User;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

import static android.widget.Toast.makeText;
import static com.zz.secondhand.utils.GlobalVariables.FIND_PRODUCT_STYLE;
import static com.zz.secondhand.utils.GlobalVariables.LOGIN_URL;

/**
 * @author Administrator
 * @title: FragmentLife
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/913:02
 */
public class FragmentLife  extends Fragment
       {
           ArrayList<Product> productArrayList;
           ListView listView;
           private  View view;

           public User getUser() {
               return user;
           }

           public void setUser(User user) {
               this.user = user;
           }

           User user;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_life,container,false);
        return view;
    }

           @Override
           public void onActivityCreated(@Nullable Bundle savedInstanceState) {
               super.onActivityCreated(savedInstanceState);
              listView = (ListView)getActivity().findViewById(R.id.life_list);

               String url=FIND_PRODUCT_STYLE;
               OkHttpClient okHttpClient = new OkHttpClient();
               RequestBody requestBody = new FormBody.Builder()
                       .add("style","生活")
                       .build();
               final Request request = new Request.Builder()
                       .url(url)
                       .post(requestBody)
                       .build();
               Call call = okHttpClient.newCall(request);
               call.enqueue(new Callback() {
                   @Override
                   public void onFailure(Call call, IOException e) {
                       Log.d("你好", "onFailure: ");
                   }

                   @Override
                   public void onResponse(Call call, Response response) throws IOException {
                       String backmess = response.body().string();
                       getActivity().runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               productArrayList = (ArrayList<Product>) JSON.parseArray(backmess,Product.class);
                               MyAdapter adapter = new MyAdapter(getContext(), R.layout.item_goods,productArrayList);
                               listView.setAdapter(adapter);
                               listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                   @Override
                                   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                       String data = productArrayList.get(position).getTitle();
                                       Intent intent = new Intent(getActivity(), ProductViewActivity.class);
                                       intent.putExtra("product",productArrayList.get(position));
                                       intent.putExtra("user",user);
                                       startActivity(intent);
                                   }
                               });
                           }
                       });
                       Log.d("你好", "onResponse: " + backmess);
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
               listView = (ListView)getActivity().findViewById(R.id.life_list);
               String url=FIND_PRODUCT_STYLE;
               OkHttpClient okHttpClient = new OkHttpClient();
               RequestBody requestBody = new FormBody.Builder()
                       .add("style","生活")
                       .build();
               final Request request = new Request.Builder()
                       .url(url)
                       .post(requestBody)
                       .build();
               Call call = okHttpClient.newCall(request);
               call.enqueue(new Callback() {
                   @Override
                   public void onFailure(Call call, IOException e) {
                       Log.d("你好", "onFailure: ");
                   }

                   @Override
                   public void onResponse(Call call, Response response) throws IOException {
                       String backmess = response.body().string();
                       getActivity().runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               productArrayList = (ArrayList<Product>) JSON.parseArray(backmess,Product.class);
                               MyAdapter adapter = new MyAdapter(getContext(), R.layout.item_goods,productArrayList);
                               listView.setAdapter(adapter);
                           }
                       });
                       Log.d("你好", "onResponse: " + backmess);
                   }
               });
           }
       }
