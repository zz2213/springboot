package com.zz.secondhand.mapper;

import com.zz.secondhand.entity.OrderForm;
import com.zz.secondhand.vo.dto.OrderFormDTO;
import org.apache.ibatis.annotations.Param;
import java.util.ArrayList;

/**
 * @author Administrator
 * @title: OrderFormMapper
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/5/1414:37
 */
public interface OrderFormMapper {
    int createOrdder(OrderForm orderForm);
    int updateOrderBynumber(@Param("ordnumber")String ordnumber,@Param("status")String status );
    ArrayList<OrderForm> querySellerOrd(@Param("page") int page, @Param("limit") int limit, @Param("ordernember") String ordernember);
    int queryAllCount();
    int deleteorder(Integer id);
    int updateOrder(OrderFormDTO orderFormDTO);
    ArrayList<OrderForm> queryOrderFormByUserId(int user_id);
    ArrayList<OrderForm> queryOrderFormByBusinessId(int business_id);

}
