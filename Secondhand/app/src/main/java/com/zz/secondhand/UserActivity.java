package com.zz.secondhand;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.alibaba.fastjson.JSON;
import com.wildma.pictureselector.ImageUtils;
import com.wildma.pictureselector.PictureSelector;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.utils.ImageUtil;
import okhttp3.*;
import java.io.IOException;
import static com.zz.secondhand.utils.GlobalVariables.UPDATE_USER;


/**
 * @author Administrator
 * @Auther: msi-pc
 * @Date: 2019/4/3 20:48
 * @Description:
 */
public class UserActivity extends Activity {
    private ImageView imageView;

    private TextView username;

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
         imageView = findViewById(R.id.image_my);
         username = findViewById(R.id.user_username);
         textrealName = findViewById(R.id.textrealName);
        textUserspassword = findViewById(R.id.textUserspassword);
        textUsersPhone = findViewById(R.id.textUsersPhone);
        textUsersQQ = findViewById(R.id.textUsersQQ);
        EditText textUsersschool= findViewById(R.id.textUsersschool);
        Button button=findViewById(R.id.save);
        textUsersschool.setHint(new SpannableString(self.getSchool()));
        textrealName.setHint(new SpannableString(self.getRealname()));
        username.setHint(new SpannableString(self.getName()));
        textUsersPhone.setHint(new SpannableString(self.getNumber().toString()));
        textUsersQQ.setHint(new SpannableString(self.getQq()));
        textUserspassword.setHint(new SpannableString(self.getPassword()));
        Bitmap head = ImageUtil.bytes2bitmap(self.getImage());
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
        button.setOnClickListener(v -> {
            User newUser = new User();
            newUser.setId(self.getId());
            if(imageUPdate==true){
                Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
                newUser.setImage(ImageUtil.bitmap2bytes(bitmap));
            }

            if(!"".equals(textrealName.getText().toString())){
                newUser.setRealname(textrealName.getText().toString());
            }
            if (!"".equals(textUserspassword.getText().toString())){
                newUser.setPassword(textUserspassword.getText().toString());
            }
            if (!"".equals(textUsersPhone.getText().toString())){
                newUser.setNumber(Long.valueOf(textUsersPhone.getText().toString()));
            }
            if (!"".equals(textUsersQQ.getText().toString())){
                newUser.setQq(textUsersQQ.getText().toString());

            }
            if (!"".equals(textUsersschool.getText().toString())){
                newUser.setSchool(textUsersschool.getText().toString());
            }
            if(null == newUser.getRealname() && null == newUser.getPassword() && null == newUser.getNumber() && null == newUser.getQq() && null == newUser.getSchool()&&imageUPdate==false)
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
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Log.d("你好", "onFailure: ");
                    }
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        assert response.body() != null;
                        String result = response.body().string();
                        if(result.equals("false"))
                        {
                            finish();
                        }
                        else{
                            Intent intent1 =new Intent();
                            intent1.putExtra("userresult",JSON.parseObject(result,User.class));
                           setResult(RESULT_OK,intent1);
                           finish();
                        }
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
                imageView.setImageBitmap(ImageUtils.getBitmap(picturePath));
                imageUPdate=true;
            }
        }
    }
}
