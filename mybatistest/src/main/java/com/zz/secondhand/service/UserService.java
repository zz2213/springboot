package com.zz.secondhand.service;

import com.alibaba.fastjson.JSON;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Resource
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public String Sel(@Param("id") String id){
        return userMapper.Sel(id);
    }
    public User FindUserByName(@Param("name") String name){
        return userMapper.findUserByName(name);
    }
    public int Register(User user) {
        User user1 = userMapper.findUserByName(user.getName());
        if(user1!=null)
        {
            return 0 ;
        }else{
            userMapper.Register(user);

            return userMapper.findUserByName(user.getName()).getId();
        }

    }
    public String Update(User user){
        String status;
        int success=userMapper.Update(user);
        if (success==1)
        {
           User returnUser = userMapper.findUserById(user.getId());
            status= JSON.toJSONString(returnUser);
        }else {
            status="false";
        }

        return status;
    }
    public User findUserById(Integer id){
        User user =userMapper.findUserById(id);
        System.out.println(user.toString());
        return user;
    }
    public List<User> queryUser( int page, int limit,String name){
        page=(page-1)*limit;
        return userMapper.queryUser(page,limit,name);
    }
    public  int queryAllCount(){
        return userMapper.queryAllCount();
    }
    public  int deleteUser(Integer id){
       return userMapper.deleteUser(id);
    }
}
