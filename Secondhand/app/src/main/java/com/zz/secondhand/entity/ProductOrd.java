package com.zz.secondhand.entity;

import java.util.Date;

public class ProductOrd {
    private Integer id;

    private User user;

    private Product product;

    private Date createtime;

    private String title;

    private String ordernember;

    public ProductOrd() {
    }

    public ProductOrd(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
        this.title = title == null ? null : title.trim();
    }

    public String getOrdernember() {
        return ordernember;
    }

    public void setOrdernember(String ordernember) {
        this.ordernember = ordernember == null ? null : ordernember.trim();
    }
}