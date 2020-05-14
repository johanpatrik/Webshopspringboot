package com.g09.webshopspringboot.service;

import com.g09.webshopspringboot.domain.OrderInfo;
import com.g09.webshopspringboot.domain.OrderPurchase;
import com.g09.webshopspringboot.domain.User;
import com.g09.webshopspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String addUser(User user) {

        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            return "register";

        }
        else {
            userRepository.save(user);
            return "shop";
        }
    }

    public String verifyUser(String userName, String password) {
        if (userRepository.findByUserNameAndPassword(userName, password).isPresent()) {
            return "shop";
        }
        else {
            return "login";
        }
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
}
