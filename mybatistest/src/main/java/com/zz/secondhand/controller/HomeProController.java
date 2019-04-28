package com.zz.secondhand.controller;

import com.zz.secondhand.service.HomeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 * @title: HomeProController
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/2814:04
 */
@Controller
@ResponseBody
@RequestMapping("/HomeController")
public class HomeProController {
    @Autowired
    HomeProductService homeProductService;
    @RequestMapping(value = "/querryhomepro",produces = {"application/json;charset=UTF-8"})
    public String querryHomePro(){
        return homeProductService.querryHomeproduct();
    }
}
