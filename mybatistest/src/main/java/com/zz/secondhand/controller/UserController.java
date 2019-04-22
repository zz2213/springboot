package com.zz.secondhand.controller;

import com.alibaba.fastjson.JSON;
import com.zz.secondhand.entity.Token;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.service.TokenService;
import com.zz.secondhand.service.UserService;
import com.zz.secondhand.utils.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Date;

@Controller
@ResponseBody
@RequestMapping("/Secondhand")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    TokenService tokenService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/register")
    public void  register(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        int user_id=0;
        ObjectInputStream obj;
        ServletInputStream in=request.getInputStream();
        obj=new ObjectInputStream(in);
        User user = null;
        try {

        user = (User)obj.readObject();
        user_id =userService.Register(user);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            obj.close();
        }
        response.setContentType("application/x-java-serialized-object");
        OutputStream out=response.getOutputStream();
        ObjectOutputStream outObj=new ObjectOutputStream(out);
        if(user_id!=0)
        {
            Token token=new Token();
            token.setUserId(user_id);
            String nowDate=new Date().toString();
            token.setTokenData(user_id+nowDate);
            stringRedisTemplate.opsForValue().set(token.getUserId().toString(),token.getTokenData());
            outObj.writeObject(token);
        }else{
            outObj.writeObject(new Token());
        }
        outObj.flush();
        outObj.close();
//        System.out.println(user.toString());
    }

    @RequestMapping("getUser")
    public String GetUser(@RequestParam("name")String name , @RequestParam("pass") String pass){
        String responsepass = userService.Sel(name);
        ReturnMessage returnMessage= new ReturnMessage();
        String back=null;
        User user = null;
        System.out.printf(pass);
        if(responsepass.equals(pass))
        {    returnMessage.setMess("success");
             user = userService.FindUserByName(name);
             Token token=tokenService.updata(user);
             returnMessage.setToken(token);
             returnMessage.setUser(user);
        }

        else {
           returnMessage.setMess("用户名重复");
        }

        return JSON.toJSONString(returnMessage);
    }
    @RequestMapping("/update")
    public String update(@RequestParam("user") String user){
        User user1= JSON.parseObject(user,User.class);
       return userService.Update(user1);
    }

}
