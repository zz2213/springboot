package com.zz.secondhand.utils;

import java.net.URL;

/**
 * @author Administrator
 * @title: GlobalVariables
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/1515:43
 */
public class GlobalVariables {
    final static public String MO_URL="http://192.168.31.114:8080";
    final static public String REGISTER_URL = MO_URL+"//Secondhand/register";
    final static public String LOGIN_URL=MO_URL+"/Secondhand/getUser";
    final static public String CREATE_PRODUCT_URL=MO_URL+"/ProductController/index";
    final static public String FIND_PRODUCT_TYPE =MO_URL+"/ProductController/findproductype";
    final static public String FIND_PRODUCT_STYLE=MO_URL+"/ProductController/findproductstyle";
    final static public String CREATE_PRODUCTORD=MO_URL+"/ProductOrdController/index";
    final static public String SELECT_PRODUCTORD=MO_URL+"/ProductOrdController/myorder";
    final static public String UPDATE_PRODUCT=MO_URL+"/ProductController/updateProductstatus";
    final static public String FIND_SELLER_ORDER=MO_URL+"/SellerOrdController/findsellordbyid";
    final static public String UPDATE_ORDER=MO_URL+"/ProductOrdController/updateorder";
    final static  public String UPDATE_USER=MO_URL+"//Secondhand/update";

}
