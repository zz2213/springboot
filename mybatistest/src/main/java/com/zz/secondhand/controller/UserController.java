package com.zz.secondhand.controller;

import com.zz.secondhand.entity.User;
import com.zz.secondhand.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
    public void  register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int insertCount=0;
        ServletInputStream in=request.getInputStream();
        User user = null;
        ObjectInputStream obj=new ObjectInputStream(in);
        try {
            user = (User)obj.readObject();
            insertCount =userService.Register(user);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            obj.close();
        }
        response.setContentType("application/x-java-serialized-object");
        System.out.println(user.getName());
        OutputStream out=response.getOutputStream();
        ObjectOutputStream outObj=new ObjectOutputStream(out);
        user.setPassword("9964646");
        outObj.writeObject(user);
        outObj.flush();
        outObj.close();
        System.out.println(user.toString());
    }

    @RequestMapping("getUser")
    public String GetUser(@RequestParam("id")String id , @RequestParam("pass") String pass){
        String responsepass =userService.Sel(id).toString();
        String back=null;
        System.out.printf(pass);
        if(responsepass.equals(pass))
            back="True";
        else {
            back="Flase";
        }

        return back ;
    }

}