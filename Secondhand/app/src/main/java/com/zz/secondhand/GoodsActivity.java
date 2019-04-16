package com.zz.secondhand;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.alibaba.fastjson.JSON;
import com.wildma.pictureselector.ImageUtils;
import com.wildma.pictureselector.PictureSelector;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.User;
import okhttp3.*;
import java.io.IOException;
import java.util.Date;
import static com.zz.secondhand.utils.GlobalVariables.CREATE_PRODUCT_URL;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/8 20:48
 * @Description:
 */
public class GoodsActivity extends Activity {
    ImageView select_image;
    Product product=new Product();
    TextView product_type;
    Button buy_btn;
    EditText describe;
    User self;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        String[] ctype = new String[]{"生活", "学习", "电子"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式
        Spinner spinner = super.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        Intent intent = getIntent();
        self =(User) intent.getSerializableExtra("user");
         product_type=findViewById(R.id.product_type);
        buy_btn = findViewById(R.id.buy_btn);
        describe = findViewById(R.id.describe);
        EditText title = findViewById(R.id.title);
        EditText price = findViewById(R.id.price);
        EditText qq = findViewById(R.id.qq);




        product_type.setText(intent.getStringExtra("product_type"));
         select_image = findViewById(R.id.select_image);
        select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector
                        .create(GoodsActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 200, 200, 1, 1);
            }
        });
        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               product.setCreatetime(new Date());
               product.setDescription(describe.getText().toString());
                product.setPrice(Long.parseLong(price.getText().toString()));
                product.setStatus("在售");
                product.setType(product_type.getText().toString());
                product.setStyle(spinner.getSelectedItem().toString());
                product.setUser(self);
                product.setTitle(title.getText().toString());


                String url=CREATE_PRODUCT_URL;
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("product",JSON.toJSONString(product))
                        .build();
                final Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)//默认就是GET请求，可以不写
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("你好", "onFailure: ");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Intent intent = new Intent(GoodsActivity.this,MainActivity.class);
                        startActivity(intent);

                        /*MyWantActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });*/
                    }
                });

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                select_image.setImageBitmap(ImageUtils.getBitmap(picturePath));
            }
        }
    }
}
