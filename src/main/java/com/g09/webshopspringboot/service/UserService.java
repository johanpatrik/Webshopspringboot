package com.g09.webshopspringboot.service;

import com.g09.webshopspringboot.domain.*;
import com.g09.webshopspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private CurrentSession currentSession;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
            currentSession = new CurrentSession(user);
            System.out.println(currentSession.getId());
            return new LoginResponse(true, user);
        } else {
            return new LoginResponse(false,null);
        }

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

    public void setCurrentSession(CurrentSession currentSession) {
        this.currentSession = currentSession;
    }
}
