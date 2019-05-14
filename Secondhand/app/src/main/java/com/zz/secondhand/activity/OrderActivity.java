package com.zz.secondhand.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;
import com.alibaba.fastjson.JSON;
import com.zz.secondhand.Login;
import com.zz.secondhand.R;
import com.zz.secondhand.adapter.OrderAdapter;
import com.zz.secondhand.entity.*;
import okhttp3.*;
import java.io.IOException;
import java.util.ArrayList;
import static com.zz.secondhand.utils.GlobalVariables.SELECT_PRODUCTORD;
import static com.zz.secondhand.utils.GlobalVariables.TOKEN_EMP;
import static com.zz.secondhand.utils.GlobalVariables.TOKEN_ERROR;

/**
 * @author Administrator
 * @Date: 2019/4/10 20:06
 * @Description:
 */
public class OrderActivity extends Activity {
    private ArrayList<OrderForm> productArrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        SharedPreferences userToken=getSharedPreferences("userToken",0);
        String tokenResult=userToken.getString("token","");

        Intent intent = getIntent();
        ListView orderlistView = findViewById(R.id.order_list);
        User self =(User) intent.getSerializableExtra("user");
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("user_id", self.getId().toString())
                .add("token",tokenResult)
                .build();
        final Request request = new Request.Builder()
                .url(SELECT_PRODUCTORD)
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
                    Intent intent = new Intent(OrderActivity.this, Login.class);
                    startActivity(intent);

                }else if(TOKEN_ERROR.equals(backmess)){
                    Intent intent = new Intent(OrderActivity.this,Login.class);
                    startActivity(intent);
                }else {
                    OrderActivity.this.runOnUiThread(() -> {
                        productArrayList = (ArrayList<OrderForm>) JSON.parseArray(backmess,OrderForm.class);
                        OrderAdapter orderAdapter = new OrderAdapter(OrderActivity.this, R.layout.item_order,productArrayList);
                        orderlistView.setAdapter(orderAdapter);
                    });
                }

            }
        });

    }
}
