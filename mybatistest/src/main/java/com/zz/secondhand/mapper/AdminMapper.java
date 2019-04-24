package com.zz.secondhand.mapper;

import com.zz.secondhand.entity.Admin;
import com.zz.secondhand.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 * @title: AdminMapper
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/2310:14
 */
public interface AdminMapper {
    int insert(Admin admin);
    Admin findByName(String name);
   List<Admin> queeryalladmin(@Param("page") int page, @Param("limit") int limit);
    int queryAllCount();
    int Update(Admin admin);
}
