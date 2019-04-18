package com.zz.secondhand.controller;

import com.alibaba.fastjson.JSON;
import com.zz.secondhand.entity.ProductOrd;
import com.zz.secondhand.entity.SellerOrd;
import com.zz.secondhand.service.ProductOrdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;

/**
 * @author Administrator
 * @title: ProductOrdController
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/1714:04
 */
@Controller
@ResponseBody
@RequestMapping("/ProductOrdController")
public class ProductOrdController {
    @Autowired
    ProductOrdService productOrdService;
    @RequestMapping("/index")
    public String index(@RequestParam("productOrd") String productOrd){
        ProductOrd productOrd1= JSON.parseObject(productOrd, ProductOrd.class);
        productOrd1.setOrdernember("B"+productOrd1.getProduct().getId()+String.valueOf(productOrd1.getCreatetime().getTime()));
        System.out.println(productOrd1.getOrdernember());
        System.out.println("*********************");
        System.out.println(String.valueOf(productOrd1.getCreatetime().getTime()));
        System.out.println("*********************");
        SellerOrd sellerOrd =new SellerOrd();
        sellerOrd.setUser(productOrd1.getProduct().getUser());
        sellerOrd.setCreatetime(productOrd1.getCreatetime());
        sellerOrd.setProduct(productOrd1.getProduct());
        sellerOrd.setOrdernember("S"+productOrd1.getProduct().getId()+String.valueOf(productOrd1.getCreatetime().getTime()));
        productOrdService.createProductOrd(productOrd1,sellerOrd);
        return  "xx";
    }
    @RequestMapping("/myorder")
    public String selectOrder(@RequestParam("user_id") int user_id){
        return JSON.toJSONString(productOrdService.findProductOrdByUserId(user_id));
    }
    @RequestMapping("/updateorder")
    public  String updateOrder(@RequestParam("number") String number,@RequestParam("status") String status){
        productOrdService.updateProductOrdByuserID(number,status);
        return "xxx";
    }
}
