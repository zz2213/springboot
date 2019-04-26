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
import com.zz.secondhand.adapter.SellerOederAdapter;
import com.zz.secondhand.entity.SellerOrd;
import com.zz.secondhand.entity.Token;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.utils.Myapplication;
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
    private Myapplication myapplication;
    ArrayList<SellerOrd> sellerOrdArrayList;
    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_ord);

        myapplication=(Myapplication) getApplication();
        Token token = new Token();
        token=myapplication.getToken();
        System.out.println(token.toString());

        SharedPreferences userToken=getSharedPreferences("userToken",0);
        String tokenResult=userToken.getString("token","");

        Intent intent = getIntent();
        ListView orderlistView = (ListView)findViewById(R.id.order_seller_list);
        User self =(User) intent.getSerializableExtra("user");
        String url=FIND_SELLER_ORDER;
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("user_id", self.getId().toString())
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
                    Intent intent = new Intent(SellerOrdActivity.this, Login.class);
                    startActivity(intent);

                }else if("token错误".equals(backmess)){
                    Intent intent = new Intent(SellerOrdActivity.this,Login.class);
                    startActivity(intent);
                }else {
                    SellerOrdActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            sellerOrdArrayList = (ArrayList<SellerOrd>) JSON.parseArray(backmess,SellerOrd.class);
                            System.out.println("))))))))))))"+sellerOrdArrayList.toString());
                            SellerOederAdapter sellerOederAdapter = new SellerOederAdapter(SellerOrdActivity.this, R.layout.item_seller_ord,sellerOrdArrayList);
                            orderlistView.setAdapter(sellerOederAdapter);
                        }
                    });
                }


            }
        });
    }
}
