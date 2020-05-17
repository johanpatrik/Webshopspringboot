package com.g09.webshopspringboot.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.g09.webshopspringboot.domain.*;
import com.g09.webshopspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private CurrentSession currentSession;


    @Autowired
    public UserService(UserRepository userRepository, CurrentSession currentSession) {
        this.userRepository = userRepository;
        this.currentSession = currentSession;
    }


    public String addUser(User user) {

        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            return "register";

        } else {
            userRepository.save(user);
            return "shop";
        }
    }

    //Kontrollerar om username och password finns i databasen?
    public LoginResponse verifyUser(String userName, String password) {
        Optional<User> optionalUser = userRepository.findByUserNameAndPassword(userName, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            currentSession.setUser(user);
            return new LoginResponse(true, user);
        } else {
            return new LoginResponse(false,null);
        }
    }

    public LogoutResponse logOut(HttpSession session) {
        String name = currentSession.getUser().getFirstName() + " " + currentSession.getUser().getLastName();
        session.invalidate();
        return new LogoutResponse(name + " has been logged out.");
    }

    public List<User> selectAllCustomers() {
        return userRepository.findAllByRoleIsNot(Role.ADMIN);
    }

    public List<User> selectAllUsers() {
        return userRepository.findAll();
    }

/*     public boolean addOrder(CurrentUser currentUser) {

        int orderTotal = currentUser.getUser().getCart().calculateOrderTotal();
        OrderPurchase order = new OrderPurchase(currentUser.getUser(), currentUser.getCart(),LocalDate.now(), orderTotal);
        for (OrderInfo oi: currentUser.getCart()) {
            oi.setOrder(order);
        }
        order.setItems(itemList);
        currentUser.getUser().getOrders().add(order);
        order.setUser(user);
        orderPurchaseRepository.save(order);
    } */


    public CurrentSession getCurrentSession() {
        return currentSession;
    }
}
