package com.zz.secondhand.controller;

import com.alibaba.fastjson.JSON;
import com.zz.secondhand.entity.OrderForm;
import com.zz.secondhand.entity.ProductOrd;
import com.zz.secondhand.service.OrderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 * @title: OrderFormController
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/5/14 15:25
 */
@Controller
@ResponseBody
@RequestMapping(value = "/OrderForm")
public class OrderFormController {
    @Autowired
    OrderFormService orderFormService;

    @RequestMapping(value = "/create",produces = {"application/json;charset=UTF-8"})
    public String index(@RequestParam("orderForm") String orderForm) {
        OrderForm orderForm1= JSON.parseObject(orderForm, OrderForm.class);
        orderForm1.setOrdernember("SE"+orderForm1.getProduct().getId()+String.valueOf(orderForm1.getCreatetime().getTime()));
        System.out.println(orderForm1.toString());
        return orderFormService.createOrderForm(orderForm1);
    }
    @RequestMapping(value = "/myorder",produces = {"application/json;charset=UTF-8"})
    public String myOrder(@RequestParam("user_id") int user_id){
        return JSON.toJSONString(orderFormService.queryOrderFormByUserId(user_id));
    }
    @RequestMapping(value = "/sellorder",produces = {"application/json;charset=UTF-8"})
    public String mySeller(@RequestParam("business_id") int business_id){
        return JSON.toJSONString(orderFormService.queryOrderFormByBusiness(business_id));
    }
    @RequestMapping(value = "/update",produces = {"application/json;charset=UTF-8"})
    public String update(@RequestParam("number") String number,@RequestParam("status") String status){
        return JSON.toJSONString(orderFormService.update(number,status));
    }


}
