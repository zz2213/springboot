package com.zz.secondhand.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.alibaba.fastjson.JSON;
import com.cniupay.pay.CNiuPay;
import com.cniupay.pay.enums.PayResultCodeEnum;
import com.cniupay.pay.listener.PayResultListener;
import com.zz.secondhand.Login;
import com.zz.secondhand.R;
import com.zz.secondhand.activity.OrderDetailed;
import com.zz.secondhand.entity.ProductOrd;
import com.zz.secondhand.entity.Token;
import com.zz.secondhand.utils.Myapplication;
import okhttp3.*;
import java.io.IOException;
import java.util.ArrayList;

import static com.zz.secondhand.utils.GlobalVariables.TOKEN_EMP;
import static com.zz.secondhand.utils.GlobalVariables.TOKEN_ERROR;
import static com.zz.secondhand.utils.GlobalVariables.UPDATE_ORDER;

/**
 * @author Administrator
 * @Date: 2019/4/10 20:08
 * @Description:
 */
public class OrderAdapter  extends BaseAdapter {
    private Myapplication myapplication;
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected ArrayList<ProductOrd> list;
    public OrderAdapter(Context context,int resource ,ArrayList<ProductOrd > list){
        inflater=LayoutInflater.from(context);
        this.context = context;
        this.resource = resource;
        if(list == null){
            this.list= new ArrayList<>();
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

    @SuppressLint("CutPasteId")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderOrder viewHolderOrder;
        if(convertView == null) {
            convertView = inflater.inflate(resource, null);
            viewHolderOrder=new ViewHolderOrder();
            viewHolderOrder.image= convertView.findViewById(R.id.order_goods_image);
            viewHolderOrder.textView= convertView.findViewById(R.id.order_goods_title);
            viewHolderOrder.button= convertView.findViewById(R.id.order_btn_status);
            viewHolderOrder.buttonDetailed = convertView.findViewById(R.id.order_btn_detailed);
            convertView.setTag(viewHolderOrder);
        }else{
            viewHolderOrder=(ViewHolderOrder) convertView.getTag();
        }
        viewHolderOrder.button.setText(list.get(position).getStatus());
        viewHolderOrder.textView.setText(list.get(position).getProduct().getTitle());
        viewHolderOrder.button.setOnClickListener(v -> {
            switch (list.get(position).getStatus()){
                case "未付款":
                    CNiuPay.getInstance(context).pay(1,"测试",list.get(position).getOrdernember(), new PayResultListener() {
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
                                myapplication=(Myapplication)context.getApplicationContext ();
                                Token token;
                                token=myapplication.getToken();
                                SharedPreferences userToken=context.getSharedPreferences("userToken",0);
                                String tokenResult=userToken.getString("token","");
                                Toast toast = Toast.makeText(context, "已付款等待商家发货", Toast.LENGTH_SHORT);
                                toast.show();
                                OkHttpClient okHttpClient = new OkHttpClient();
                                RequestBody requestBody = new FormBody.Builder()
                                        .add("status", "已付款")
                                        .add("token",tokenResult)
                                        .add("number", list.get(position).getProduct().getId().toString())
                                        .build();
                                final Request request = new Request.Builder()
                                        .url(UPDATE_ORDER)
                                        .post(requestBody)
                                        .build();
                                Call call = okHttpClient.newCall(request);
                                call.enqueue(new Callback() {
                                    @Override
                                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                        Log.d("你好", "onFailure: ");
                                    }

                                    @Override
                                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException
                                    {
                                        assert response.body() != null;
                                        String backmess = response.body().string();
                                        if(TOKEN_EMP.equals(backmess))
                                        {
                                            Intent intent = new Intent(context, Login.class);
                                           context.startActivity(intent);

                                        }else if(TOKEN_ERROR.equals(backmess)){
                                            Intent intent = new Intent(context,Login.class);
                                            context.startActivity(intent);
                                        }else {
                                            viewHolderOrder.button.setText("已付款");
                                        }

                                    }
                                });

                            } else {
                                System.out.println("err");
                            }
                        }
                    });
                    break;
                case "已发货":
                    Toast toast1 = Toast.makeText(context, "已收货", Toast.LENGTH_SHORT);
                    toast1.show();
                    Token token;
                    myapplication=(Myapplication)context.getApplicationContext ();
                    token=myapplication.getToken();
                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("status", "已收货")
                            .add("token", JSON.toJSONString(token))
                            .add("number", list.get(position).getProduct().getId().toString())
                            .build();
                    final Request request = new Request.Builder()
                            .url(UPDATE_ORDER)
                            .post(requestBody)
                            .build();
                    Call call1 = okHttpClient.newCall(request);
                    call1.enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Log.d("你好", "onFailure: ");
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException
                        {
                            assert response.body() != null;
                            String backmess = response.body().string();
                            if(TOKEN_EMP.equals(backmess))
                            {
                                Intent intent = new Intent(context, Login.class);
                                context.startActivity(intent);

                            }else if(TOKEN_ERROR.equals(backmess)){
                                Intent intent = new Intent(context,Login.class);
                                context.startActivity(intent);
                            }else {
                                viewHolderOrder.button.setText("已收货");
                            }


                        }
                    });
                    break;
                    default:
                        break;
            }
            });
        viewHolderOrder.buttonDetailed.setOnClickListener(v -> {
            Intent intent = new Intent(context, OrderDetailed.class);
            intent.putExtra("productord",list.get(position));
            context.startActivity(intent);
        });

        return convertView;
    }
    public static class ViewHolderOrder{
        ImageView image;
        TextView textView;
        Button button;
        Button buttonDetailed;

                      }
            }

