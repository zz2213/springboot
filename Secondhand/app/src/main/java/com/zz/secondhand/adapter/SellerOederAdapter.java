package com.zz.secondhand.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.zz.secondhand.R;
import com.zz.secondhand.activity.OrderDetailed;
import com.zz.secondhand.activity.SellerOrdDEtailed;
import com.zz.secondhand.entity.ProductOrd;
import com.zz.secondhand.entity.SellerOrd;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

import static com.zz.secondhand.utils.GlobalVariables.UPDATE_ORDER;

/**
 * @author Administrator
 * @title: SellerOederAdapter
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/189:49
 */
public class SellerOederAdapter extends BaseAdapter {
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected ArrayList<SellerOrd> list;

    public SellerOederAdapter(Context context, int resource, ArrayList<SellerOrd> list) {
        inflater=LayoutInflater.from(context);
        this.context = context;
        this.resource = resource;
        if(list == null){
            this.list=new ArrayList<SellerOrd>();
        }else{
            this.list=list;
        }
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      OrderAdapter.ViewHolderOrder viewHolderOrder=null;
        if(convertView == null) {
            convertView = inflater.inflate(resource, null);
            viewHolderOrder=new OrderAdapter.ViewHolderOrder();
            viewHolderOrder.image=(ImageView)convertView.findViewById(R.id.order_goods_image);
            viewHolderOrder.textView=(TextView)convertView.findViewById(R.id.order_goods_title);
            viewHolderOrder.button=(Button) convertView.findViewById(R.id.order_btn_status);
            viewHolderOrder.button_detailed=(Button) convertView.findViewById(R.id.order_btn_detailed);
            convertView.setTag(viewHolderOrder);
        }else{
            viewHolderOrder=(OrderAdapter.ViewHolderOrder) convertView.getTag();
        }
        viewHolderOrder.textView.setText(list.get(position).getProduct().getTitle());
        final  Button button1=(Button) convertView.findViewById(R.id.order_btn_status);
        viewHolderOrder.button.setText(list.get(position).getStatus());
        viewHolderOrder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (list.get(position).getStatus()){
                    case "已付款":
                        Toast toast = (Toast) Toast.makeText(context, "已付款等待商家发货", Toast.LENGTH_SHORT);
                        toast.show();
                        String url=UPDATE_ORDER;
                        OkHttpClient okHttpClient = new OkHttpClient();
                        RequestBody requestBody = new FormBody.Builder()
                                .add("status", "已发货")
                                .add("number",list.get(position).getProduct().getId().toString())
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
                            public void onResponse(Call call, Response response) throws IOException
                            {

                                button1.setText("已发货");
                            }
                        });
                        break;
                    case "已收货":
                        Toast toast1 = (Toast) Toast.makeText(context, "已收钱", Toast.LENGTH_SHORT);
                        toast1.show();
                        OkHttpClient okHttpClient1 = new OkHttpClient();
                        RequestBody requestBody1 = new FormBody.Builder()
                                .add("status", "完成")
                                .add("number", list.get(position).getProduct().getId().toString())
                                .build();
                        final Request request1 = new Request.Builder()
                                .url(UPDATE_ORDER)
                                .post(requestBody1)
                                .build();
                        Call call1 = okHttpClient1.newCall(request1);
                        call1.enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.d("你好", "onFailure: ");
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException
                            {
                                button1.setText("完成");
                            }
                        });
                        break;
                }

            }

        });
        viewHolderOrder.button_detailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SellerOrdDEtailed.class);
                intent.putExtra("seller0rd",list.get(position));
                context.startActivity(intent);
            }
        });

        return convertView;
    }
    public static class ViewHolderOrder{
        ImageView image;
        TextView textView;
        Button button;
        Button button_detailed;

    }
}
