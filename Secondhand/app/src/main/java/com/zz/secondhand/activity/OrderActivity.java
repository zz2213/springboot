package com.zz.secondhand.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;
import com.alibaba.fastjson.JSON;
import com.zz.secondhand.R;
import com.zz.secondhand.adapter.OrderAdapter;
import com.zz.secondhand.entity.Order;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.ProductOrd;
import com.zz.secondhand.entity.User;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

import static com.zz.secondhand.utils.GlobalVariables.CREATE_PRODUCT_URL;
import static com.zz.secondhand.utils.GlobalVariables.FIND_PRODUCT_TYPE;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/10 20:06
 * @Description:
 */
public class OrderActivity extends Activity {
    ArrayList<Product> productArrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        User self =(User) intent.getSerializableExtra("user");
        String url=FIND_PRODUCT_TYPE;
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("type", "求购")
                .add("user_id", self.getId().toString())
                .build();
        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("你好", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String backmess = response.body().string();
               productArrayList =JSON.parseObject(backmess,ArrayList.class);
                System.out.println(productArrayList.toString());
            }
        });
        ListView orderlistView = (ListView)findViewById(R.id.order_list);
        ArrayList<ProductOrd> orderList = new ArrayList<ProductOrd>();
        for (int i = 0; i < 21; i++) {
            orderList.add(new ProductOrd("status"+i));
        }
        OrderAdapter orderAdapter = new OrderAdapter(this, R.layout.item_order,orderList);
        orderlistView.setAdapter(orderAdapter);

    }
}
