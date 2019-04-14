package com.zz.secondhand.controller;

import com.zz.secondhand.entity.User;
import com.zz.secondhand.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/testBoot")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/add")
    @PostMapping
    public User register(User user){
//        Integer ID =Integer.getInteger(id);
//        user.setId(ID);
        System.out.println(user.toString());
        return user;
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
