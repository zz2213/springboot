package com.zz.secondhand.utils;


/**
 * @author Administrator
 * @title: GlobalVariables
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/1515:43
 */
public class GlobalVariables {
    private final static String MO_URL="http://192.168.40.116:8080";
    final static public String MODEL="106.14.162.13  192.168.40.116";
    final static public String REGISTER_URL = MO_URL+"/Secondhand/register";
    final static public String LOGIN_URL=MO_URL+"/Secondhand/getUser";
    final static public String CREATE_PRODUCT_URL=MO_URL+"/ProductController/index";
    final static public String FIND_PRODUCT_TYPE =MO_URL+"/ProductController/findproductype";
    final static public String FIND_PRODUCT_STYLE=MO_URL+"/ProductController/findproductstyle";
    final static public String CREATE_PRODUCTORD=MO_URL+"/ProductOrdController/index";
    final static public String SELECT_PRODUCTORD=MO_URL+"/ProductOrdController/myorder";
    final static public String UPDATE_PRODUCT=MO_URL+"/ProductController/updateProductstatus";
    final static public String FIND_SELLER_ORDER=MO_URL+"/SellerOrdController/findsellordbyid";
    final static public String UPDATE_ORDER=MO_URL+"/ProductOrdController/updateorder";
    final static  public String UPDATE_USER=MO_URL+"/Secondhand/update";
    final static  public String FIND_TOKEN=MO_URL+"/Token/find";
    final static public String QUERRY_HOME_PRO=MO_URL+"/HomeController/querryhomepro";
    final static public String FAILED="failed";
    final static public String SUCCESS="success";
    final static public String TOKEN_ERROR="token错误";
    final static public String TOKEN_EMP="token为空";
    final static public String ISSELLER="已出售";
    final static public String ONSELLER="在售";
}
