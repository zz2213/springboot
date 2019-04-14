package com.zz.secondhand.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import com.zz.secondhand.R;
import com.zz.secondhand.adapter.OrderAdapter;
import com.zz.secondhand.entity.Order;

import java.util.ArrayList;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/10 20:06
 * @Description:
 */
public class OrderActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ListView orderlistView = (ListView)findViewById(R.id.order_list);
        ArrayList<Order> orderList = new ArrayList<Order>();
        for (int i = 0; i < 21; i++) {
            orderList.add(new Order("status"+i));
        }
        OrderAdapter orderAdapter = new OrderAdapter(this, R.layout.item_order,orderList);
        orderlistView.setAdapter(orderAdapter);

    }
}
