package com.zz.secondhand.controller;

import com.alibaba.fastjson.JSONObject;
import com.zz.secondhand.entity.Admin;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.service.AdminService;
import com.zz.secondhand.service.ProductServive;
import com.zz.secondhand.service.UserService;
import com.zz.secondhand.utils.ProductUtils;
import com.zz.secondhand.vo.ProductVo;
import com.zz.secondhand.vo.UserVO;
import com.zz.secondhand.vo.dto.ProductDto;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
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
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/getadmin")
    public String getadmin( @RequestParam(required = false,defaultValue ="1" ) int page,
                            @RequestParam(required = false,defaultValue ="15") int limit){
        List<Admin> list=adminService.queeryalladmin(page,limit);
        int countx = adminService.queryAllCount();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",countx);
        jsonObject.put("data",list);
        System.out.println( jsonObject.toString());
        return  jsonObject.toJSONString();
    }

    @RequestMapping("/test1")
    public String test(@RequestBody ProductDto productDto){
        System.out.println(productDto.toString());
        Product product = ProductUtils.dtoToProduct(productDto);
        productServive.updateProduct(product);
        return "product";
    }
    @RequestMapping("/test2")
    public String test2(@RequestBody ProductDto productDto){
        System.out.println(productDto.getId());
        productServive.deleteProduct(productDto.getId());
        return "product";
    }
    @RequestMapping("/adminupdate")
    public String adminupdate(){
        return "adminupdate";
    }
    @RequestMapping("/adminadd")
    public String adminadd(){
        return "adminadd";
    }
    @RequestMapping("/userdelete")
    public String userdelete(@RequestBody User user){
        System.out.println(user.getId());
        userService.deleteUser(user.getId());
        return "product";
    }
    @ResponseBody
    @RequestMapping("/updateadmin")
    public String updateadmin(@RequestBody Admin admin){
        System.out.println(admin.toString());
        adminService.Update(admin);
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/addadmin")
    public String addadmin(@RequestBody Admin admin){
        System.out.println(admin.toString());
        adminService.insert(admin);
        return "ok";
    }

    @RequestMapping("/updateuser")
    public String updateuser(@RequestBody User user){
        System.out.println(user.toString());
        userService.Update(user);
        return "product";
    }


    @RequestMapping("/product")
    public String index(){
        return "product";
    }

    @RequestMapping("/studyproduct")
    public String study(){
        return "studyproduct";
    }

    @RequestMapping("/productupdate")
    public String productup(){
        return "productupdate";
    }
    @RequestMapping("/userupdate")
    public String userup(){
        return "userupdate";
    }
    @RequestMapping("/user")
    public String userrounter(){
        return "user";
    }
    @RequestMapping("/admin")
    public String adminrounter(){
        return "admin";
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
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            productDto.setCreatetime(formatter.format(datas.get(i).getCreatetime()));
            productDto.setDescription(datas.get(i).getDescription());
            Base64 encoder = new Base64();
            String stringBase64 =(datas.get(i).getImage() != null ? encoder.encodeToString(datas.get(i).getImage()) : "");
            productDto.setImage(stringBase64);
            productDto.setPrice(datas.get(i).getPrice());
            productDto.setStatus(datas.get(i).getStatus());
            productDto.setTitle(datas.get(i).getTitle());
            productDto.setType(datas.get(i).getType());
            productDto.setStyle(datas.get(i).getStyle());
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
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            productDto.setCreatetime(formatter.format(datas.get(i).getCreatetime()));
            productDto.setDescription(datas.get(i).getDescription());
            Base64 encoder = new Base64();
            String stringBase64 =(datas.get(i).getImage() != null ? encoder.encodeToString(datas.get(i).getImage()) : "");
            productDto.setImage(stringBase64);
            productDto.setPrice(datas.get(i).getPrice());
            productDto.setStatus(datas.get(i).getStatus());
            productDto.setTitle(datas.get(i).getTitle());
            productDto.setType(datas.get(i).getType());
            productDto.setStyle(datas.get(i).getStyle());
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
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            productDto.setCreatetime(formatter.format(datas.get(i).getCreatetime()));
            productDto.setDescription(datas.get(i).getDescription());
            Base64 encoder = new Base64();
            String stringBase64 =(datas.get(i).getImage() != null ? encoder.encodeToString(datas.get(i).getImage()) : "");
            productDto.setImage(stringBase64);
            productDto.setPrice(datas.get(i).getPrice());
            productDto.setStatus(datas.get(i).getStatus());
            productDto.setTitle(datas.get(i).getTitle());
            productDto.setType(datas.get(i).getType());
            productDto.setStyle(datas.get(i).getStyle());
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

    @ResponseBody
    @RequestMapping("/getuser")
    public String getuser(
            @RequestParam(required = false,defaultValue ="1" ) int page,
            @RequestParam(required = false,defaultValue ="15") int limit
    ){
        List<User> datas=userService.queryUser(page,limit);
        ArrayList<UserVO> list= new ArrayList<UserVO>();
        for(int i=0;i<datas.size();i++){
            UserVO userVO=new UserVO();
            userVO.setId(datas.get(i).getId());
            Base64 encoder = new Base64();
            String stringBase64 =(datas.get(i).getImage() != null ? encoder.encodeToString(datas.get(i).getImage()) : "");
            userVO.setImage(stringBase64);
            userVO.setName(datas.get(i).getName());
            userVO.setRealname(datas.get(i).getRealname());
            userVO.setPassword(datas.get(i).getPassword());
            userVO.setNumber(datas.get(i).getNumber());
            userVO.setSchool(datas.get(i).getSchool());
            userVO.setQq(datas.get(i).getQq());
            list.add(userVO);
        }
        int countx = userService.queryAllCount();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",countx);
        jsonObject.put("data",list);
        System.out.println( jsonObject.toString());
        return  jsonObject.toJSONString();
    }

}
