package com.zz.secondhand.vo;

import com.zz.secondhand.entity.User;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * @title: ProductVo
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/23 13:51
 */
public class ProductVo implements Serializable{

        private static final long serialVersionUID = 1L;
        private Integer id;

        private Integer user_id;

        private Date createtime;

        private String title;

        private byte[] image;

        private String description;

        private Long price;

        private String style;

        private String type;

        private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
