package com.zz.secondhand.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import com.alibaba.fastjson.JSON;
import com.zz.secondhand.Login;
import com.zz.secondhand.R;
import com.zz.secondhand.entity.*;
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

    AlertDialog.Builder builder;
     Dialog dialog;
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



               builder= new AlertDialog.Builder(ProductViewActivity.this);
               dialog= builder.create();
                View view= LayoutInflater.from(ProductViewActivity.this).inflate(R.layout.dialog_choosepage, null);
                TextView cancel =view.findViewById(R.id.choosepage_cancel);
                TextView sure =view.findViewById(R.id.choosepage_sure);
                EditText name=view.findViewById(R.id.person_name);
                EditText phone=view.findViewById(R.id.person_phone);
                EditText address=view.findViewById(R.id.person_address);
                dialog.setContentView(view);
                dialog.show();
                dialog.getWindow().setContentView(view);
                //使editext可以唤起软键盘
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ProductViewActivity.this, "cancel", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!address.getText().toString().equals("")&& !name.getText().toString().equals("") && !phone.getText().toString().equals("")){
                            myapplication=(Myapplication) getApplication();
                            Token token = myapplication.getToken();
                            SharedPreferences userToken=getSharedPreferences("userToken",0);
                            String tokenResult=userToken.getString("token","");
                            OrderForm orderForm =new OrderForm();
                            orderForm.setUser(user);
                            orderForm.setProduct(product);
                            orderForm.setCreatetime(new Date());
                            orderForm.setStatus("未付款");
                            orderForm.setAddress(address.getText().toString());
                            orderForm.setName(name.getText().toString());
                            orderForm.setPhone(phone.getText().toString());
                            Toast.makeText(ProductViewActivity.this, "sure", Toast.LENGTH_SHORT).show();

                            OkHttpClient okHttpClient = new OkHttpClient();
                            RequestBody requestBody = new FormBody.Builder()
                                    .add("orderForm", JSON.toJSONString(orderForm))
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
                            dialog.dismiss();
                        }else{
                            Toast.makeText(ProductViewActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                        }

                    }
                });




            }

        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog!=null && dialog.isShowing()) { dialog.dismiss(); }
    }
}

