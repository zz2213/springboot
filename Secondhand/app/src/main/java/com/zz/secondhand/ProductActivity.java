package com.zz.secondhand;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.User;
import okhttp3.*;

import java.io.IOException;

import static android.widget.Toast.makeText;
import static com.zz.secondhand.utils.GlobalVariables.LOGIN_URL;
import static com.zz.secondhand.utils.GlobalVariables.UPDATE_PRODUCT;

/**
 * @author Administrator
 * @title: ProductActivity
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/1015:15
 */
public class ProductActivity  extends Activity{
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Intent intent = getIntent();
        Product product =(Product) intent.getSerializableExtra("product");
        Button button = findViewById(R.id.pro_buy_btn);
        textView = (TextView)findViewById(R.id.pro_title);
        textView.setText(product.getTitle());
        button.setText("下架");
        Toast.makeText(this,product.getTitle(),Toast.LENGTH_SHORT).show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("*******************");
                String url=UPDATE_PRODUCT;
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("status","下架")
                        .add("id",product.getId().toString())
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
                        finish();

                    }
                });
            }
        });
    }
}
