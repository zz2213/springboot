package com.zz.secondhand.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.cniupay.pay.CNiuPay;
import com.cniupay.pay.enums.PayResultCodeEnum;
import com.cniupay.pay.listener.PayResultListener;
import com.zz.secondhand.R;
import com.zz.secondhand.activity.OrderDetailed;
import com.zz.secondhand.entity.ProductOrd;
import okhttp3.*;
import java.io.IOException;
import java.util.ArrayList;
import static com.zz.secondhand.utils.GlobalVariables.UPDATE_ORDER;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/10 20:08
 * @Description:
 */
public class OrderAdapter  extends BaseAdapter {
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected ArrayList<ProductOrd> list;
    public OrderAdapter(Context context,int resource ,ArrayList<ProductOrd > list){
        inflater=LayoutInflater.from(context);
        this.context = context;
        this.resource = resource;
        if(list == null){
            this.list=new ArrayList<ProductOrd>();
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
        ViewHolderOrder viewHolderOrder=null;
        if(convertView == null) {
            convertView = inflater.inflate(resource, null);
            viewHolderOrder=new ViewHolderOrder();
            viewHolderOrder.image=(ImageView)convertView.findViewById(R.id.order_goods_image);
            viewHolderOrder.textView=(TextView)convertView.findViewById(R.id.order_goods_title);
            viewHolderOrder.button=(Button) convertView.findViewById(R.id.order_btn_status);
            viewHolderOrder.button_detailed=(Button) convertView.findViewById(R.id.order_btn_detailed);
            convertView.setTag(viewHolderOrder);
        }else{
            viewHolderOrder=(ViewHolderOrder) convertView.getTag();
        }
        final  Button button1=(Button) convertView.findViewById(R.id.order_btn_status);
        viewHolderOrder.textView.setText(list.get(position).getProduct().getTitle());
        viewHolderOrder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (list.get(position).getStatus()){
                    case "未付款":
                        CNiuPay.getInstance(context).pay(1,"测试","cs1155555555", new PayResultListener() {
                            /**
                             * 支付完成回调
                             * @param context 上下文
                             * @param payResult 支付状态
                             * @param resultMsg 支付提示信息（失败时返回失败提示）
                             * @param amount 支付金额
                             */
                            @Override
                            public void onPayFinished(Context context, PayResultCodeEnum payResult, String resultMsg, long amount) {
                                if (PayResultCodeEnum.SUCCESS == payResult) {
                                    System.out.println("succ");
                                    Toast toast = (Toast) Toast.makeText(context, "已付款等待商家发货", Toast.LENGTH_SHORT);
                                    toast.show();
                                    String url=UPDATE_ORDER;
                                    OkHttpClient okHttpClient = new OkHttpClient();
                                    RequestBody requestBody = new FormBody.Builder()
                                            .add("status", "已付款")
                                            .add("number", list.get(position).getProduct().getId().toString())
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
                                            button1.setText("已付款");
                                        }
                                    });

                                } else {
                                    System.out.println("err");
                                }
                            }
                        });

                        break;
                    case "已发货":
                        Toast toast1 = (Toast) Toast.makeText(context, "已收货", Toast.LENGTH_SHORT);
                        toast1.show();
                        OkHttpClient okHttpClient1 = new OkHttpClient();
                        RequestBody requestBody1 = new FormBody.Builder()
                                .add("status", "已收货")
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
                                button1.setText("已收货");

                            }
                        });
                        break;
                }
                }

        });
        viewHolderOrder.button.setText(list.get(position).getStatus());
        System.out.println("************************");
        System.out.println(list.get(position).getProduct().getTitle());
        System.out.println("************************");
        viewHolderOrder.button_detailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailed.class);
                intent.putExtra("productord",list.get(position));
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

