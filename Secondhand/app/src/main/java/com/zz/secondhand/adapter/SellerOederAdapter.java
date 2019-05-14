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
import com.zz.secondhand.Login;
import com.zz.secondhand.R;
import com.zz.secondhand.activity.SellerOrdDetailed;
import com.zz.secondhand.entity.OrderForm;
import com.zz.secondhand.entity.SellerOrd;
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
 * @title: SellerOederAdapter
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/189:49
 */
public class SellerOederAdapter extends BaseAdapter {
    private Myapplication myapplication;
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected ArrayList<OrderForm> list;

    public SellerOederAdapter(Context context, int resource, ArrayList<OrderForm> list) {
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
      SellerOederAdapter.ViewHolderOrder viewHolderOrder;
        if(convertView == null) {
            convertView = inflater.inflate(resource, null);
            viewHolderOrder=new SellerOederAdapter.ViewHolderOrder();
            viewHolderOrder.image= convertView.findViewById(R.id.sell_order_image);
            viewHolderOrder.textView= convertView.findViewById(R.id.sell_order_title);
            viewHolderOrder.textViewst= convertView.findViewById(R.id.sellerorder_status);
            viewHolderOrder.button= convertView.findViewById(R.id.sell_order_status_btn);
            viewHolderOrder.buttonDetailed = convertView.findViewById(R.id.sell_order_detailed);
            convertView.setTag(viewHolderOrder);
        }else{
            viewHolderOrder=(SellerOederAdapter.ViewHolderOrder) convertView.getTag();
        }
        viewHolderOrder.textView.setText(list.get(position).getProduct().getTitle());
        final  Button button1= convertView.findViewById(R.id.sell_order_status_btn);
        viewHolderOrder.button.setText(list.get(position).getStatus());
        viewHolderOrder.button.setOnClickListener(v -> {
            switch (list.get(position).getStatus()){
                case "已付款":
                    Toast toast = Toast.makeText(context, "已付款等待商家发货", Toast.LENGTH_SHORT);
                    toast.show();
                    myapplication=(Myapplication)context.getApplicationContext ();
                    Token token;
                    token=myapplication.getToken();
                    SharedPreferences userToken=context.getSharedPreferences("userToken",0);
                    String tokenResult=userToken.getString("token","");
                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("status", "已发货")
                            .add("token",tokenResult)
                            .add("number",list.get(position).getOrdernember())
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
                                button1.setText("已发货");
                            }


                        }
                    });
                    break;
                case "已收货":
                    Toast toast1 = Toast.makeText(context, "已收钱", Toast.LENGTH_SHORT);
                    toast1.show();
                    OkHttpClient okHttpClient1 = new OkHttpClient();
                    RequestBody requestBody1 = new FormBody.Builder()
                            .add("status", "完成")
                            .add("number", list.get(position).getOrdernember())
                            .build();
                    final Request request1 = new Request.Builder()
                            .url(UPDATE_ORDER)
                            .post(requestBody1)
                            .build();
                    Call call1 = okHttpClient1.newCall(request1);
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
                                button1.setText("完成");
                            }

                        }
                    });
                    break;
                    default:
            }

        });
        viewHolderOrder.buttonDetailed.setOnClickListener(v -> {
            Intent intent = new Intent(context, SellerOrdDetailed.class);
            intent.putExtra("seller0rd",list.get(position));
            context.startActivity(intent);
        });

        return convertView;
    }
    public static class ViewHolderOrder{
        ImageView image;
        TextView textView;
        TextView textViewst;
        Button button;
        Button buttonDetailed;

    }
}
