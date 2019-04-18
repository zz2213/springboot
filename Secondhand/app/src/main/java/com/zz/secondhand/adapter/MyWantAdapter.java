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
import com.alibaba.fastjson.JSON;
import com.zz.secondhand.ProductActivity;
import com.zz.secondhand.R;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.ProductOrd;

import java.util.ArrayList;

/**
 * @author Administrator
 * @title: MyWantAdapter
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/1614:40
 */
public class MyWantAdapter extends BaseAdapter {
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected ArrayList<Product> list;
    public MyWantAdapter(Context context,int resource ,ArrayList<Product > list){
        inflater=LayoutInflater.from(context);
        this.context = context;
        this.resource = resource;
        if(list == null){
            System.out.println("对不起  为空" );
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
            viewHolderOrder.textView1=(TextView)convertView.findViewById(R.id.order_goods_status);
            viewHolderOrder.button=(Button) convertView.findViewById(R.id.order_btn_status);
            viewHolderOrder.button_detailed=(Button) convertView.findViewById(R.id.order_btn_detailed);
            convertView.setTag(viewHolderOrder);
        }else{
            viewHolderOrder=(ViewHolderOrder) convertView.getTag();
        }
        viewHolderOrder.textView.setText(list.get(position).getTitle());
        viewHolderOrder.textView1.setText(list.get(position).getStatus());
        if (list.get(position).getStatus().equals("在售"))
        {
            viewHolderOrder.button.setText("下架");
        }
        else
        {
            viewHolderOrder.button.setText(list.get(position).getStatus());
            viewHolderOrder.button.setClickable(false);
        }
        viewHolderOrder.button_detailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = list.get(position).getTitle();
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("product", list.get(position));
                context.startActivity(intent);
            }
        });

        return convertView;
    }
    public static class ViewHolderOrder{
        ImageView image;
        TextView textView;
        TextView textView1;
        Button button;
        Button button_detailed;

    }
}
