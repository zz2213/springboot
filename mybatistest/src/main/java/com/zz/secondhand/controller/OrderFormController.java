package com.zz.secondhand.controller;

import com.alibaba.fastjson.JSON;
import com.zz.secondhand.entity.OrderForm;
import com.zz.secondhand.entity.ProductOrd;
import com.zz.secondhand.service.OrderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Administrator
 * @title: OrderFormController
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/5/14 15:25
 */
@Controller
@RequestMapping(value = "/OrderForm")
public class OrderFormController {
    @Autowired
    OrderFormService orderFormService;
    @RequestMapping(value = "/create",produces = {"application/json;charset=UTF-8"})
    public String index(@RequestParam("orderForm") String orderForm) {
        OrderForm orderForm1= JSON.parseObject(orderForm, OrderForm.class);
        orderForm1.setOrdernember("SE"+orderForm1.getProduct().getId()+String.valueOf(orderForm1.getCreatetime().getTime()));
        return orderFormService.createOrderForm(orderForm1);
    }

}
