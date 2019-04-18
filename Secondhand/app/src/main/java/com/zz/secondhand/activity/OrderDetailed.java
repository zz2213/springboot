package com.zz.secondhand.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import com.zz.secondhand.R;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.ProductOrd;

import java.text.SimpleDateFormat;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/10 21:09
 * @Description:
 */
public class OrderDetailed extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detailed);
        Intent intent = getIntent();
        ProductOrd productOrd =(ProductOrd) intent.getSerializableExtra("productord");
        TextView order_user_name = findViewById(R.id.order_user_name);
        order_user_name.setText(productOrd.getUser().getId().toString());
        TextView goods_name = findViewById(R.id.goods_name);
        goods_name.setText(productOrd.getProduct().getTitle());
        TextView order_time = findViewById(R.id.order_time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        order_time.setText(sdf.format(productOrd.getCreatetime()));
        TextView order_number = findViewById(R.id.order_number);
        order_number.setText(productOrd.getOrdernember());
    }
}
