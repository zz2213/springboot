package com.zz.secondhand.controller;

import com.alibaba.fastjson.JSON;
import com.zz.secondhand.service.SellerOrdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 * @title: OrderSellerController
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/1810:21
 */
@Controller
@ResponseBody
@RequestMapping("/SellerOrdController")
public class OrderSellerController {
    @Autowired
    SellerOrdService sellerOrdService;
    @RequestMapping("/findsellordbyid")
    public String findByid(@RequestParam("user_id") String user_id){
        return JSON.toJSONString(sellerOrdService.findSellerOrdByUserId(user_id));
    }
}
