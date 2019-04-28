package com.zz.secondhand.mapper;

import com.zz.secondhand.entity.Product;
import com.zz.secondhand.vo.ProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * @author Administrator
 * @title: HomeProductMapper
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/289:40
 */
public interface HomeProductMapper {
    int createProduct(Integer id);
    ArrayList<ProductVo> queryHomeProduct(@Param("page") int page, @Param("limit") int limit);
    ArrayList<Product> queryHomeProduct2();
    int queryCount();
    int findproductbyproductid(@Param("id") Integer id);
    int deleteProduct(Integer id);
}
