package com.zz.secondhand.service;

import com.zz.secondhand.entity.Admin;
import com.zz.secondhand.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @title: AdminService
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/2310:21
 */
@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;
  public  Admin  findByName(String name){

      return adminMapper.findByName(name);
  }
}
