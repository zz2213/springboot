package com.zz.secondhand.mapper;

import com.zz.secondhand.entity.SellerOrd;

import java.util.ArrayList;

/**
 * @author Administrator
 * @title: SellerOrdMapper
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/188:54
 */
public interface SellerOrdMapper {
    int createSellerOrd(SellerOrd sellerOrd);
    SellerOrd findSellerOrdByProductId(String product_id);
    ArrayList<SellerOrd> findSellerOrdByUserId(String user_id);
    int updateSellerOrdBynumber(String ordnumber,String status );
}
