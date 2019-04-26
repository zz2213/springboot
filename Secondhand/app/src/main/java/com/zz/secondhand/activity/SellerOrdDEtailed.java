package com.zz.secondhand.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;
import com.zz.secondhand.R;
import com.zz.secondhand.entity.ProductOrd;
import com.zz.secondhand.entity.SellerOrd;

import java.text.SimpleDateFormat;

/**
 * @author Administrator
 * @title: SellerOrdDEtailed
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/189:56
 */
public class SellerOrdDEtailed  extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_order_detailed);
        Intent intent = getIntent();

        SellerOrd sellerOrd =(SellerOrd) intent.getSerializableExtra("seller0rd");
        TextView order_user_name = findViewById(R.id.sellerorder_user_name);
        order_user_name.setText(sellerOrd.getUser().getId().toString());
        TextView goods_name = findViewById(R.id.sellergoods_name);
        goods_name.setText(sellerOrd.getProduct().getTitle());
        TextView order_time = findViewById(R.id.sellerorder_time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        order_time.setText(sdf.format(sellerOrd.getCreatetime()));
        TextView order_number = findViewById(R.id.sellerorder_number);
        order_number.setText(sellerOrd.getOrdernember());
        TextView order_address = findViewById(R.id.sellerorder_address);
        order_address.setText(sellerOrd.getAddress());
        Button button =findViewById(R.id.sellerorder_status);
        button.setText(sellerOrd.getStatus());
    }
}
