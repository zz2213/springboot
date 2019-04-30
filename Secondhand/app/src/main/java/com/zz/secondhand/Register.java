package com.zz.secondhand;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import android.widget.ImageView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.wildma.pictureselector.ImageUtils;
import com.wildma.pictureselector.PictureSelector;
import com.zz.secondhand.entity.Token;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.utils.ImageUtil;
import com.zz.secondhand.utils.Myapplication;

import static android.widget.Toast.makeText;
import static com.zz.secondhand.utils.GlobalVariables.*;

/**
 * @author Administrator
 */
public class Register extends Activity {
    private Token token;
    private ImageView imageView;
    private Myapplication myapplication;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myapplication =(Myapplication) getApplication();
        setContentView(R.layout.register);
        Button registerBtnSure = findViewById(R.id.register_btn_sure);
        EditText userName = findViewById(R.id.user_name);
        EditText userRealname = findViewById(R.id.user_realname);
        EditText userPwd = findViewById(R.id.user_password);
        imageView =findViewById(R.id.head_image);
        EditText userNumber = findViewById(R.id.user_number);
        EditText userSchool =findViewById(R.id.user_school);
        EditText userQq =findViewById(R.id.user_qq);
        imageView.setOnClickListener(v -> PictureSelector
                .create(Register.this, PictureSelector.SELECT_REQUEST_CODE)
                .selectPicture(true, 200, 200, 1, 1));
        registerBtnSure .setOnClickListener(v -> {

            User user = new User();
            user.setName(userName.getText().toString());
            user.setRealname(userRealname.getText().toString());
            user.setPassword(userPwd.getText().toString());
            user.setSchool(userSchool.getText().toString());
            user.setNumber( Long.parseLong(userNumber.getText().toString()));
            user.setQq(userQq.getText().toString());
            Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
            user.setImage(ImageUtil.bitmap2bytes(bitmap));

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
                objOut.writeObject(user);
                objOut.flush();
                objOut.close();
                os.close();
                if(HttpURLConnection.HTTP_OK==httpURLConnection.getResponseCode()){
                                  //得到httpURLConnection的输入流，这里面包含服务器返回来的java对象
                                 InputStream in=httpURLConnection.getInputStream();
                                   //构建对象输入流，使用readObject()方法取出输入流中的java对象
                                   ObjectInputStream inObj=new ObjectInputStream(in);
                                token= (Token) inObj.readObject();
                                if(token.getUserId()!=null)
                                {
                                    SharedPreferences userToken = getSharedPreferences("userToken",0);
                                    SharedPreferences.Editor editor = userToken.edit();
                                    editor.putString("token", JSON.toJSONString(token));
                                    editor.apply();
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



            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
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
