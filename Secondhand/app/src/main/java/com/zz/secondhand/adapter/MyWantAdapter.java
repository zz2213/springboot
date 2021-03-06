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
import com.zz.secondhand.entity.Product;
import java.util.ArrayList;

import static com.zz.secondhand.utils.GlobalVariables.ONSELLER;

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
      ViewHolderOrder viewHolderOrder;
        if(convertView == null) {
            convertView = inflater.inflate(resource, null);
            viewHolderOrder=new ViewHolderOrder();
            viewHolderOrder.textView= convertView.findViewById(R.id.order_goods_title);
            viewHolderOrder.textView1= convertView.findViewById(R.id.order_goods_status);
            viewHolderOrder.button= convertView.findViewById(R.id.order_btn_status);
            viewHolderOrder.buttonDetailed = convertView.findViewById(R.id.order_btn_detailed);
            convertView.setTag(viewHolderOrder);
        }else{
            viewHolderOrder=(ViewHolderOrder) convertView.getTag();
        }
        viewHolderOrder.textView.setText(list.get(position).getTitle());
        viewHolderOrder.textView1.setText(list.get(position).getStatus());
        if (ONSELLER.equals(list.get(position).getStatus()))
        {
            viewHolderOrder.button.setText("下架");
        }
        else
        {
            viewHolderOrder.button.setText(list.get(position).getStatus());
            viewHolderOrder.button.setClickable(false);
        }
        viewHolderOrder.buttonDetailed.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductActivity.class);
            intent.putExtra("product", list.get(position));
            context.startActivity(intent);
        });

        return convertView;
    }
    public static class ViewHolderOrder{
        ImageView image;
        TextView textView;
        TextView textView1;
        Button button;
        Button buttonDetailed;

    }
}
