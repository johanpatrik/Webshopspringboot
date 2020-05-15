package com.g09.webshopspringboot.controller;

import com.g09.webshopspringboot.domain.LoginResponse;
import com.g09.webshopspringboot.domain.Role;
import com.g09.webshopspringboot.domain.User;
import com.g09.webshopspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @PostMapping
    public void addUserToDB(User user){
        userService.addUser(user);
    }

    @CrossOrigin
    @GetMapping("username/{username}/pwd/{password}")
    public LoginResponse verifyUser(@PathVariable String username, @PathVariable String password){

        Optional<User> user = userService.verifyUser(username,password);
        if (user.isPresent()) {
            return new LoginResponse(true,user.get().getRole());
        } else {
            return new LoginResponse(false,null);
        }
    }

    @CrossOrigin
    @GetMapping
    public List<User> getAllUsers(){
        return userService.selectAllUsers();
    }

    @GetMapping("/abc/defg/hjkl/mno")
    public String orderConfirmation(@RequestBody User user){

       /* if(userService.addOrder(user)){
            return "confirmation";
        }*/

        return "Shop";
    }
}
