package com.g09.webshopspringboot.controller;

import com.g09.webshopspringboot.domain.CurrentSession;
import com.g09.webshopspringboot.domain.LoginResponse;
import com.g09.webshopspringboot.domain.LogoutResponse;
import com.g09.webshopspringboot.domain.User;
import com.g09.webshopspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @PostMapping
    public void addUserToDB(User user){
        userService.addUser(user);
    }

    @CrossOrigin
    @GetMapping("login")
    public LoginResponse verifyUser(@RequestParam String username, @RequestParam String password){
        return userService.verifyUser(username,password);
    }

    @CrossOrigin
    @GetMapping
    public List<User> getAllUsers(){
        return userService.selectAllUsers();
    }

    @GetMapping("customers")
    public List<User> getAllCustomers(){
        return userService.selectAllCustomers();
    }

    @GetMapping("logout")
    public LogoutResponse logOutUser(HttpServletRequest request) {
        return userService.logOut(request.getSession());
    }

    @GetMapping("test")
    public CurrentSession getValuesFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CurrentSession currentSession = (CurrentSession) session.getAttribute("scopedTarget.currentSession");
        return currentSession;
    }

    @GetMapping("/confirmation")
    public String orderConfirmation(@RequestBody CurrentSession currentSession){

        if(userService.addOrder(currentSession)){
            return "confirmation";
        }

        return "Shop";
    }
}
