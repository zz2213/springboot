package com.zz.secondhand;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.Token;
import com.zz.secondhand.utils.Myapplication;
import okhttp3.*;
import java.io.IOException;
import static com.zz.secondhand.utils.GlobalVariables.*;

/**
 * @author Administrator
 * @title: ProductActivity
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/1015:15
 */
public class ProductActivity  extends Activity{

    private Myapplication myapplication;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Intent intent = getIntent();
        Product product =(Product) intent.getSerializableExtra("product");
        Button button = findViewById(R.id.pro_buy_btn);
        TextView textView = findViewById(R.id.pro_title);
        TextView proDescribe = findViewById(R.id.pro_describe);
        TextView proPrice = findViewById(R.id.pro_price);
        TextView proQq = findViewById(R.id.pro_qq);
        textView.setText(product.getTitle());
        proDescribe.setText(product.getDescription());
        proPrice.setText(product.getPrice().toString());
        proQq.setText(product.getUser().getQq());
        button.setText("下架");
        Toast.makeText(this,product.getTitle(),Toast.LENGTH_SHORT).show();
        button.setOnClickListener(v -> {
            System.out.println("*******************");
            myapplication=(Myapplication) getApplication();
            Token token;
            token=myapplication.getToken();
            System.out.println(token.toString());

            SharedPreferences userToken=getSharedPreferences("userToken",0);
            String tokenResult=userToken.getString("token","");

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("status","下架")
                    .add("id",product.getId().toString())
                    .add("token",tokenResult)
                    .build();
            final Request request = new Request.Builder()
                    .url(UPDATE_PRODUCT)
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
                        Intent intent1 = new Intent(ProductActivity.this, Login.class);
                        startActivity(intent1);

                    }else if(TOKEN_ERROR.equals(backmess)){
                        Intent intent1 = new Intent(ProductActivity.this,Login.class);
                        startActivity(intent1);
                    }else {
                        finish();
                    }


                }
            });
        });
    }
}
