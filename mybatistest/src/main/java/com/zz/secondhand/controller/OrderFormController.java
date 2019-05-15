package com.zz.secondhand.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zz.secondhand.entity.OrderForm;
import com.zz.secondhand.service.OrderFormService;
import com.zz.secondhand.vo.OrderFormVo;
import com.zz.secondhand.vo.dto.OrderFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @title: OrderFormController
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/5/14 15:25
 */
@Controller
@RequestMapping(value = "/OrderForm")
public class OrderFormController {
    @Autowired
    OrderFormService orderFormService;
    @ResponseBody
    @RequestMapping(value = "/create",produces = {"application/json;charset=UTF-8"})
    public String index(@RequestParam("orderForm") String orderForm) {
        System.out.println("test   "+orderForm);
        OrderForm orderForm1= JSON.parseObject(orderForm, OrderForm.class);
        orderForm1.setOrdernember("SE"+orderForm1.getProduct().getId()+String.valueOf(orderForm1.getCreatetime().getTime()));

        return orderFormService.createOrderForm(orderForm1);
    }
    @ResponseBody
    @RequestMapping(value = "/myorder",produces = {"application/json;charset=UTF-8"})
    public String myOrder(@RequestParam("user_id") int user_id){
        return JSON.toJSONString(orderFormService.queryOrderFormByUserId(user_id));
    }
    @ResponseBody
    @RequestMapping(value = "/sellorder",produces = {"application/json;charset=UTF-8"})
    public String mySeller(@RequestParam("business_id") int business_id){
        return JSON.toJSONString(orderFormService.queryOrderFormByBusiness(business_id));
    }
    @ResponseBody
    @RequestMapping(value = "/update",produces = {"application/json;charset=UTF-8"})
    public String update(@RequestParam("number") String number,@RequestParam("status") String status){
        return JSON.toJSONString(orderFormService.update(number,status));
    }
    @ResponseBody
    @RequestMapping(value = "/getbuyer",produces = {"application/json;charset=UTF-8"})
    public String querrybuyerord(@RequestParam(required = false,defaultValue ="1" ) int page,
                                 @RequestParam(required = false,defaultValue ="15") int limit,
                                 @RequestParam(required = false) String ordernember){
        List<OrderForm> list = orderFormService.querySellerOrd(page, limit, ordernember);
        ArrayList<OrderFormVo> orderFormVos=new ArrayList<OrderFormVo>();
        for (int i =0 ; i< list.size();i++){
            OrderFormVo orderFormVo=new OrderFormVo();
            orderFormVo.setAddress(list.get(i).getAddress());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            orderFormVo.setCreatetime(formatter.format(list.get(i).getCreatetime()));
            orderFormVo.setId(list.get(i).getId());
            orderFormVo.setOrdernember(list.get(i).getOrdernember());
            orderFormVo.setStatus(list.get(i).getStatus());
            orderFormVo.setUser_id(list.get(i).getUser().getId());
            orderFormVo.setBusiness_id(list.get(i).getBusiness().getId());
            orderFormVo.setBusiness_name(list.get(i).getBusiness().getRealname());
            orderFormVo.setProduct_id(list.get(i).getProduct().getId());
            orderFormVo.setProduct_price(list.get(i).getProduct().getPrice());
            orderFormVo.setProduct_title(list.get(i).getProduct().getTitle());
            orderFormVo.setUser_name(list.get(i).getUser().getRealname());
            orderFormVo.setName(list.get(i).getName());
            orderFormVo.setPhone(list.get(i).getPhone());
            orderFormVos.add(orderFormVo);
        }
        int countx = orderFormService.queryAllCount();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",countx);
        jsonObject.put("data",orderFormVos);
        return  jsonObject.toJSONString();
    }
    @RequestMapping(value = "/updatebuyer",produces = {"application/json;charset=UTF-8"})
    public String updatebuyer(@RequestBody OrderFormDTO orderFormDTO){
        System.out.println(orderFormDTO.toString());
       orderFormService.updateOrder(orderFormDTO);
        return "buyerord";
    }

}
