package com.zz.secondhand;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/8 20:48
 * @Description:
 */
public class GoodsActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        String[] ctype = new String[]{"生活", "学习", "电子"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式

        Spinner spinner = super.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

    }
}
