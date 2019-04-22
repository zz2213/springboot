package com.zz.secondhand;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.audiofx.AudioEffect;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.io.IOException;
import java.sql.SQLOutput;

import com.alibaba.fastjson.JSON;
import com.zz.secondhand.entity.Token;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.utils.CustomAppliction;
import com.zz.secondhand.utils.Myapplication;
import com.zz.secondhand.utils.ReturnMessage;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.widget.Toast.makeText;
import static com.zz.secondhand.utils.GlobalVariables.*;

public class Login extends Activity {

    private Button register_button;
    private Button login_button;
    private EditText login_edit_pwd;
    private EditText login_edit_account;
    private CheckBox login_Remember;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        register_button = (Button)findViewById(R.id.login_btn_register);
        login_button    = (Button) findViewById(R.id.login_btn_login);
        login_edit_pwd =(EditText) findViewById(R.id.login_edit_pwd);
        login_edit_account =(EditText) findViewById(R.id.login_edit_account);
        login_Remember=(CheckBox)findViewById(R.id.Login_Remember);
        login_Remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(login_Remember.isChecked()){
                    System.out.println("login"+"记住密码");
                    sharedPreferences.edit().putBoolean("ISCHECK",true).commit();
                }
                else {
                    System.out.println("login"+"err");
                    sharedPreferences.edit().putBoolean("ISCHECK",false).commit();
                }
            }
        });

        sharedPreferences=this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("ISCHECK",false)){
            login_Remember.setChecked(true);
            System.out.println("sp:"+sharedPreferences.getString("USER_NAME",""));
            login_edit_account.setText(sharedPreferences.getString("USER_NAME",""));
            login_edit_pwd.setText(sharedPreferences.getString("PASSWORD",""));
        }


        Token token = new Token();
        SharedPreferences userToken=getSharedPreferences("userToken",0);
        String tokenResult=userToken.getString("token","");
        if("".equals(tokenResult)){

        }
        else {
            token=JSON.parseObject(tokenResult,Token.class);
            String url=FIND_TOKEN;
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("token", JSON.toJSONString(token))
                    .build();
            final Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)//默认就是GET请求，可以不写
                    .build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d("login", "onFailure: ");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String backmess = response.body().string();
                    ReturnMessage returnMessage;
                    returnMessage=JSON.parseObject(backmess,ReturnMessage.class);

                    if(SUCCESS.equals(returnMessage.getMess()))
                    {


                        Intent intent = new Intent(Login.this,MainActivity.class);
                        intent.putExtra("user",returnMessage.getUser());
                        startActivity(intent);
                        finish();

                    }else{

                        System.out.println("%%%%%%%%%%%%%%%%%%%%%%");
                    }
                    Log.d("Login", "onResponse: " + backmess);
                }
            });


        }

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
                        Log.d("login", "onFailure: ");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String backmess = response.body().string();
                        ReturnMessage returnMessage = JSON.parseObject(backmess,ReturnMessage.class);

                        if(returnMessage.getMess().equals(SUCCESS))
                        {

                            if (login_Remember.isChecked()){
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.putString("USER_NAME",login_edit_account.getText().toString());

                                editor.putString("PASSWORD",login_edit_pwd.getText().toString());
                                editor.commit();
                            }
                            Token token=returnMessage.getToken();
                            SharedPreferences userToken = getSharedPreferences("userToken",0);
                            SharedPreferences.Editor editor = userToken.edit();
                            System.out.println("Login"+token.toString());
                            editor.putString("token", JSON.toJSONString(token));
                            editor.commit();
                            Intent intent = new Intent(Login.this,MainActivity.class);
                            intent.putExtra("user",returnMessage.getUser());
                            startActivity(intent);
                            finish();

                        }else{
                             Looper.prepare();
                            makeText(Login.this, "用户名已存在",Toast.LENGTH_LONG).show();
                            Looper.loop();
                        }
                        Log.d("login", "onResponse: " + backmess);
                    }
                });



            }
        });


    }


}
