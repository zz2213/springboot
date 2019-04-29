package com.zz.secondhand;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.alibaba.fastjson.JSON;
import com.wildma.pictureselector.ImageUtils;
import com.wildma.pictureselector.PictureSelector;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.Token;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.utils.ImageUtil;
import com.zz.secondhand.utils.Myapplication;
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
    private Myapplication myapplication;
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
        Intent intent = getIntent();
        self =(User) intent.getSerializableExtra("user");
         product_type=findViewById(R.id.product_type);
        buy_btn = findViewById(R.id.buy_btn);
        describe = findViewById(R.id.describe);
        EditText title = findViewById(R.id.title);
        EditText price = findViewById(R.id.price);
        EditText qq = findViewById(R.id.qq);
        RadioGroup radioGroup=findViewById(R.id.rg);
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
                RadioButton radioButton=findViewById(radioGroup.getCheckedRadioButtonId());
                product.setStyle(radioButton.getText().toString());
                User user = new User();
                user.setId(self.getId());
                product.setUser(user);
                product.setTitle(title.getText().toString());
                product.getUser().setQq(qq.getText().toString());
                Bitmap bitmap=((BitmapDrawable)select_image.getDrawable()).getBitmap();
                product.setImage(ImageUtil.bitmap2bytes(bitmap));
                product.setCreatetime(new Date());
                myapplication=(Myapplication) getApplication();
                SharedPreferences userToken=getSharedPreferences("userToken",0);
                String tokenResult=userToken.getString("token","");

                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("product",JSON.toJSONString(product))
                        .add("token",tokenResult)
                        .build();
                final Request request = new Request.Builder()
                        .url(CREATE_PRODUCT_URL)
                        .post(requestBody)
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
                        intent.putExtra("user",self);
                        startActivity(intent);
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
