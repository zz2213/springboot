package com.zz.secondhand;

import android.app.Activity;
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

import com.zz.secondhand.entity.User;

import static android.widget.Toast.makeText;

public class Register extends Activity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Button register_btn_sure=( Button)findViewById(R.id.register_btn_sure);
        EditText user_name = findViewById(R.id.user_name);
        EditText user_pwd = findViewById(R.id.user_password);
        EditText user_school =findViewById(R.id.user_school);
        register_btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User();
                user.setName(user_name.getText().toString());
                user.setPassword(user_pwd.getText().toString());
                try {
                    StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    URL url = new URL("http://192.168.31.114:8080//myserv/registor");
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
                    objOut.writeObject(user);
                    objOut.flush();
                    objOut.close();
                    os.close();
                    if(HttpURLConnection.HTTP_OK==httpURLConnection.getResponseCode()){

                                      //得到httpURLConnection的输入流，这里面包含服务器返回来的java对象
                                     InputStream in=httpURLConnection.getInputStream();

                                       //构建对象输入流，使用readObject()方法取出输入流中的java对象
                                       ObjectInputStream inObj=new ObjectInputStream(in);
                                      user= (User) inObj.readObject();

                                      //取出对象里面的数据

                                        //输出日志，在控制台可以看到接收到的数据
                                      Log.w("HTTP", user.getPassword()+"  :by post");

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
}
