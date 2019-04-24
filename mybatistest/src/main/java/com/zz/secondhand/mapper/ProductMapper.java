package com.zz.secondhand.mapper;

import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.vo.ProductVo;
import org.apache.ibatis.annotations.Param;

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
    ArrayList<Product> findProductByStyle(String style);
    ArrayList<ProductVo> queryProductByStyle(@Param("page") int page, @Param("limit") int limit, @Param("style") String style);
    int queryAllCount(String style);
    int updateProductstatus(String status,Integer id);
    int updateProduct(Product product);
    int deleteProduct(Integer id);
}
