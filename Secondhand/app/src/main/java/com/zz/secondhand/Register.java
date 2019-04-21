package com.zz.secondhand;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.widget.ImageView;
import android.widget.Toast;
import com.wildma.pictureselector.ImageUtils;
import com.wildma.pictureselector.PictureSelector;
import com.zz.secondhand.entity.Token;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.utils.ImageUtil;

import static android.widget.Toast.makeText;
import static com.zz.secondhand.utils.GlobalVariables.*;

public class Register extends Activity {
    String isSuccess;
    ImageView imageView;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Button register_btn_sure=( Button)findViewById(R.id.register_btn_sure);
        EditText user_name = findViewById(R.id.user_name);
        EditText user_realname = findViewById(R.id.user_realname);
        EditText user_pwd = findViewById(R.id.user_password);
        imageView =findViewById(R.id.head_image);
        EditText user_number = findViewById(R.id.user_number);
        EditText user_school =findViewById(R.id.user_school);
        EditText user_qq =findViewById(R.id.user_qq);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector
                        .create(Register.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 200, 200, 1, 1);
            }
        });
        register_btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User();
                user.setName(user_name.getText().toString());
                user.setRealname(user_realname.getText().toString());
                user.setPassword(user_pwd.getText().toString());
                user.setSchool(user_school.getText().toString());
                user.setNumber( Integer.valueOf(user_number.getText().toString()));
                user.setQq(user_qq.getText().toString());
                Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
                user.setImage(ImageUtil.Bitmap2Bytes(bitmap));

                try {
                    StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    URL url = new URL(REGISTER_URL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setConnectTimeout(2000);
                    httpURLConnection.setReadTimeout(2000);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setRequestProperty("Content-type","application/x-java-serialized-object");
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.connect();
                    OutputStream os= httpURLConnection.getOutputStream();
                    ObjectOutputStream objOut=new ObjectOutputStream(os);
                    /*Map<String,Object> map=new HashMap<String,Object>();
                    map.put("user",user);
                    map.put("token",)*/
                    objOut.writeObject(user);
                    objOut.flush();
                    objOut.close();
                    os.close();
                    if(HttpURLConnection.HTTP_OK==httpURLConnection.getResponseCode()){

                                      //得到httpURLConnection的输入流，这里面包含服务器返回来的java对象
                                     InputStream in=httpURLConnection.getInputStream();

                                       //构建对象输入流，使用readObject()方法取出输入流中的java对象
                                       ObjectInputStream inObj=new ObjectInputStream(in);
                                    isSuccess= (String) inObj.readObject();
                                    if("True".equals(isSuccess))
                                    {
                                        makeText(Register.this, "注册成功", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Register.this,Login.class);
                                        startActivity(intent);
                                    }else{
                                        makeText(Register.this, "用户名重复", Toast.LENGTH_LONG).show();
                                    }

                                      //取出对象里面的数据

                                        //输出日志，在控制台可以看到接收到的数据
                                      Log.w("HTTP", "  :by post");

                                     //关闭创建的流
                                      in.close();
                                     inObj.close();
                                }else{
                                      Log.w("HTTP","Connction failed"+httpURLConnection.getResponseCode());
                                 }



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
             /*   String url="http://192.168.31.114:8080/testBoot/add";
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("username",resetpwd_edit_name.getText().toString())
                        .add("pass",resetpwd_edit_pwd.getText().toString())
                        .add("realname",resetpwd_edit_pwd_new.getText().toString())
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
                        String backmess =response.body().string();
                        Log.d("你好", "onResponse: " + backmess);
                    }
                });*/

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
            }
        }
    }
}
