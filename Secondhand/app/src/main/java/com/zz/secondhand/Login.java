package com.zz.secondhand;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.utils.Myapplication;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.widget.Toast.makeText;
import static com.zz.secondhand.utils.GlobalVariables.LOGIN_URL;

public class Login extends Activity {

    private Button register_button;
    private Button login_button;
    private EditText login_edit_pwd;
    private EditText login_edit_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        register_button = (Button)findViewById(R.id.login_btn_register);
        login_button    = (Button) findViewById(R.id.login_btn_login);
        login_edit_pwd =(EditText) findViewById(R.id.login_edit_pwd);
        login_edit_account =(EditText) findViewById(R.id.login_edit_account);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=LOGIN_URL;
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("name",login_edit_account.getText().toString())
                        .add("pass",login_edit_pwd.getText().toString())
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
                        String backmess = response.body().string();
                        User user = JSON.parseObject(backmess,User.class);
                        if(user!=null)
                        {
                            /*Looper.prepare();
                            makeText(Login.this, "登录成功",Toast.LENGTH_LONG).show();
                            Looper.loop();*/
                            Intent intent = new Intent(Login.this,MainActivity.class);
                            intent.putExtra("user",user);
                            startActivity(intent);
                            finish();

                        }else{
                             Looper.prepare();
                            makeText(Login.this, "用户名已存在",Toast.LENGTH_LONG).show();
                            Looper.loop();
                        }
                        Log.d("你好", "onResponse: " + backmess);
                    }
                });



            }
        });


    }


}
