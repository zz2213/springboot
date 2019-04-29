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
import com.zz.secondhand.adapter.SellerOederAdapter;
import com.zz.secondhand.entity.SellerOrd;
import com.zz.secondhand.entity.Token;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.utils.Myapplication;
import okhttp3.*;
import java.io.IOException;
import java.util.ArrayList;
import static com.zz.secondhand.utils.GlobalVariables.FIND_SELLER_ORDER;
import static com.zz.secondhand.utils.GlobalVariables.TOKEN_EMP;
import static com.zz.secondhand.utils.GlobalVariables.TOKEN_ERROR;

/**
 * @author Administrator
 * @title: SellerOrdActivity
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/189:45
 */
public class SellerOrdActivity extends Activity {
    private ArrayList<SellerOrd> sellerOrdArrayList;
    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_ord);

        Myapplication myapplication = (Myapplication) getApplication();
        Token token;
        token= myapplication.getToken();
        SharedPreferences userToken=getSharedPreferences("userToken",0);
        String tokenResult=userToken.getString("token","");
        Intent intent = getIntent();
        ListView orderlistView = findViewById(R.id.order_seller_list);
        User self =(User) intent.getSerializableExtra("user");
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("user_id", self.getId().toString())
                .add("token",tokenResult)
                .build();
        final Request request = new Request.Builder()
                .url(FIND_SELLER_ORDER)
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
                    Intent intent = new Intent(SellerOrdActivity.this, Login.class);
                    startActivity(intent);

                }else if(TOKEN_ERROR.equals(backmess)){
                    Intent intent = new Intent(SellerOrdActivity.this,Login.class);
                    startActivity(intent);
                }else {
                    SellerOrdActivity.this.runOnUiThread(() -> {
                        sellerOrdArrayList = (ArrayList<SellerOrd>) JSON.parseArray(backmess,SellerOrd.class);
                        SellerOederAdapter sellerOederAdapter = new SellerOederAdapter(SellerOrdActivity.this, R.layout.item_seller_ord,sellerOrdArrayList);
                        orderlistView.setAdapter(sellerOederAdapter);
                    });
                }


            }
        });
    }
}
