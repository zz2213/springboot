package com.zz.secondhand.service;

import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.ProductOrd;
import com.zz.secondhand.entity.SellerOrd;
import com.zz.secondhand.mapper.ProductMapper;
import com.zz.secondhand.mapper.ProductOrdMapper;
import com.zz.secondhand.mapper.SellerOrdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * @author Administrator
 * @title: SellerOrdService
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/189:03
 */
@Resource
@Service
public class SellerOrdService {
    @Autowired
    SellerOrdMapper sellerOrdMapper;
    @Autowired
    ProductServive productServive;
    @Autowired
    ProductOrdMapper productOrdMapper;
    @Autowired
    ProductMapper productMapper;

    public int createSellerOrd(SellerOrd sellerOrd){
        return sellerOrdMapper.createSellerOrd(sellerOrd);
    }
    public SellerOrd findSellerOrdByProductId(Integer product_id){
        return sellerOrdMapper.findSellerOrdByProductId(product_id);
    }
    public ArrayList<SellerOrd> findSellerOrdByUserId(String user_id){
        return sellerOrdMapper.findSellerOrdByUserId(user_id);
    }
    public int updateSellerOrdBynumber(String ordernumber,String status){


        return sellerOrdMapper.updateSellerOrdBynumber(ordernumber,status);
    }
    public ArrayList<SellerOrd> querySellerOrd(int page,int limit, String ordernember){
        page=(page-1)*limit;
        return  sellerOrdMapper.querySellerOrd(page, limit, ordernember);
    }
    public int queryAllCount(){
        return sellerOrdMapper.queryAllCount();
    }

}
