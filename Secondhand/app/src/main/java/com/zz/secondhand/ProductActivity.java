package com.zz.secondhand;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.zz.secondhand.entity.Product;

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
        button.setText("取消");
        Toast.makeText(this,product.getTitle(),Toast.LENGTH_SHORT).show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
