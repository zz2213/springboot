package com.zz.secondhand;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.alibaba.fastjson.JSON;
import com.zz.secondhand.activity.ProductViewActivity;
import com.zz.secondhand.adapter.HomeAdapter;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.Token;
import com.zz.secondhand.utils.Myapplication;
import okhttp3.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import static com.zz.secondhand.utils.GlobalVariables.QUERRY_HOME_PRO;
import static com.zz.secondhand.utils.GlobalVariables.TOKEN_EMP;
import static com.zz.secondhand.utils.GlobalVariables.TOKEN_ERROR;

/**
 * @author msi-pc
 * @Date: 2019/4/2 14:10
 * @Description:
 */
public class FragmentHome extends Fragment {
    private ListView listView;
    private ArrayList<Product> productArrayList;
    private SwipeRefreshLayout swipeRefreshLayout;
    Myapplication myapplication;
    @Nullable
    @Override


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.fragment_home,null);
        return view;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView= Objects.requireNonNull(getActivity()).findViewById(R.id.recommend_list);
        myapplication = (Myapplication) getActivity().getApplication();
        swipeRefreshLayout=getActivity().findViewById(R.id.swipeLayouthome);
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
            startActivity(intent);
        });

    }
    private void doData(boolean isDo){
        Token token;
        token= myapplication.getToken();
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
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                assert response.body() != null;

                String backmess = response.body().string();
                System.out.println("test "+backmess);
                if(TOKEN_EMP.equals(backmess))
                {
                    Intent intent = new Intent(getActivity(), Login.class);
                    startActivity(intent);

                }else if(TOKEN_ERROR.equals(backmess)){
                    Intent intent = new Intent(getActivity(),Login.class);
                    startActivity(intent);
                }else {
                    Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                        System.out.println("adapter");
                        productArrayList = (ArrayList<Product>) JSON.parseArray(backmess,Product.class);
                        HomeAdapter adapter = new HomeAdapter(getContext(), R.layout.item_goods,productArrayList);
                        listView.setAdapter(adapter);
                        if (isDo){
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });
                }

            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        doData(false);
    }


}
