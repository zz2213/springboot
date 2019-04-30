package com.zz.secondhand.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.ProductOrd;
import com.zz.secondhand.entity.SellerOrd;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.service.ProductOrdService;
import com.zz.secondhand.vo.SellerOrdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
    @RequestMapping(value = "/index",produces = {"application/json;charset=UTF-8"})
    public String index(@RequestParam("productOrd") String productOrd){
        ProductOrd productOrd1= JSON.parseObject(productOrd, ProductOrd.class);
        productOrd1.setOrdernember("B"+productOrd1.getProduct().getId()+String.valueOf(productOrd1.getCreatetime().getTime()));
        SellerOrd sellerOrd =new SellerOrd();
        sellerOrd.setUser(productOrd1.getUser());
        sellerOrd.setCreatetime(productOrd1.getCreatetime());
        sellerOrd.setProduct(productOrd1.getProduct());
        sellerOrd.setOrdernember("S"+productOrd1.getProduct().getId()+String.valueOf(productOrd1.getCreatetime().getTime()));
        productOrdService.createProductOrd(productOrd1,sellerOrd);
        return  "xx";
    }
    @RequestMapping(value = "/myorder",produces = {"application/json;charset=UTF-8"})
    public String selectOrder(@RequestParam("user_id") int user_id){
        return JSON.toJSONString(productOrdService.findProductOrdByUserId(user_id));
    }
    @RequestMapping(value = "/updateorder",produces = {"application/json;charset=UTF-8"})
    public  String updateOrder(@RequestParam("number") String number,@RequestParam("status") String status){
        productOrdService.updateProductOrdByuserID(number,status);
        return "xxx";
    }
    @RequestMapping(value = "/getbuyer",produces = {"application/json;charset=UTF-8"})
    public String querrybuyerord(){
        List<ProductOrd> list = productOrdService.querySellerOrd();
        ArrayList<SellerOrdVo> sellerOrdVos=new ArrayList<SellerOrdVo>();
        for (int i =0 ; i< list.size();i++){
            SellerOrdVo sellerOrdVo=new SellerOrdVo();
            sellerOrdVo.setAddress(list.get(i).getAddress());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sellerOrdVo.setCreatetime(formatter.format(list.get(i).getCreatetime()));
            sellerOrdVo.setId(list.get(i).getId());
            sellerOrdVo.setOrdernember(list.get(i).getOrdernember());
            sellerOrdVo.setStatus(list.get(i).getStatus());
            sellerOrdVo.setUser_id(list.get(i).getUser().getId());
            sellerOrdVo.setProduct_id(list.get(i).getProduct().getId());
            sellerOrdVo.setProduct_price(list.get(i).getProduct().getPrice());
            sellerOrdVo.setProduct_title(list.get(i).getProduct().getTitle());
            sellerOrdVo.setUser_name(list.get(i).getUser().getRealname());
            sellerOrdVos.add(sellerOrdVo);
        }
        int countx = productOrdService.queryAllCount();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",countx);
        jsonObject.put("data",sellerOrdVos);
        return  jsonObject.toJSONString();
    }
    @RequestMapping(value = "/updatebuyer",produces = {"application/json;charset=UTF-8"})
    public String updatebuyer(@RequestBody SellerOrdVo sellerOrdVo){
        Product product =new Product();
        product.setId(sellerOrdVo.getProduct_id());
        product.setUser(new User());
        product.setTitle(sellerOrdVo.getProduct_title());
        product.setPrice(sellerOrdVo.getProduct_price());
        productOrdService.updateBuyerOrd(sellerOrdVo,product);
        return "ok";
    }
    @RequestMapping("/deletebuyer")
    public String deletebuyerord(@RequestBody SellerOrdVo sellerOrdVo){
        productOrdService.deletebuyerord(sellerOrdVo);
        return "ok";
    }
}
