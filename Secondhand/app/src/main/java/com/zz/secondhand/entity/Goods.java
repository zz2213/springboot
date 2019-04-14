package com.zz.secondhand.entity;

import android.media.Image;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/9 20:15
 * @Description:
 */
public class Goods {
    Image image;
    String title;

    public Goods(String title) {
        this.title = title;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
