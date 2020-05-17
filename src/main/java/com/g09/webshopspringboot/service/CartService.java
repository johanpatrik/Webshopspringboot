package com.g09.webshopspringboot.service;

import com.g09.webshopspringboot.domain.CartItem;
import com.g09.webshopspringboot.domain.CurrentSession;
import com.g09.webshopspringboot.domain.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private CurrentSession currentSession;

    @Autowired
    public CartService(CurrentSession currentSession) {
        this.currentSession = currentSession;
    }

    public boolean addItemToCart(CartItem cartItem) {
        return currentSession.getCart().add(cartItem);
    }

    public boolean removeCartItem(CartItem cartItem) {
        return currentSession.getCart().remove(cartItem);
    }
}
