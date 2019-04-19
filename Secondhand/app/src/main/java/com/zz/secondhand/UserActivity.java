package com.zz.secondhand;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.alibaba.fastjson.JSON;
import com.wildma.pictureselector.ImageUtils;
import com.wildma.pictureselector.PictureSelector;
import com.zz.secondhand.activity.MyGoodsActivity;
import com.zz.secondhand.adapter.MyWantAdapter;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.utils.CustomAppliction;
import com.zz.secondhand.utils.ImageUtil;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

import static com.zz.secondhand.utils.GlobalVariables.FIND_PRODUCT_TYPE;
import static com.zz.secondhand.utils.GlobalVariables.UPDATE_USER;


/**
 * @Auther: msi-pc
 * @Date: 2019/4/3 20:48
 * @Description:
 */
public class UserActivity extends Activity {
    ImageView imageView;

    TextView username;

    EditText textrealName;

    EditText textUserspassword;

    EditText textUsersPhone;

    EditText textUsersQQ;

    EditText textUsersschool;

    boolean imageUPdate=false;

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_user);
        Intent intent = getIntent();
        User self =(User) intent.getSerializableExtra("user");
         imageView = (ImageView) findViewById(R.id.image_my);
         username = (TextView) findViewById(R.id.user_username);
         textrealName = (EditText) findViewById(R.id.textrealName);
        textUserspassword =(EditText)findViewById(R.id.textUserspassword);
        textUsersPhone =(EditText) findViewById(R.id.textUsersPhone);
        textUsersQQ = (EditText)findViewById(R.id.textUsersQQ);
        EditText textUsersschool=(EditText)findViewById(R.id.textUsersschool);
        Button button=findViewById(R.id.save);
        textUsersschool.setHint(new SpannableString(self.getSchool()));
        textrealName.setHint(new SpannableString(self.getRealname()));
        username.setHint(new SpannableString(self.getName()));
        textUsersPhone.setHint(new SpannableString(self.getNumber().toString()));
        textUsersQQ.setHint(new SpannableString(self.getQq()));
        textUserspassword.setHint(new SpannableString(self.getPassword()));
        Bitmap head = ImageUtil.Bytes2Bitmap(self.getImage()) ;
        if (head!=null) {
            Drawable drawable = new BitmapDrawable(getResources(), head);
            imageView.setImageDrawable(drawable);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector
                        .create(UserActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 200, 200, 1, 1);

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User newUser = new User();
                newUser.setId(self.getId());

                if(!"".equals(textrealName.getText().toString())){
                    newUser.setRealname(textrealName.getText().toString());
                }
                if (!"".equals(textUserspassword.getText().toString())){
                    newUser.setPassword(textUserspassword.getText().toString());
                }
                if (!"".equals(textUsersPhone.getText().toString())){
                    newUser.setNumber(Integer.valueOf(textUsersPhone.getText().toString()));
                }
                if (!"".equals(textUsersQQ.getText().toString())){
                    newUser.setQq(textUsersQQ.getText().toString());

                }
                if (!"".equals(textUsersschool.getText().toString())){
                    newUser.setSchool(textUsersschool.getText().toString());
                }
                System.out.println(newUser.toString());
                System.out.println(textUsersPhone.getText().toString());
                if(null==newUser.getRealname() && null==newUser.getPassword()&&null==newUser.getNumber()&&null==newUser.getQq()&&null==newUser.getQq())
                {
                  Toast toast=  Toast.makeText(UserActivity.this,"请输入修改内容",Toast.LENGTH_SHORT);
                  toast.show();
                }else{
                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("user", JSON.toJSONString(newUser))
                            .build();
                    final Request request = new Request.Builder()
                            .url(UPDATE_USER)
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
                            String result = response.body().string();
                            if(result.equals("false"))
                            {
                                System.out.println("error");
                                finish();
                            }
                            else{

                                Intent intent1 =new Intent();
                                intent1.putExtra("userresult",JSON.parseObject(result,User.class));
                                System.out.println("#################"+JSON.parseObject(result,User.class));
                               setResult(RESULT_OK,intent1);
                               finish();

                            }
                        }
                    });
                }


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                imageView.setImageBitmap(ImageUtils.getBitmap(picturePath));
                imageUPdate=true;
            }
        }
    }
}
