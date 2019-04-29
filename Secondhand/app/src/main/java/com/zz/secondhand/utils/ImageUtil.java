package com.zz.secondhand.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.*;

/**
 * @author Administrator
 * @title: ImageUtil
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/199:02
 */
 public  class ImageUtil {
    public static  Bitmap bytes2bitmap(byte[] b) {
                 if (b.length != 0) {
                         return BitmapFactory.decodeByteArray(b, 0, b.length);
                     } else {
                         return null;
                    }
             }
    public static byte[] bitmap2bytes(Bitmap bm) {
                 ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
                return baos.toByteArray();
            }
}
