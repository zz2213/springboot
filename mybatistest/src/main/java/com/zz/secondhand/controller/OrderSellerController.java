package com.zz.secondhand.controller;

import com.alibaba.fastjson.JSON;
import com.zz.secondhand.service.SellerOrdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Administrator
 * @title: OrderSellerController
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/1810:21
 */
@Controller
@ResponseBody
@RequestMapping("/SellerOrdController")
public class OrderSellerController {
    @Autowired
    SellerOrdService sellerOrdService;
    @RequestMapping(value = "/findsellordbyid",produces = {"application/json;charset=UTF-8"})
    public String findByid(@RequestParam("user_id") String user_id){
        return JSON.toJSONString(sellerOrdService.findSellerOrdByUserId(user_id));
    }
   /* @RequestMapping(value = "/getseller",produces = {"application/json;charset=UTF-8"})
    public String querrysellerord(@RequestParam(required = false,defaultValue ="1" ) int page,
                                  @RequestParam(required = false,defaultValue ="15") int limit,
                                  @RequestParam(required = false) String ordernember){
       List<SellerOrd> list = sellerOrdService.querySellerOrd(page, limit, ordernember);
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
        int countx = sellerOrdService.queryAllCount();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",countx);
        jsonObject.put("data",sellerOrdVos);
        return  jsonObject.toJSONString();
    }*/
   /* @RequestMapping(value = "/updateseller",produces = {"application/json;charset=UTF-8"})
    public String updateserller(@RequestBody SellerOrdVo sellerOrdVo){
        Product product =new Product();
        product.setId(sellerOrdVo.getProduct_id());
        product.setUser(new User());
        product.setTitle(sellerOrdVo.getProduct_title());
        product.setPrice(sellerOrdVo.getProduct_price());
        sellerOrdService.updateSellerOrd(sellerOrdVo,product);
        return "ok";
    }
    @RequestMapping("/deleteseller")
    public String deletesellerord(@RequestBody SellerOrdVo sellerOrdVo){
        sellerOrdService.deletesellerord(sellerOrdVo);
        return "ok";
    }*/
}
