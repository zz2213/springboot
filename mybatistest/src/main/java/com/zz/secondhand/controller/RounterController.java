package com.zz.secondhand.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 * @title: RounterController
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/2811:14
 */
@Controller
@RequestMapping("/")
public class RounterController {
    @RequestMapping("/homeProduct")
    public String homeproductrounter(){
        return "homeproduct";
    }
    @RequestMapping(value = "/productupdate",produces = {"application/json;charset=UTF-8"})
    public String productup(){

        return "productupdate";
    }
    @RequestMapping(value = "/userupdate",produces = {"application/json;charset=UTF-8"})
    public String userup(){

        return "userupdate";
    }
    @RequestMapping(value = "/user",produces = {"application/json;charset=UTF-8"})
    public String userrounter(){
        return "user";
    }
    @RequestMapping(value = "/admin",produces = {"application/json;charset=UTF-8"})
    public String adminrounter(){
        return "admin";
    }
    @RequestMapping(value = "/sellerupdate",produces = {"application/json;charset=UTF-8"})
    public String sellerupdate(){

        return "sellerupdate";
    }

    @RequestMapping(value = "/buyerupdate",produces = {"application/json;charset=UTF-8"})
    public String buyerupdate(){

        return "buyerupdate";
    }

    @RequestMapping(value = "/studyproduct",produces = {"application/json;charset=UTF-8"})
    public String study(){

        return "studyproduct";
    }
    @RequestMapping(value = "/product",produces = {"application/json;charset=UTF-8"})
    public String index(){
        return "product";
    }

    @RequestMapping(value = "/buyerord",produces = {"application/json;charset=UTF-8"})
    public String buyerord(){
        return "buyerord";
    }

    @RequestMapping(value = "/sellerord",produces = {"application/json;charset=UTF-8"})
    public String sellerord(){
        return "seller";
    }
    @RequestMapping(value = "/adminupdate",produces = {"application/json;charset=UTF-8"})
    public String adminupdate(){
        return "adminupdate";
    }

    @RequestMapping(value = "/adminadd",produces = {"application/json;charset=UTF-8"})
    public String adminadd(){
        return "adminadd";
    }

}
