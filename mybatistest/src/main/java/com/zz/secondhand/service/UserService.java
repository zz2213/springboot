package com.zz.secondhand.service;

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

@Resource
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public String Sel(@Param("id") String id){
        return userMapper.Sel(id);
    }
    public int Register(User user) {
        User user1 = userMapper.findUserByName(user.getName());
        if(user1!=null)
        {
            return 0 ;
        }else{
            return userMapper.Register(user);
        }

    }

}
