package com.zz.secondhand.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.mapper.HomeProductMapper;
import com.zz.secondhand.vo.ProductVo;
import com.zz.secondhand.vo.dto.ProductDto;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2019/4/289:44
 */
@Service
public class HomeProductService {
    @Autowired
    HomeProductMapper homeProductMapper;

    /**
     *
     * @param page
     * @param limit
     * @return 返回推荐商品列表
     */
    public  String queryHomeProduct(int page, int limit, String title){
        page=(page-1)*limit;
      /*  homeProductMapper.queryHomeProduct(page,limit,title);*/

        List<ProductVo> datas=homeProductMapper.queryHomeProduct(page,limit,title);
        ArrayList<ProductDto> list= new ArrayList<ProductDto>();
        for(int i=0;i<datas.size();i++){
            ProductDto productDto=new ProductDto();
            productDto.setId(datas.get(i).getId());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            productDto.setCreatetime(formatter.format(datas.get(i).getCreatetime()));
            productDto.setDescription(datas.get(i).getDescription());
            Base64 encoder = new Base64();
            String stringBase64 =(datas.get(i).getImage() != null ? encoder.encodeToString(datas.get(i).getImage()) : "");
            productDto.setImage(stringBase64);
            productDto.setPrice(datas.get(i).getPrice());
            productDto.setStatus(datas.get(i).getStatus());
            productDto.setTitle(datas.get(i).getTitle());
            productDto.setType(datas.get(i).getType());
            productDto.setStyle(datas.get(i).getStyle());
            productDto.setUser_id(datas.get(i).getUser_id());
            list.add(productDto);
        }
        int countx = homeProductMapper.queryCount();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",countx);
        jsonObject.put("data",list);

        return jsonObject.toJSONString();
    }

    /**
     *
     * @param id
     * @return 取消商品的推荐
     */
    public String deleteProduct(Integer id){
        homeProductMapper.deleteProduct(id);
        return "取消推荐商品";
    }

    /**
     *
     * @param productDto
     * @return  推荐商品
     * @throws ParseException
     */
    public String recommendPro(ProductDto productDto) throws ParseException {
        /*Product product=new Product();
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        product.setCreatetime(formatter.parse(productDto.getCreatetime()));
        product.setDescription(productDto.getDescription());
        Base64 encoder = new Base64();
        byte[] stringBase64 = encoder.decode(productDto.getImage());
        product.setImage(stringBase64);
        product.setPrice(productDto.getPrice());
        product.setStatus(productDto.getStatus());
        product.setTitle(productDto.getTitle());
        product.setType(productDto.getType());
        product.setStyle(productDto.getStyle());
        User user=new User();
        user.setId(productDto.getUser_id());
        product.setUser(user);*/
        if (homeProductMapper.findproductbyproductid(productDto.getId())==1){
            return "0";
        }else {
            homeProductMapper.createProduct(productDto.getId());
            return "1";
        }

    }

    /**
     *
     * @return 推荐商品
     */
    public String querryHomeproduct(){
        ArrayList<Product> list =homeProductMapper.queryHomeProduct2();
      for(Product s:list){
          System.out.println(s.toString());
      }
        return JSON.toJSONString(list);
    }
}
