package com.zz.secondhand.service;

import com.zz.secondhand.entity.OrderForm;
import com.zz.secondhand.entity.ProductOrd;
import com.zz.secondhand.mapper.OrderFormMapper;
import com.zz.secondhand.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * @author Administrator
 * @title: OrderFormService
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/5/1415:11
 */
@Service
public class OrderFormService {
    @Autowired
    OrderFormMapper orderFormMapper;
    @Autowired
    ProductMapper productMapper;

    @Transactional(rollbackOn = Exception.class)
    public String createOrderForm(OrderForm orderForm){
       orderForm.getBusiness().setId(orderForm.getProduct().getUser().getId());
        orderFormMapper.createOrdder(orderForm);
        productMapper.updateProductstatus("已售",orderForm.getProduct().getId());
        return "ok";
    }
    public ArrayList<OrderForm> queryOrderFormByUserId(int user_id){
       return orderFormMapper.queryOrderFormByUserId(user_id);
    }
    public ArrayList<OrderForm> queryOrderFormByBusiness(int business_id){
        return orderFormMapper.queryOrderFormByBusinessId(business_id);
    }
}
