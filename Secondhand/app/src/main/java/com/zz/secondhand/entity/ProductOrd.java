package com.zz.secondhand.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 */
public class ProductOrd implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private User user;

    private Product product;

    private Date createtime;

    private String status;

    private String ordernember;

    private  String address;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAddress(String address) {
        this.address = address;
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


    public String getOrdernember() {
        return ordernember;
    }

    public void setOrdernember(String ordernember) {
        this.ordernember = ordernember == null ? null : ordernember.trim();
    }
}