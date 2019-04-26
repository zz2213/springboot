package com.zz.secondhand.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;
import com.alibaba.fastjson.JSON;
import com.zz.secondhand.Login;
import com.zz.secondhand.R;
import com.zz.secondhand.adapter.MyWantAdapter;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.Token;
import com.zz.secondhand.entity.User;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

import static com.zz.secondhand.utils.GlobalVariables.FIND_PRODUCT_TYPE;

/**
 * @author Administrator
 * @title: MyWantActivity
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/1614:38
 */
public class MyWantActivity extends Activity {
    ArrayList<Product> productArrayList;
    String backmess;
    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywant);

        Token token = new Token();
        SharedPreferences userToken=getSharedPreferences("userToken",0);
        String tokenResult=userToken.getString("token","");

        ListView orderlistView = (ListView)findViewById(R.id.mywant_list);
        Intent intent = getIntent();
        User self =(User) intent.getSerializableExtra("user");
        String url=FIND_PRODUCT_TYPE;
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("token",tokenResult)
                .add("type", "求购")
                .add("user_id", self.getId().toString())
                .build();
        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("你好", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                backmess = response.body().string();
                if("token为空".equals(backmess))
                {
                    Intent intent = new Intent(MyWantActivity.this, Login.class);
                    startActivity(intent);

                }else if("token错误".equals(backmess)){
                    Intent intent = new Intent(MyWantActivity.this,Login.class);
                    startActivity(intent);
                }else {
                    MyWantActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            productArrayList = (ArrayList<Product>) JSON.parseArray(backmess,Product.class);
                            System.out.println(productArrayList.toString());
                            MyWantAdapter myWantAdapter = new MyWantAdapter(MyWantActivity.this, R.layout.item_order, productArrayList);
                            orderlistView.setAdapter(myWantAdapter);
                        }
                    });
                }

            }
        });


    }

}
