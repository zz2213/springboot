package com.zz.secondhand;

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
import com.alibaba.fastjson.JSON;
import com.zz.secondhand.activity.ProductViewActivity;
import com.zz.secondhand.adapter.MyAdapter;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.Token;
import com.zz.secondhand.utils.Myapplication;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

import static com.zz.secondhand.utils.GlobalVariables.QUERRY_HOME_PRO;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/2 14:10
 * @Description:
 */
public class FragmentHome extends Fragment {
    private Myapplication myapplication;
    private ListView listView;
    ArrayList<Product> productArrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView=(ListView) getActivity().findViewById(R.id.recommend_list);
        myapplication =(Myapplication) getActivity().getApplication();
        Token token=new Token();
        token=myapplication.getToken();
        OkHttpClient okHttpClient =new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("token", JSON.toJSONString(token))
                .build();
        final Request request = new Request.Builder()
                .url(QUERRY_HOME_PRO)
                .post(requestBody)
                .build();
        Call call =okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String backmess = response.body().string();
                System.out.println("00000000000000000000"+backmess);
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
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                }

            }
        });

    }
}
