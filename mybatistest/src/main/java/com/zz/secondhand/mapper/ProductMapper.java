package com.zz.secondhand.mapper;

import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.User;

import java.util.ArrayList;

/**
 * @author Administrator
 * @title: ProductMapper
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/1610:42
 */
public interface ProductMapper {
    int createProduct(Product product);
    ArrayList<Product> findProductByType(String type ,Integer user_id);
}
