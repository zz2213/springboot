package com.zz.secondhand.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.zz.secondhand.R;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.utils.ImageUtil;

import java.util.ArrayList;

/**
 * @author Administrator
 * @title: HomeAdapter
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/2815:23
 */
public class HomeAdapter extends BaseAdapter {
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected ArrayList<Product> list;
    public HomeAdapter(Context context,int resource ,ArrayList<Product> list){
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

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeAdapter.ViewHolder vh;
        if(convertView == null){
            convertView = inflater.inflate(resource,null);
            vh = new HomeAdapter.ViewHolder();
            vh.image = convertView.findViewById(R.id.goods_image);
            vh.textView= convertView.findViewById(R.id.goods_title);
            vh.textPrice= convertView.findViewById(R.id.goods_price);
            vh.textStatus= convertView.findViewById(R.id.goods_status);
            convertView.setTag(vh);
        }else{
            vh=(HomeAdapter.ViewHolder)convertView.getTag();
        }

        vh.textView.setText(list.get(position).getTitle());
        vh.textPrice.setText(list.get(position).getPrice().toString());
        vh.textStatus.setText(list.get(position).getStatus());
        Bitmap head = ImageUtil.bytes2bitmap(list.get(position).getImage()) ;
        if (head!=null){
            Drawable drawable = new BitmapDrawable(context.getResources(),head);
            vh.image.setImageDrawable(drawable);
        }else {
            Toast.makeText(context, "图片为空", Toast.LENGTH_SHORT).show();
        }

        return convertView;
    }
    public  static class ViewHolder{
        ImageView image;
        TextView textView;

        TextView textPrice;
        TextView textStatus;
    }
}
