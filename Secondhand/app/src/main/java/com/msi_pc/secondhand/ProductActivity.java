package com.msi_pc.secondhand;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;
import com.msi_pc.secondhand.fragment.FragmentElectronic;

/**
 * @author Administrator
 * @title: ProductActivity
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/1015:15
 */
public class ProductActivity  extends Activity{
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
        textView = (TextView)findViewById(R.id.pro_title);
        textView.setText(data);
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
    }
}
