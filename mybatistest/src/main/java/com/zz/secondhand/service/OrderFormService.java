package com.zz.secondhand.service;

import com.zz.secondhand.entity.OrderForm;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.mapper.OrderFormMapper;
import com.zz.secondhand.mapper.ProductMapper;
import com.zz.secondhand.vo.dto.OrderFormDTO;
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
       orderForm.setBusiness(orderForm.getProduct().getUser());
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
    public String update(String ordernumber, String status){
        orderFormMapper.updateOrderBynumber(ordernumber,status);
        return "ok";
    }
    public  ArrayList<OrderForm> querySellerOrd(int page,int limit, String ordernember){
        page=(page-1)*limit;
        return orderFormMapper.querySellerOrd(page, limit, ordernember);
    }
    public int queryAllCount(){
        return orderFormMapper.queryAllCount();
    }
    public int updateOrder(OrderFormDTO orderFormDTO){

        Product product =new Product();
        product.setId(orderFormDTO.getProduct_id());
        product.setUser(new User());
        product.setTitle(orderFormDTO.getProduct_title());
        product.setPrice(orderFormDTO.getProduct_price());
        productMapper.updateProduct(product);
       orderFormMapper.updateOrder(orderFormDTO);
       return  1;
    }
    @Transactional(rollbackOn = Exception.class)
    public void deletebuyerord(OrderFormDTO orderFormDTO){

    }
}
