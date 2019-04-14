package com.zz.secondhand.service;

import com.zz.secondhand.entity.User;
import com.zz.secondhand.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Resource
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public String Sel(@Param("id") String id){
        return userMapper.Sel(id);
    }
    public int Register(User user){
        return userMapper.Register(user);
    }

}
