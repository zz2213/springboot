package com.msi_pc.secondhand;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.widget.Toast.makeText;

public class Register extends Activity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Button register_btn_sure=( Button)findViewById(R.id.register_btn_sure);
        EditText resetpwd_edit_name = findViewById(R.id.resetpwd_edit_name);
        EditText resetpwd_edit_pwd = findViewById(R.id.resetpwd_edit_pwd_old);
        EditText resetpwd_edit_pwd_new =findViewById(R.id.resetpwd_edit_pwd_new);
        register_btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="http://192.168.31.114:8080/testBoot/add";
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
                });

            }
        });

    }
}
