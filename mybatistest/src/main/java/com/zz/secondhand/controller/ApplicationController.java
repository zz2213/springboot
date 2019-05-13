package com.zz.secondhand.controller;

import com.alibaba.fastjson.JSONObject;
import com.zz.secondhand.entity.Admin;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.service.AdminService;
import com.zz.secondhand.service.HomeProductService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
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
    @Autowired
    HomeProductService homeProductService;

    @ResponseBody
    @RequestMapping(value = "/getadmin" ,produces = {"application/json;charset=UTF-8"})
    public String getadmin( @RequestParam(required = false,defaultValue ="1" ) int page,
                            @RequestParam(required = false,defaultValue ="15") int limit){
        List<Admin> list=adminService.queeryalladmin(page,limit);
        int countx = adminService.queryAllCount();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",countx);
        jsonObject.put("data",list);
        return  jsonObject.toJSONString();
    }

    @RequestMapping(value = "/test1",produces = {"application/json;charset=UTF-8"})
    public String test(@RequestBody ProductDto productDto){
        Product product = ProductUtils.dtoToProduct(productDto);
        productServive.updateProduct(product);
        return "product";
    }

    @RequestMapping(value = "/test2",produces = {"application/json;charset=UTF-8"})
    public String test2(@RequestBody ProductDto productDto){
        productServive.deleteProduct(productDto.getId());
        return "product";
    }

    @RequestMapping(value = "/adminupdate",produces = {"application/json;charset=UTF-8"})
    public String adminupdate(){
        return "adminupdate";
    }

    @RequestMapping(value = "/adminadd",produces = {"application/json;charset=UTF-8"})
    public String adminadd(){
        return "adminadd";
    }

    @RequestMapping(value = "/userdelete",produces = {"application/json;charset=UTF-8"})
    public String userdelete(@RequestBody User user){
        userService.deleteUser(user.getId());
        return "product";
    }

    @ResponseBody
    @RequestMapping(value = "/updateadmin",produces = {"application/json;charset=UTF-8"})
    public String updateadmin(@RequestBody Admin admin){
        adminService.Update(admin);
        return "ok";
    }

    @ResponseBody
    @RequestMapping(value = "/addadmin",produces = {"application/json;charset=UTF-8"})
    public String addadmin(@RequestBody Admin admin){
        adminService.insert(admin);
        return "ok";
    }

    @RequestMapping(value = "/updateuser",produces = {"application/json;charset=UTF-8"})
    public String updateuser(@RequestBody User user){
        userService.Update(user);
        return "product";
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

    @ResponseBody
    @RequestMapping(value = "/showDataStudy",produces = {"application/json;charset=UTF-8"})
    public String studyshow(
            @RequestParam(required = false,defaultValue ="1" ) int page,
            @RequestParam(required = false,defaultValue ="15") int limit,
            @RequestParam(required = false) Integer id
    ){
        List<ProductVo> datas=productServive.queryProductByStyle(page,limit,"学习",id);
        ArrayList<ProductDto> list= new ArrayList<ProductDto>();
        for (ProductVo data : datas) {
            ProductDto productDto = new ProductDto();
            productDto.setId(data.getId());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            productDto.setCreatetime(formatter.format(data.getCreatetime()));
            productDto.setDescription(data.getDescription());
            Base64 encoder = new Base64();
            String stringBase64 = (data.getImage() != null ? encoder.encodeToString(data.getImage()) : "");
            productDto.setImage(stringBase64);
            productDto.setPrice(data.getPrice());
            productDto.setStatus(data.getStatus());
            productDto.setTitle(data.getTitle());
            productDto.setType(data.getType());
            productDto.setStyle(data.getStyle());
            productDto.setUser_id(data.getUser_id());
            list.add(productDto);
        }
        int countx = productServive.queryAllCount("学习");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",countx);
        jsonObject.put("data",list);
        return  jsonObject.toJSONString();
    }

    @RequestMapping(value = "/elctronicproduct",produces = {"application/json;charset=UTF-8"})
    public String electronic(){
        return "elctronicproduct";
    }
    @ResponseBody
    @RequestMapping(value = "/showDataEle",produces = {"application/json;charset=UTF-8"})
    public String elctronicshow(
            @RequestParam(required = false,defaultValue ="1" ) int page,
            @RequestParam(required = false,defaultValue ="15") int limit,
              @RequestParam(required = false) Integer id
    ){
        List<ProductVo> datas=productServive.queryProductByStyle(page,limit,"电子",id);
        ArrayList<ProductDto> list= new ArrayList<ProductDto>();
        for (ProductVo data : datas) {
            ProductDto productDto = new ProductDto();
            productDto.setId(data.getId());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            productDto.setCreatetime(formatter.format(data.getCreatetime()));
            productDto.setDescription(data.getDescription());
            Base64 encoder = new Base64();
            String stringBase64 = (data.getImage() != null ? encoder.encodeToString(data.getImage()) : "");
            productDto.setImage(stringBase64);
            productDto.setPrice(data.getPrice());
            productDto.setStatus(data.getStatus());
            productDto.setTitle(data.getTitle());
            productDto.setType(data.getType());
            productDto.setStyle(data.getStyle());
            productDto.setUser_id(data.getUser_id());
            list.add(productDto);
        }
        int countx = productServive.queryAllCount("电子");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",countx);
        jsonObject.put("data",list);
        return  jsonObject.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/showData",produces = {"application/json;charset=UTF-8"})
    public String methodshow(
            @RequestParam(required = false,defaultValue ="1" ) int page,
            @RequestParam(required = false,defaultValue ="15") int limit,
            @RequestParam(required = false) Integer id
    ){
        System.out.println(id);
        List<ProductVo> datas=productServive.queryProductByStyle(page,limit,"生活",id);
        ArrayList<ProductDto> list= new ArrayList<ProductDto>();
        for (ProductVo data : datas) {
            ProductDto productDto = new ProductDto();
            productDto.setId(data.getId());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            productDto.setCreatetime(formatter.format(data.getCreatetime()));
            productDto.setDescription(data.getDescription());
            Base64 encoder = new Base64();
            String stringBase64 = (data.getImage() != null ? encoder.encodeToString(data.getImage()) : "");
            productDto.setImage(stringBase64);
            productDto.setPrice(data.getPrice());
            productDto.setStatus(data.getStatus());
            productDto.setTitle(data.getTitle());
            productDto.setType(data.getType());
            productDto.setStyle(data.getStyle());
            productDto.setUser_id(data.getUser_id());
            list.add(productDto);
        }
        int countx = productServive.queryAllCount("生活");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",countx);
        jsonObject.put("data",list);;
        return  jsonObject.toJSONString();

    }

    @GetMapping(value = "/login",produces = {"application/json;charset=UTF-8"})
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping(value = "/login",produces = {"application/json;charset=UTF-8"})
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
    @RequestMapping(value = "/getuser",produces = {"application/json;charset=UTF-8"})
    public String getuser(
            @RequestParam(required = false,defaultValue ="1" ) int page,
            @RequestParam(required = false,defaultValue ="15") int limit
    ){
        List<User> datas=userService.queryUser(page,limit);
        ArrayList<UserVO> list= new ArrayList<UserVO>();
        for (User data : datas) {
            UserVO userVO = new UserVO();
            userVO.setId(data.getId());
            Base64 encoder = new Base64();
            String stringBase64 = (data.getImage() != null ? encoder.encodeToString(data.getImage()) : "");
            userVO.setImage(stringBase64);
            userVO.setName(data.getName());
            userVO.setRealname(data.getRealname());
            userVO.setPassword(data.getPassword());
            userVO.setNumber(data.getNumber());
            userVO.setSchool(data.getSchool());
            userVO.setQq(data.getQq());
            list.add(userVO);
        }
        int countx = userService.queryAllCount();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",countx);
        jsonObject.put("data",list);
        return  jsonObject.toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/recommend")
    public String recommend(@RequestBody ProductDto productDto ) throws ParseException {
        return homeProductService.recommendPro(productDto);
    }
    @ResponseBody
    @RequestMapping(value = "/listhomeproduct",produces = {"application/json;charset=UTF-8"})
    public String listHomeProduct(@RequestParam(required = false,defaultValue ="1" ) int page,
                                  @RequestParam(required = false,defaultValue ="15") int limit){
        return homeProductService.queryHomeProduct(page,limit);
    }
    @ResponseBody
    @RequestMapping("/cancelrecommend")
    public String cancelRecommend(@RequestBody ProductDto productDto){
        return homeProductService.deleteProduct(productDto.getId());
    }

}
