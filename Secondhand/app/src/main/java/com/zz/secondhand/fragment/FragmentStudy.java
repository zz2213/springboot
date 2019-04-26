package com.zz.secondhand.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.alibaba.fastjson.JSON;
import com.zz.secondhand.Login;
import com.zz.secondhand.ProductActivity;
import com.zz.secondhand.R;
import com.zz.secondhand.activity.ProductViewActivity;
import com.zz.secondhand.adapter.MyAdapter;
import com.zz.secondhand.entity.Goods;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.Token;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.utils.Myapplication;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

import static com.zz.secondhand.utils.GlobalVariables.FIND_PRODUCT_STYLE;

/**
 * @author Administrator
 * @title: FragmentStudy
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/913:03
 */
public class FragmentStudy extends Fragment {
    private Myapplication myapplication;
    ArrayList<Product> productArrayList;
    MyAdapter adapter;
    ListView listView;

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
        View view = inflater.inflate(R.layout.fragment_study,container,false);
        return view;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         listView = (ListView)getActivity().findViewById(R.id.study_list);

        myapplication=(Myapplication) getActivity().getApplication();
        Token token = new Token();
        token=myapplication.getToken();
        System.out.println(token.toString());

        SharedPreferences userToken=getActivity().getSharedPreferences("userToken",0);
        String tokenResult=userToken.getString("token","");

        String url=FIND_PRODUCT_STYLE;
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("style","电子")
                .add("token",tokenResult)
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
                if("token为空".equals(backmess))
                {
                    Intent intent = new Intent(getActivity(), Login.class);
                    startActivity(intent);

                }else if("token错误".equals(backmess)){
                    Intent intent = new Intent(getActivity(),Login.class);
                    startActivity(intent);
                }else {
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
                }



                Log.d("你好", "onResponse: " + backmess);
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        listView = (ListView)getActivity().findViewById(R.id.study_list);
        myapplication=(Myapplication) getActivity().getApplication();
        Token token = new Token();
        token=myapplication.getToken();
        String url=FIND_PRODUCT_STYLE;
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("style","学习")
                .add("token", JSON.toJSONString(token))
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
                if("token为空".equals(backmess))
                {
                    Intent intent = new Intent(getActivity(), Login.class);
                    startActivity(intent);

                }else if("token错误".equals(backmess)){
                    Intent intent = new Intent(getActivity(),Login.class);
                    startActivity(intent);
                }else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            productArrayList = (ArrayList<Product>) JSON.parseArray(backmess,Product.class);
                            MyAdapter adapter = new MyAdapter(getContext(), R.layout.item_goods,productArrayList);
                            listView.setAdapter(adapter);
                        }
                    });
                }
                Log.d("你好", "onResponse: " + backmess);
            }
        });

    }
}
