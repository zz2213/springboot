package com.zz.secondhand.controller;

import com.alibaba.fastjson.JSONObject;
import com.zz.secondhand.entity.Admin;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.service.AdminService;
import com.zz.secondhand.service.ProductServive;
import com.zz.secondhand.service.UserService;
import com.zz.secondhand.vo.ProductVo;
import com.zz.secondhand.vo.dto.ProductDto;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @title: ApplicationController
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/22 16:05
 */
@Controller
@RequestMapping("/")
public class ApplicationController {
    @Autowired
    AdminService adminService;
    @Autowired
    ProductServive productServive;

    @RequestMapping("/product")
    public String index(){
        return "product";
    }
    @RequestMapping("/studyproduct")
    public String study(){
        return "studyproduct";
    }
    @ResponseBody
    @RequestMapping("/showDataStudy")
    public String studyshow(
            @RequestParam(required = false,defaultValue ="1" ) int page,
            @RequestParam(required = false,defaultValue ="15") int limit
    ){
        List<ProductVo> datas=productServive.queryProductByStyle(page,limit,"学习");
        ArrayList<ProductDto> list= new ArrayList<ProductDto>();
        for(int i=0;i<datas.size();i++){
            ProductDto productDto=new ProductDto();
            productDto.setId(datas.get(i).getId());
            productDto.setCreatetime(datas.get(i).getCreatetime());
            productDto.setDescription(datas.get(i).getDescription());
            Base64 encoder = new Base64();
            String stringBase64 =(datas.get(i).getImage() != null ? encoder.encodeToString(datas.get(i).getImage()) : "");
            productDto.setImage(stringBase64);
            productDto.setPrice(datas.get(i).getPrice());
            productDto.setStatus(datas.get(i).getStatus());
            productDto.setTitle(datas.get(i).getTitle());
            productDto.setType(datas.get(i).getType());
            productDto.setUser_id(datas.get(i).getUser_id());
            list.add(productDto);
        }
        int countx = productServive.queryAllCount("学习");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",countx);
        jsonObject.put("data",list);
        System.out.println( jsonObject.toString());
        return  jsonObject.toJSONString();
    }

    @RequestMapping("/elctronicproduct")
    public String electronic(){
        return "elctronicproduct";
    }
    @ResponseBody
    @RequestMapping("/showDataEle")
    public String elctronicshow(
            @RequestParam(required = false,defaultValue ="1" ) int page,
            @RequestParam(required = false,defaultValue ="15") int limit
    ){
        List<ProductVo> datas=productServive.queryProductByStyle(page,limit,"电子");
        ArrayList<ProductDto> list= new ArrayList<ProductDto>();
        for(int i=0;i<datas.size();i++){
            ProductDto productDto=new ProductDto();
            productDto.setId(datas.get(i).getId());
            productDto.setCreatetime(datas.get(i).getCreatetime());
            productDto.setDescription(datas.get(i).getDescription());
            Base64 encoder = new Base64();
            String stringBase64 =(datas.get(i).getImage() != null ? encoder.encodeToString(datas.get(i).getImage()) : "");
            productDto.setImage(stringBase64);
            productDto.setPrice(datas.get(i).getPrice());
            productDto.setStatus(datas.get(i).getStatus());
            productDto.setTitle(datas.get(i).getTitle());
            productDto.setType(datas.get(i).getType());
            productDto.setUser_id(datas.get(i).getUser_id());
            list.add(productDto);
        }
        int countx = productServive.queryAllCount("电子");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",countx);
        jsonObject.put("data",list);
        System.out.println( jsonObject.toString());
        return  jsonObject.toJSONString();
    }

    @ResponseBody
    @RequestMapping("/showData")
    public String methodshow(
            @RequestParam(required = false,defaultValue ="1" ) int page,
            @RequestParam(required = false,defaultValue ="15") int limit
    ){
        List<ProductVo> datas=productServive.queryProductByStyle(page,limit,"生活");
        ArrayList<ProductDto> list= new ArrayList<ProductDto>();
        for(int i=0;i<datas.size();i++){
            ProductDto productDto=new ProductDto();
            productDto.setId(datas.get(i).getId());
            productDto.setCreatetime(datas.get(i).getCreatetime());
            productDto.setDescription(datas.get(i).getDescription());
            Base64 encoder = new Base64();
            String stringBase64 =(datas.get(i).getImage() != null ? encoder.encodeToString(datas.get(i).getImage()) : "");
            productDto.setImage(stringBase64);
            productDto.setPrice(datas.get(i).getPrice());
            productDto.setStatus(datas.get(i).getStatus());
            productDto.setTitle(datas.get(i).getTitle());
            productDto.setType(datas.get(i).getType());
            productDto.setUser_id(datas.get(i).getUser_id());
            list.add(productDto);
        }
        int countx = productServive.queryAllCount("生活");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",countx);
        jsonObject.put("data",list);
        System.out.println( jsonObject.toString());
        return  jsonObject.toJSONString();

    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(ModelAndView modelAndView, @Valid Admin admin, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            modelAndView.addObject("error",bindingResult.getFieldError().getDefaultMessage());
            modelAndView.setViewName("login");
            return modelAndView;
        }

        String userName = admin.getName();
        String password = admin.getPassword();
        Admin admin1=adminService.findByName(userName);
        if(admin1==null){
            modelAndView.addObject("error","无此用户！");
            modelAndView.setViewName("login");
            return modelAndView;
        }
        if(!admin1.getPassword().equals(password)){
            modelAndView.addObject("error","密码错误！");
            modelAndView.setViewName("login");
            return modelAndView;
        }
        modelAndView.addObject("userName",userName);
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
