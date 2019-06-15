package com.zz.secondhand;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.*;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.zz.secondhand.entity.Token;
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

/**
 * @author Administrator
 */
public class Login extends Activity {

    private EditText loginEditPwd;
    private EditText loginEditAccount;
    private CheckBox loginRemember;
    private SharedPreferences sharedPreferences;
    private Myapplication myapplication;
    private String isCheck = "ISCHECK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        myapplication = (Myapplication) getApplication();
        Button registerButton = findViewById(R.id.login_btn_register);
        Button loginButton = findViewById(R.id.login_btn_login);
        loginEditPwd = findViewById(R.id.login_edit_pwd);
        loginEditAccount = findViewById(R.id.login_edit_account);
        loginRemember = findViewById(R.id.Login_Remember);
        loginRemember.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (loginRemember.isChecked()) {
                sharedPreferences.edit().putBoolean(isCheck, true).apply();
            } else {
                sharedPreferences.edit().putBoolean(isCheck, false).apply();
            }
        });

        sharedPreferences = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(isCheck, false)) {
            loginRemember.setChecked(true);
            loginEditAccount.setText(sharedPreferences.getString("USER_NAME", ""));
            loginEditPwd.setText(sharedPreferences.getString("PASSWORD", ""));
        }

        Token token;
        SharedPreferences userToken = getSharedPreferences("userToken", 0);
        String tokenResult = userToken.getString("token", "");
        if ("".equals(tokenResult)) {
            Log.d("token", "kong");
        } else {
            token = JSON.parseObject(tokenResult, Token.class);
            OkHttpClient okHttpClient;
            okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("token", JSON.toJSONString(token))
                    .build();
            final Request request = new Request.Builder()
                    .url(FIND_TOKEN)
                    .post(requestBody)
                    .build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.d("login", "onFailure: ");
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    assert response.body() != null;
                    String backmess = response.body().string();
                    ReturnMessage returnMessage;
                    returnMessage = JSON.parseObject(backmess, ReturnMessage.class);
                    if (SUCCESS.equals(returnMessage.getMess())) {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.putExtra("user", returnMessage.getUser());
                        startActivity(intent);
                        finish();

                    } else {

                    }
                    Log.d("Login", "onResponse: " + backmess);
                }
            });


        }

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
        });
        loginButton.setOnClickListener(v -> {
            if (loginEditAccount.getText().toString().equals("") || loginEditPwd.getText().toString().equals("")) {
                makeText(Login.this, "账号或密码为空", Toast.LENGTH_LONG).show();
            } else {
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("name", loginEditAccount.getText().toString())
                        .add("pass", loginEditPwd.getText().toString())
                        .build();
                final Request request = new Request.Builder()
                        .url(LOGIN_URL)
                        .post(requestBody)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Log.d("login", "onFailure: ");
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        assert response.body() != null;
                        String backmess = response.body().string();
                        ReturnMessage returnMessage = JSON.parseObject(backmess, ReturnMessage.class);
                        if (returnMessage.getMess().equals(SUCCESS)) {
                            if (loginRemember.isChecked()) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("USER_NAME", loginEditAccount.getText().toString());
                                editor.putString("PASSWORD", loginEditPwd.getText().toString());
                                editor.apply();
                            }
                            Token token1 = returnMessage.getToken();
                            SharedPreferences userToken1 = getSharedPreferences("userToken", 0);
                            SharedPreferences.Editor editor = userToken1.edit();
                            editor.putString("token", JSON.toJSONString(token1));
                            editor.apply();

                            myapplication = (Myapplication) getApplication();
                            myapplication.setToken(token1);
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            intent.putExtra("user", returnMessage.getUser());
                            startActivity(intent);
                            finish();

                        } else {
                            Looper.prepare();
                            makeText(Login.this, "账号或密码错误", Toast.LENGTH_LONG).show();
                            Looper.loop();
                        }
                        Log.d("login", "onResponse: " + backmess);
                    }
                });
            }


        });


    }

}
