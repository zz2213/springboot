package com.zz.secondhand.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;
import com.alibaba.fastjson.JSON;
import com.cniupay.pay.CNiuPay;
import com.cniupay.pay.enums.PayResultCodeEnum;
import com.cniupay.pay.listener.PayResultListener;
import com.zz.secondhand.R;
import com.zz.secondhand.adapter.MyWantAdapter;
import com.zz.secondhand.adapter.OrderAdapter;
import com.zz.secondhand.entity.Order;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.ProductOrd;
import com.zz.secondhand.entity.User;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

import static com.zz.secondhand.utils.GlobalVariables.CREATE_PRODUCT_URL;
import static com.zz.secondhand.utils.GlobalVariables.FIND_PRODUCT_TYPE;
import static com.zz.secondhand.utils.GlobalVariables.SELECT_PRODUCTORD;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/10 20:06
 * @Description:
 */
public class OrderActivity extends Activity {
    ArrayList<ProductOrd> productArrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        ListView orderlistView = (ListView)findViewById(R.id.order_list);
        User self =(User) intent.getSerializableExtra("user");
        String url=SELECT_PRODUCTORD;
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("user_id", self.getId().toString())
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
                OrderActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        productArrayList = (ArrayList<ProductOrd>) JSON.parseArray(backmess,ProductOrd.class);
                        OrderAdapter orderAdapter = new OrderAdapter(OrderActivity.this, R.layout.item_order,productArrayList);
                        orderlistView.setAdapter(orderAdapter);
                    }
                });
            }
        });

    }
}
