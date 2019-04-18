package com.zz.secondhand.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;
import com.alibaba.fastjson.JSON;
import com.zz.secondhand.R;
import com.zz.secondhand.adapter.SellerOederAdapter;
import com.zz.secondhand.entity.SellerOrd;
import com.zz.secondhand.entity.User;
import okhttp3.*;
import java.io.IOException;
import java.util.ArrayList;
import static com.zz.secondhand.utils.GlobalVariables.FIND_SELLER_ORDER;

/**
 * @author Administrator
 * @title: SellerOrdActivity
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/189:45
 */
public class SellerOrdActivity extends Activity {
    ArrayList<SellerOrd> sellerOrdArrayList;
    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_ord);
        Intent intent = getIntent();
        ListView orderlistView = (ListView)findViewById(R.id.order_seller_list);
        User self =(User) intent.getSerializableExtra("user");
        String url=FIND_SELLER_ORDER;
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
                SellerOrdActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        sellerOrdArrayList = (ArrayList<SellerOrd>) JSON.parseArray(backmess,SellerOrd.class);
                        SellerOederAdapter sellerOederAdapter = new SellerOederAdapter(SellerOrdActivity.this, R.layout.item_order,sellerOrdArrayList);
                        orderlistView.setAdapter(sellerOederAdapter);
                    }
                });

            }
        });
    }
}
