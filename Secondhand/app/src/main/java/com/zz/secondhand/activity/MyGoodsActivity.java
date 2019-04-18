package com.zz.secondhand.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;
import com.alibaba.fastjson.JSON;
import com.zz.secondhand.R;
import com.zz.secondhand.adapter.MyWantAdapter;
import com.zz.secondhand.entity.Product;
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
    String listProduct;
    ArrayList<Product> productArrayList;
    @Override
    public void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygoods);
        ListView orderlistView = (ListView)findViewById(R.id.mygood_list);
        Intent intent = getIntent();
        User self =(User) intent.getSerializableExtra("user");
        String url=FIND_PRODUCT_TYPE;
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("type", "商品")
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
                listProduct = response.body().string();
                MyGoodsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        productArrayList = (ArrayList<Product>) JSON.parseArray(listProduct,Product.class);
                        System.out.println(productArrayList.toString());
                        MyWantAdapter myWantAdapter = new MyWantAdapter(MyGoodsActivity.this, R.layout.item_order, productArrayList);
                        orderlistView.setAdapter(myWantAdapter);
                    }
                });
            }
        });


    }

}
