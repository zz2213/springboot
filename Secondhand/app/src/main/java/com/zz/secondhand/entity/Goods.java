package com.zz.secondhand.entity;

import android.media.Image;

/**
 * @author Administrator
 * @Auther: msi-pc
 * @Date: 2019/4/9 20:15
 * @Description:
 */
public class Goods {
    private Image image;
    private String title;

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
