package com.zz.secondhand.mapper;

import com.zz.secondhand.entity.SellerOrd;
import com.zz.secondhand.vo.SellerOrdVo;
import org.apache.ibatis.annotations.Param;

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
    SellerOrd findSellerOrdByProductId(Integer product_id);
    ArrayList<SellerOrd> findSellerOrdByUserId(String user_id);
    int updateSellerOrdBynumber(String ordnumber,String status );
    int updateSellerOrd(SellerOrdVo sellerOrdVo);
    ArrayList<SellerOrd> querySellerOrd(@Param("page") int page, @Param("limit") int limit, @Param("ordernember") String ordernember);
    int queryAllCount();
    int deletesellerord(Integer id);
}
