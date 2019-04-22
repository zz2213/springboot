package com.zz.secondhand.controller;

import org.jboss.jandex.Index;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 * @title: ApplicationController
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/2216:05
 */
@Controller
@RequestMapping("/Application")
public class ApplicationController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
