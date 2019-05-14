package com.zz.secondhand.service;

import com.zz.secondhand.entity.Admin;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.mapper.AdminMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
  public List<Admin> queeryalladmin(int page,int limit, String name){
      page=(page-1)*limit;
   return adminMapper.queeryalladmin(page,limit,name);
  }
  public int queryAllCount(){
      return adminMapper.queryAllCount();
  }
  public  int Update(Admin admin){
      return adminMapper.Update(admin);
  }
  public  int insert(Admin admin){
      return adminMapper.insert(admin);
  }
}

