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
import com.zz.secondhand.entity.Token;
import com.zz.secondhand.entity.User;
import okhttp3.*;
import java.io.IOException;
import java.util.ArrayList;
import static com.zz.secondhand.utils.GlobalVariables.FIND_PRODUCT_TYPE;

/**
 * @author Administrator
 * @title: MyGoodsActivity
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/1616:49
 */
public class MyGoodsActivity extends Activity {
   private String listProduct;
    private ArrayList<Product> productArrayList;
    private String tokenErr="token错误";
    private String tokenEmpty="token为空";
    @Override
    public void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygoods);

        Token token;
        SharedPreferences userToken=getSharedPreferences("userToken",0);
        String tokenResult=userToken.getString("token","");
        token=JSON.parseObject(tokenResult,Token.class);

        ListView orderlistView = findViewById(R.id.mygood_list);
        Intent intent = getIntent();
        User self =(User) intent.getSerializableExtra("user");
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("token",JSON.toJSONString(token))
                .add("type", "商品")
                .add("user_id", self.getId().toString())
                .build();
        final Request request = new Request.Builder()
                .url(FIND_PRODUCT_TYPE)
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
                listProduct = response.body().string();
                if(tokenEmpty.equals(listProduct))
                {
                    Intent intent = new Intent(MyGoodsActivity.this, Login.class);
                    startActivity(intent);

                }else if(tokenErr.equals(listProduct)){
                    Intent intent = new Intent(MyGoodsActivity.this,Login.class);
                    startActivity(intent);
                }else {
                    MyGoodsActivity.this.runOnUiThread(() -> {
                        productArrayList = (ArrayList<Product>) JSON.parseArray(listProduct,Product.class);
                        MyWantAdapter myWantAdapter = new MyWantAdapter(MyGoodsActivity.this, R.layout.item_order, productArrayList);
                        orderlistView.setAdapter(myWantAdapter);
                    });
                }

            }
        });


    }

}
