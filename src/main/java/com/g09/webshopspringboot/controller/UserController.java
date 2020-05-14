package com.g09.webshopspringboot.controller;

import com.g09.webshopspringboot.domain.User;
import com.g09.webshopspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<User> getAllUsers(){
        return userService.selectAllUsers();
    }

    @GetMapping
    public String orderConfirmation(@RequestBody User user){

        if(userService.addOrder(user)){
            return "confirmation";
        }
        return "Shop";
    }
}
