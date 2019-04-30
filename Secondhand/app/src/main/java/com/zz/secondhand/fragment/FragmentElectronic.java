package com.zz.secondhand.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.alibaba.fastjson.JSON;
import com.zz.secondhand.Login;
import com.zz.secondhand.R;
import com.zz.secondhand.activity.ProductViewActivity;
import com.zz.secondhand.adapter.MyAdapter;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.Token;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.utils.Myapplication;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Objects;

import static com.zz.secondhand.utils.GlobalVariables.FIND_PRODUCT_STYLE;
import static com.zz.secondhand.utils.GlobalVariables.TOKEN_EMP;
import static com.zz.secondhand.utils.GlobalVariables.TOKEN_ERROR;

/**
 * @author Administrator
 * @title: FragmentElectronic
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/913:03
 */
public class FragmentElectronic extends Fragment {
    private Myapplication myapplication;
    private ArrayList<Product> productArrayList;
    private SwipeRefreshLayout swipeRefreshLayout;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_electrobic,container,false);
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView listView = Objects.requireNonNull(getActivity()).findViewById(R.id.electrobic_list);
        myapplication=(Myapplication) getActivity().getApplication();
        swipeRefreshLayout=getActivity().findViewById(R.id.swipeLayout1);
        swipeRefreshLayout.setColorSchemeResources(R.color.red,
                R.color.green,
                R.color.black,
                R.color.white);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(R.color.green);
        swipeRefreshLayout.setProgressViewEndTarget(true, 100);
        swipeRefreshLayout.setOnRefreshListener(() -> doData(true));
        doData(false);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getActivity(), ProductViewActivity.class);
            intent.putExtra("product",productArrayList.get(position));
            intent.putExtra("user",user);
            startActivity(intent);
        });

    }
private void doData(boolean isDo){
    ListView listView = Objects.requireNonNull(getActivity()).findViewById(R.id.electrobic_list);
    myapplication=(Myapplication) getActivity().getApplication();
    Token token;
    token=myapplication.getToken();
    OkHttpClient okHttpClient = new OkHttpClient();
    RequestBody requestBody = new FormBody.Builder()
            .add("style","电子")
            .add("token", JSON.toJSONString(token))
            .build();
    final Request request = new Request.Builder()
            .url(FIND_PRODUCT_STYLE)
            .post(requestBody)
            .build();
    Call call = okHttpClient.newCall(request);
    call.enqueue(new Callback() {
        @Override
        public void onFailure(@NonNull Call call, @NonNull IOException e) {
            Log.d("你好", "onFailure: ");
        }

        @Override
        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            assert response.body() != null;
            String backmess = response.body().string();
            if(TOKEN_EMP.equals(backmess))
            {
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);

            }else if(TOKEN_ERROR.equals(backmess)){
                Intent intent = new Intent(getActivity(),Login.class);
                startActivity(intent);
            }else {
                (Objects.requireNonNull(getActivity())).runOnUiThread(() -> {
                    productArrayList = (ArrayList<Product>) JSON.parseArray(backmess,Product.class);
                    MyAdapter adapter = new MyAdapter(getContext(), R.layout.item_goods,productArrayList);
                    listView.setAdapter(adapter);
                    if (isDo){
                        swipeRefreshLayout.setRefreshing(false);
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
        doData(false);

    }

}
