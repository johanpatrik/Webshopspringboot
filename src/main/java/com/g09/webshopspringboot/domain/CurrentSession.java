package com.g09.webshopspringboot.domain;


import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SessionScope
@Component
public class CurrentSession {

    private String id = "unknown";
    private User user = null;
    private List<CartItem> cart = null;

    public CurrentSession() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.id = UUID.randomUUID().toString();
        this.cart = new ArrayList<>();
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    public String getId() {
        return id;
    }

    public int calculateOrderTotal(){
        int total = 0;
        for(CartItem cartItem : cart){
            total += cartItem.getQuantity()*cartItem.getRecord().getPrice();
        }
        if(user.getRole() == Role.PREMIUM){
            return (int) (total*.9);
        }
        if(user.getTotalSpent() + total >= 500_000){
            user.setRole(Role.PREMIUM);
        }
        return total;
    }
}
