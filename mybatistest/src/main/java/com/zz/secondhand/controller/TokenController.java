package com.zz.secondhand.controller;

import com.alibaba.fastjson.JSON;
import com.zz.secondhand.entity.Token;
import com.zz.secondhand.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 * @title: TokenController
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/2210:11
 */
@Controller
@ResponseBody
@RequestMapping("/Token")
public class TokenController {
    @Autowired
    TokenService tokenService;
    @RequestMapping(value = "find",produces = {"application/json;charset=UTF-8"})
    public String findToken(@RequestParam("token")String token){
        Token token1= JSON.parseObject(token,Token.class);
        return  tokenService.find(token1);
    }
}
