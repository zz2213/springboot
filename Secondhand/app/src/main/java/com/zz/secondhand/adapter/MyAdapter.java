package com.zz.secondhand.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.zz.secondhand.R;
import com.zz.secondhand.entity.Goods;

import java.util.ArrayList;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/9 20:12
 * @Description:
 */
public class MyAdapter extends BaseAdapter {
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected ArrayList<Goods> list;
    public MyAdapter(Context context,int resource ,ArrayList<Goods> list){
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
        ViewHolder vh = null;
        if(convertView == null){
            convertView = inflater.inflate(resource,null);
//            if(convertView == null){
//                System.out.println(convertView);
//            }
            vh = new ViewHolder();
            vh.image = (ImageView) convertView.findViewById(R.id.goods_image);
            vh.textView= (TextView) convertView.findViewById(R.id.goods_title);
            convertView.setTag(vh);
        }else{
            vh=(ViewHolder)convertView.getTag();
        }

        vh.textView.setText(list.get(position).getTitle());


        return convertView;
    }
    public  static class ViewHolder{
        ImageView image;
        TextView textView;
    }
}
