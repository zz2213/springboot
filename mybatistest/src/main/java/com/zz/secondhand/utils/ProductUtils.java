package com.zz.secondhand.utils;

import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.vo.dto.ProductDto;
import org.apache.tomcat.util.codec.binary.Base64;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Administrator
 * @title: ProductUtils
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/2413:33
 */
public  class ProductUtils {

    public static Product dtoToProduct(ProductDto productDto){
        Product product=new Product();
        product.setId(productDto.getId());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            product.setCreatetime(formatter.parse(productDto.getCreatetime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        product.setDescription(productDto.getDescription());
       /* Base64 encoder = new Base64();
        product.setImage(encoder.decode(productDto.getImage()));*/
        product.setPrice(productDto.getPrice());
        product.setStatus(productDto.getStatus());
        product.setTitle(productDto.getTitle());
        product.setType(productDto.getType());
        product.setStyle(productDto.getStyle());
        User user=new User();
        user.setId(productDto.getUser_id());
        product.setUser(user);
       return product;
    }
}
