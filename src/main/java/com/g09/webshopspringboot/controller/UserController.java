package com.g09.webshopspringboot.controller;

import com.g09.webshopspringboot.domain.User;
import com.g09.webshopspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("shop")
    public void addUserToDB(User user){
        userService.addUser(user);
    }

    @GetMapping("shop")
    public void verifyUser(String username,String password){
        userService.verifyUser(username,password);
    }
}
