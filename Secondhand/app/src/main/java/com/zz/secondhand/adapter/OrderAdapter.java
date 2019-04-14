package com.zz.secondhand.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.zz.secondhand.ProductActivity;
import com.zz.secondhand.R;
import com.zz.secondhand.entity.Order;

import java.util.ArrayList;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/10 20:08
 * @Description:
 */
public class OrderAdapter  extends BaseAdapter {
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected ArrayList<Order> list;
    public OrderAdapter(Context context,int resource ,ArrayList<Order > list){
        inflater=LayoutInflater.from(context);
        this.context = context;
        this.resource = resource;
        if(list == null){
            this.list=new ArrayList<>();
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
        viewHolderOrder.textView.setText(list.get(position).getStatus());
        viewHolderOrder.button_detailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = list.get(position).getStatus();
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("extra_data",data);
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
