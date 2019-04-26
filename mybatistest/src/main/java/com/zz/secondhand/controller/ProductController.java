package com.zz.secondhand.controller;

import com.alibaba.fastjson.JSON;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.service.ProductServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 * @title: ProductController
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/1610:40
 */
@Controller
@ResponseBody
@RequestMapping("/ProductController")
public class ProductController {
    @Autowired
    ProductServive productServive;
    @RequestMapping(value = "/index",produces = {"application/json;charset=UTF-8"})
    public String index(@RequestParam("product") String product){
        Product product1=JSON.parseObject(product, Product.class);
        System.out.println(product1.toString());
        
        productServive.createProduct(product1);
        return  "xx";
    }
    @RequestMapping(value = "/findproductype",produces = {"application/json;charset=UTF-8"})
    public String findByType(@RequestParam("type") String type,@RequestParam("user_id") Integer user_id){
       return JSON.toJSONString(productServive.findProductByType(type,user_id));
    }
    @RequestMapping(value = "/findproductstyle",produces = {"application/json;charset=UTF-8"})
    public String findByStyle(@RequestParam("style") String style){
        System.out.println(JSON.toJSONString(productServive.findProductByStyle(style)));
        return JSON.toJSONString(productServive.findProductByStyle(style));
    }
    @RequestMapping(value = "/updateProductstatus",produces = {"application/json;charset=UTF-8"})
    public String updateProductstatus(@RequestParam("status") String status,@RequestParam("id") Integer id){
        return productServive.updateProductstatus(status,id);
    }
}
