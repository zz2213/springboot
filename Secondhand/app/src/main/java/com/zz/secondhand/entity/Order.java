package com.zz.secondhand.entity;

import java.util.Date;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/10 20:09
 * @Description:
 */
public class Order {
    private Date createtime;
    private float cost;
    private Goods goods;
    private String ordernumber;
    private String status;

    public Order(String status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
