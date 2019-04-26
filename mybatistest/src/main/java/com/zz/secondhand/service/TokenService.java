package com.zz.secondhand.service;

import com.alibaba.fastjson.JSON;
import com.zz.secondhand.entity.Token;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.mapper.UserMapper;
import com.zz.secondhand.utils.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Administrator
 * @title: TokenService
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/2210:12
 */
@Resource
@Service
public class TokenService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
   public String find (Token token){
       ReturnMessage returnMessage=new ReturnMessage();
       String tokenresult=stringRedisTemplate.opsForValue().get(token.getUserId().toString());
       if(token.getTokenData().equals(tokenresult)){
           System.out.println(token.toString());
           User user = userService.findUserById(token.getUserId());
           returnMessage.setUser(user);
           returnMessage.setMess("success");
       }
       else {
           returnMessage.setMess("failed");

       }

          return JSON.toJSONString(returnMessage);
    }
    public Token updata(User user){
       Token token=new Token();
       token.setUserId(user.getId());
        Date date =new Date();
       token.setTokenData(token.getUserId().toString()+date);
       stringRedisTemplate.opsForValue().set(token.getUserId().toString(),token.getTokenData());
       return token;
    }
}
