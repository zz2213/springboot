package com.zz.secondhand.service;

import com.zz.secondhand.entity.Product;
import com.zz.secondhand.mapper.ProductMapper;
import com.zz.secondhand.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author Administrator
 * @title: ProductServive
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/1610:41
 */
@Resource
@Service
public class ProductServive {
    @Autowired
    ProductMapper productMapper;
    public  int createProduct(Product product){
        return  productMapper.createProduct(product);
    }
   public ArrayList<Product> findProductByType(String type,Integer user_id){
        return productMapper.findProductByType(type,user_id);
   }
   public  ArrayList<Product> findProductByStyle(String style){
        return productMapper.findProductByStyle(style);
   }
    public int updateProductstatus(String status,Integer id){
        return productMapper.updateProductstatus(status,id);
    }

}
