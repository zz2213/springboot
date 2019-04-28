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
import com.zz.secondhand.adapter.MyWantAdapter;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.User;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

import static com.zz.secondhand.utils.GlobalVariables.FIND_PRODUCT_TYPE;
import static com.zz.secondhand.utils.GlobalVariables.TOKEN_EMP;
import static com.zz.secondhand.utils.GlobalVariables.TOKEN_ERROR;

/**
 * @author Administrator
 * @title: MyWantActivity
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/1614:38
 */
public class MyWantActivity extends Activity {
    private ArrayList<Product> productArrayList;
    private String backmess;
    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywant);

        SharedPreferences userToken=getSharedPreferences("userToken",0);
        String tokenResult=userToken.getString("token","");

        ListView orderlistView = findViewById(R.id.mywant_list);
        Intent intent = getIntent();
        User self =(User) intent.getSerializableExtra("user");
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("token",tokenResult)
                .add("type", "求购")
                .add("user_id", self.getId().toString())
                .build();
        Request.Builder builder = new Request.Builder();
        builder.url(FIND_PRODUCT_TYPE);
        builder.post(requestBody);
        final Request request = builder.build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("你好", "onFailure: ");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                assert response.body() != null;
                backmess = response.body().string();
                if(TOKEN_EMP.equals(backmess))
                {
                    Intent intent = new Intent(MyWantActivity.this, Login.class);
                    startActivity(intent);

                }else if(TOKEN_ERROR.equals(backmess)){
                    Intent intent = new Intent(MyWantActivity.this,Login.class);
                    startActivity(intent);
                }else {
                    MyWantActivity.this.runOnUiThread(() -> {
                        productArrayList = (ArrayList<Product>) JSON.parseArray(backmess,Product.class);
                        System.out.println(productArrayList.toString());
                        MyWantAdapter myWantAdapter = new MyWantAdapter(MyWantActivity.this, R.layout.item_order, productArrayList);
                        orderlistView.setAdapter(myWantAdapter);
                    });
                }

            }
        });


    }

}
