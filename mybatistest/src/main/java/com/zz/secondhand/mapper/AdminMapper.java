package com.zz.secondhand.mapper;

import com.zz.secondhand.entity.Admin;

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
}
