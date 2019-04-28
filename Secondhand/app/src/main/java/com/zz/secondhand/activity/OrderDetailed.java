package com.zz.secondhand.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import com.zz.secondhand.R;
import com.zz.secondhand.entity.ProductOrd;

import java.text.SimpleDateFormat;

/**
 * @author Administrator
 * @Date: 2019/4/10 21:09
 * @Description:
 */
public class OrderDetailed extends Activity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detailed);
        Intent intent = getIntent();
        ProductOrd productOrd =(ProductOrd) intent.getSerializableExtra("productord");
        TextView orderUserName = findViewById(R.id.order_user_name);
        orderUserName.setText(productOrd.getUser().getId().toString());
        TextView goodsName = findViewById(R.id.goods_name);
        goodsName.setText(productOrd.getProduct().getTitle());
        TextView orderTime = findViewById(R.id.order_time);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        orderTime.setText(sdf.format(productOrd.getCreatetime()));
        TextView orderNumber = findViewById(R.id.order_number);
        orderNumber.setText(productOrd.getOrdernember());
    }
}
