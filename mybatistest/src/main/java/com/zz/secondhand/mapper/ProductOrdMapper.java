package com.zz.secondhand.mapper;

import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.ProductOrd;
import com.zz.secondhand.entity.SellerOrd;
import com.zz.secondhand.vo.SellerOrdVo;

import java.util.ArrayList;

/**
 * @author Administrator
 * @title: ProductOrdMapper
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/1713:57
 */
public interface ProductOrdMapper {
    int createProductOrd(ProductOrd productOrd);
    ProductOrd findProductOrdById(Integer id);
    ArrayList<ProductOrd> findProductOrdByUserId(int user_id);
    int updateProductOrdBynumber(String ordnumber,String status );
    int updateBuyerOrd(SellerOrdVo sellerOrdVo);
    ArrayList<ProductOrd> querySellerOrd();
    int queryAllCount();
    int deletebuyerord(Integer id);

}
