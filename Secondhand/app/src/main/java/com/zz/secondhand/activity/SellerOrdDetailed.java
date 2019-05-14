package com.zz.secondhand.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;
import com.zz.secondhand.R;
import com.zz.secondhand.entity.OrderForm;
import com.zz.secondhand.entity.SellerOrd;

import java.text.SimpleDateFormat;

/**
 * @author Administrator
 * @date 2019/4/189:56
 */
public class SellerOrdDetailed extends Activity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_order_detailed);
        Intent intent = getIntent();
        OrderForm sellerOrd =(OrderForm) intent.getSerializableExtra("seller0rd");
        TextView orderUserName = findViewById(R.id.sellerorder_user_name);
        orderUserName.setText(sellerOrd.getUser().getId().toString());
        TextView goodsName = findViewById(R.id.sellergoods_name);
        goodsName.setText(sellerOrd.getProduct().getTitle());
        TextView orderTime = findViewById(R.id.sellerorder_time);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        orderTime.setText(sdf.format(sellerOrd.getCreatetime()));
        TextView orderNumber = findViewById(R.id.sellerorder_number);
        orderNumber.setText(sellerOrd.getOrdernember());
        TextView orderAddress = findViewById(R.id.sellerorder_address);
        orderAddress.setText(sellerOrd.getAddress());
        Button button =findViewById(R.id.sellerorder_status);
        button.setText(sellerOrd.getStatus());
    }
}
