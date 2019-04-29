package com.zz.secondhand.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.zz.secondhand.Login;
import com.zz.secondhand.R;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.ProductOrd;
import com.zz.secondhand.entity.Token;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.utils.ImageUtil;
import com.zz.secondhand.utils.Myapplication;
import okhttp3.*;
import java.io.IOException;
import java.util.Date;

import static com.zz.secondhand.utils.GlobalVariables.*;

/**
 * @author Administrator
 * @title: ProductViewActivity
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/178:55
 */
public class ProductViewActivity extends Activity {
    private Myapplication myapplication;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodduct_view);
        Intent intent = getIntent();
        Product product =(Product) intent.getSerializableExtra("product");
        User user =(User) intent.getSerializableExtra("user");
        TextView productViewType =findViewById(R.id.product_view_type);
        TextView productViewTitle =findViewById(R.id.product_view_title);
        TextView productViewStyle = findViewById(R.id.product_view_style);
        TextView productViewDescribe =findViewById(R.id.product_view_describe);
        TextView productViewPrice = findViewById(R.id.product_view_price);
        TextView productViewQq =findViewById(R.id.product_view_qq);
        ImageView imageView =findViewById(R.id.product_view_image);
        Button productBuyBtn =findViewById(R.id.product_buy_btn);
        productViewType.setText(product.getType());
        productViewTitle .setText(product.getTitle());
        productViewStyle .setText(product.getStyle());
        productViewDescribe .setText(product.getDescription());
        productViewPrice .setText(product.getPrice().toString());
        productViewQq .setText(product.getUser().getQq());
        Bitmap head = ImageUtil.bytes2bitmap(product.getImage());
        if (head !=null){
            Drawable drawable = new BitmapDrawable(getResources(), head);
            imageView.setImageDrawable(drawable);
        }
        productBuyBtn .setOnClickListener(v -> {
            if(user.getId().equals(product.getUser().getId()))
            {
                Toast.makeText(getApplicationContext(), "你不能购买自己的商品", Toast.LENGTH_SHORT).show();
            }
            else if(ISSELLER.equals(product.getStatus())){
                Toast.makeText(getApplicationContext(), "你不能购买已出售的商品", Toast.LENGTH_SHORT).show();
            }
            else{
                myapplication=(Myapplication) getApplication();
                Token token = myapplication.getToken();
                SharedPreferences userToken=getSharedPreferences("userToken",0);
                String tokenResult=userToken.getString("token","");
                ProductOrd productOrd = new ProductOrd();
                productOrd.setUser(user);
                productOrd.setProduct(product);
                productOrd.setCreatetime(new Date());
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("productOrd", JSON.toJSONString(productOrd))
                        .add("token",tokenResult)
                        .build();
                final Request request = new Request.Builder()
                        .url(CREATE_PRODUCTORD)
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
                                         Intent intent1 = new Intent(ProductViewActivity.this, Login.class);
                                         startActivity(intent1);

                                     }else if(TOKEN_ERROR.equals(backmess)){
                                         Intent intent1 = new Intent(ProductViewActivity.this,Login.class);
                                         startActivity(intent1);
                                     }else {
                                         finish();
                                     }

                                 }
                             });
            }

        });

    }
}

