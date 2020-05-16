package com.g09.webshopspringboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CurrentSession {


    private String id = "unknown";
    private User user = null;
    private List<OrderInfo> cart = null;


    public CurrentSession(User user) {
        this.id = UUID.randomUUID().toString();
        this.cart = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
