package com.zz.secondhand.service;

import com.zz.secondhand.entity.ProductOrd;
import com.zz.secondhand.mapper.ProductMapper;
import com.zz.secondhand.mapper.ProductOrdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * @author Administrator
 * @title: ProductOrdService
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/1714:03
 */
@Resource
@Service
public class ProductOrdService {
    @Autowired
    ProductOrdMapper productOrdMapper;
    @Autowired
    ProductMapper productMapper;
    @Transactional(rollbackOn = Exception.class)
    public int createProductOrd(ProductOrd productOrd){
        int i=productOrdMapper.createProductOrd(productOrd);
        productMapper.updateProductstatus("已出售",productOrd.getProduct().getId());
        return i;
    }
    public ArrayList<ProductOrd> findProductOrdByUserId(int user_id)
    {
        return productOrdMapper.findProductOrdByUserId(user_id);
    }
}
