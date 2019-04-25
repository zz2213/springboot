package com.zz.secondhand.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.zz.secondhand.Login;
import com.zz.secondhand.MainActivity;
import com.zz.secondhand.R;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.ProductOrd;
import com.zz.secondhand.entity.User;
import okhttp3.*;
import java.io.IOException;
import java.util.Date;
import static com.zz.secondhand.utils.GlobalVariables.CREATE_PRODUCTORD;

/**
 * @author Administrator
 * @title: ProductViewActivity
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/178:55
 */
public class ProductViewActivity extends Activity {

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodduct_view);
        Intent intent = getIntent();
        Product product =(Product) intent.getSerializableExtra("product");
        User user =(User) intent.getSerializableExtra("user");
        TextView product_view_type =findViewById(R.id.product_view_type);
        TextView product_view_title=findViewById(R.id.product_view_title);
        TextView product_view_style= findViewById(R.id.product_view_style);
        TextView product_view_describe=findViewById(R.id.product_view_describe);
        TextView product_view_price= findViewById(R.id.product_view_price);
        TextView product_view_qq=findViewById(R.id.product_view_qq);
        Button product_buy_btn=findViewById(R.id.product_buy_btn);
        product_view_type.setText(product.getType());
        product_view_title.setText(product.getTitle());
        product_view_style.setText(product.getStyle());
        product_view_describe.setText(product.getDescription());
        product_view_price.setText(product.getPrice().toString());
        product_view_qq.setText(product.getUser().getQq());


        product_buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getId().equals(product.getUser().getId()))
                {
                    Toast.makeText(getApplicationContext(), "你不能购买自己的商品", Toast.LENGTH_SHORT).show();
                    System.out.println("你不能购买自己的商品");
                }
                else if("已出售".equals(product.getStatus())){
                    Toast.makeText(getApplicationContext(), "你不能购买已出售的商品", Toast.LENGTH_SHORT).show();
                }
                else{

                    ProductOrd productOrd = new ProductOrd();
                    productOrd.setUser(user);
                    productOrd.setProduct(product);
                    productOrd.setCreatetime(new Date());
//                    productOrd.setStatus(product.getTitle());

                    String url=CREATE_PRODUCTORD;
                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("productOrd", JSON.toJSONString(productOrd))
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
                                             finish();
                                     }
                                 });
                }


            }
        });

    }
}

