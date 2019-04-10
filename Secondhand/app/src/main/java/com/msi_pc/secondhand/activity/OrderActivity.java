package com.msi_pc.secondhand.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.msi_pc.secondhand.ProductActivity;
import com.msi_pc.secondhand.R;
import com.msi_pc.secondhand.adapter.OrderAdapter;
import com.msi_pc.secondhand.entity.Goods;
import com.msi_pc.secondhand.entity.Order;

import java.sql.BatchUpdateException;
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
