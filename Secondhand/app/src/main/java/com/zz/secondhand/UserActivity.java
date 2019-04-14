package com.zz.secondhand;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.wildma.pictureselector.ImageUtils;
import com.wildma.pictureselector.PictureSelector;


/**
 * @Auther: msi-pc
 * @Date: 2019/4/3 20:48
 * @Description:
 */
public class UserActivity extends Activity {
    ImageView imageView;
    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_user);
         imageView = (ImageView) findViewById(R.id.image_my);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector
                        .create(UserActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 200, 200, 1, 1);

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
