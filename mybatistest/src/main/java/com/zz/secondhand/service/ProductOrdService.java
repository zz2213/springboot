package com.zz.secondhand.service;

import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.ProductOrd;
import com.zz.secondhand.entity.SellerOrd;
import com.zz.secondhand.mapper.ProductMapper;
import com.zz.secondhand.mapper.ProductOrdMapper;
import com.zz.secondhand.mapper.SellerOrdMapper;
import com.zz.secondhand.vo.SellerOrdVo;
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
    ProductServive productServive;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    SellerOrdService sellerOrdService;
    @Autowired
    SellerOrdMapper sellerOrdMapper;



    @Transactional(rollbackOn = Exception.class)
    public void createProductOrd(ProductOrd productOrd, SellerOrd sellerOrd){
        productOrd.setStatus("未付款");
        int i=productOrdMapper.createProductOrd(productOrd);
        productMapper.updateProductstatus("已出售",productOrd.getProduct().getId());
    }
    public ArrayList<ProductOrd> findProductOrdByUserId(int userId)
    {
        return productOrdMapper.findProductOrdByUserId(userId);
    }
    @Transactional(rollbackOn = Exception.class)
    public void updateProductOrdByuserID(String ordernumber, String status){
        sellerOrdService.updateSellerOrdBynumber(ordernumber,status);
        productOrdMapper.updateProductOrdBynumber(ordernumber,status);
    }
    public  ArrayList<ProductOrd> querySellerOrd(){
        return productOrdMapper.querySellerOrd();
    }
    public int queryAllCount(){
        return productOrdMapper.queryAllCount();
    }
   public void updateBuyerOrd(SellerOrdVo sellerOrdVo, Product product){
       productServive.updateProduct(product);
       productOrdMapper.updateBuyerOrd(sellerOrdVo);
   }
   @Transactional(rollbackOn = Exception.class)
   public void deletebuyerord(SellerOrdVo sellerOrdVo){
    SellerOrd sellerOrd=sellerOrdMapper.findSellerOrdByProductId(sellerOrdVo.getProduct_id());
    sellerOrdMapper.updateSellerOrdBynumber(sellerOrd.getOrdernember(),"已取消");
       productMapper.updateProductstatus("在售",sellerOrdVo.getProduct_id());
       productOrdMapper.deletebuyerord(sellerOrdVo.getId());
   }
}
