package com.zz.secondhand.vo;

import java.io.Serializable;

/**
 * @author Administrator
 * @title: SellerOrdVo
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/25 10:08
 */
public class SellerOrdVo implements Serializable {
    private static final long serialVersionUID = 1L;
    Integer id;
    Integer user_id;
    String user_name;
    Integer product_id;
    String product_title;
    long  product_price;
    String createtime;
    String ordernember;
    String address;
    String status;

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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public long getProduct_price() {
        return product_price;
    }

    public void setProduct_price(long product_price) {
        this.product_price = product_price;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getOrdernember() {
        return ordernember;
    }

    public void setOrdernember(String ordernember) {
        this.ordernember = ordernember;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SellerOrdVo{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", product_id=" + product_id +
                ", product_title='" + product_title + '\'' +
                ", product_price=" + product_price +
                ", createtime='" + createtime + '\'' +
                ", ordernember='" + ordernember + '\'' +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
