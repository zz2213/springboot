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
    ArrayList<Product> findProductByType(@Param("type")String type ,@Param("user_id")Integer user_id);
    ArrayList<Product> findProductByStyle(@Param("style")String style , @Param("status")String status);
    int updateProductOrdBynumber(@Param("ordnumber") String ordnumber,@Param("status")String status );
    ArrayList<ProductVo> queryProductByStyle(@Param("page") int page, @Param("limit") int limit, @Param("style") String style,@Param("id") Integer id);
    int queryAllCount(String style);
    int updateProductstatus(@Param("status")String status,@Param("id")Integer id);
    int updateProduct(Product product);
    int deleteProduct(Integer id);
}
