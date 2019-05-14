package com.zz.secondhand.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * @title: OrderForm
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/5/1414:33
 */
public class OrderForm  implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private User user;

    private  User business;

    private Product product;

    private Date createtime;

    private String status;

    private String ordernember;

    private String name;

    private String phone;

    private  String address;

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

    public User getBusiness() {
        return business;
    }

    public void setBusiness(User business) {
        this.business = business;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrdernember() {
        return ordernember;
    }

    public void setOrdernember(String ordernember) {
        this.ordernember = ordernember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
