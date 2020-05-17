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
    private List<OrderInfo> cart = null;

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

    public List<OrderInfo> getCart() {
        return cart;
    }

    public void setCart(List<OrderInfo> cart) {
        this.cart = cart;
    }

    public String getId() {
        return id;
    }
}
