package com.zz.secondhand.controller;

import com.alibaba.fastjson.JSON;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

@Controller
@ResponseBody
@RequestMapping("/Secondhand")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/register")
    public void  register(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        int insertCount=0;
        ObjectInputStream obj;
        ServletInputStream in=request.getInputStream();
        obj=new ObjectInputStream(in);
        User user = null;
        try {

        user = (User)obj.readObject();
        insertCount =userService.Register(user);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            obj.close();
        }
        response.setContentType("application/x-java-serialized-object");
//        System.out.println(user.getName());
        OutputStream out=response.getOutputStream();
        ObjectOutputStream outObj=new ObjectOutputStream(out);
        if(insertCount==1)
        {
            outObj.writeObject("True");
        }else{
            outObj.writeObject("False");
        }
        outObj.flush();
        outObj.close();
//        System.out.println(user.toString());
    }

    @RequestMapping("getUser")
    public String GetUser(@RequestParam("name")String name , @RequestParam("pass") String pass){
        String responsepass = userService.Sel(name);
        String back=null;
        User user = null;
        System.out.printf(pass);
        if(responsepass.equals(pass))
        {
             user = userService.FindUserByName(name);
        }

        else {
            back="Flase";
        }

        return JSON.toJSONString(user);
    }
    @RequestMapping("/update")
    public String update(@RequestParam("user") String user){
        User user1= JSON.parseObject(user,User.class);
       return userService.Update(user1);
    }

}
