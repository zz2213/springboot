package com.msi_pc.secondhand;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.widget.Toast.makeText;

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
                String url="http://192.168.31.114:8080/testBoot/getUser";
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("id",login_edit_account.getText().toString())
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
                        String backmess =response.body().string();
                        if(backmess.equalsIgnoreCase("True"))
                        {
                            makeText(Login.this, "登录成功",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Login.this,Register.class);
                            startActivity(intent);

                        }
                        Log.d("你好", "onResponse: " + backmess);
                    }
                });



            }
        });


    }


}
