package com.g09.webshopspringboot.service;

import com.g09.webshopspringboot.domain.CartItem;
import com.g09.webshopspringboot.domain.CurrentSession;
import com.g09.webshopspringboot.domain.Record;
import com.g09.webshopspringboot.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private CurrentSession currentSession;
    private RecordRepository recordRepository;

    @Autowired
    public CartService(CurrentSession currentSession, RecordRepository recordRepository) {
        this.currentSession = currentSession;
        this.recordRepository = recordRepository;
    }


    public boolean addItemToCart(Long recordId) {

        return currentSession.getCart().add(new CartItem(recordRepository.findById(recordId).get(),1));
    }

    public boolean removeCartItem(CartItem cartItem) {
        return currentSession.getCart().remove(cartItem);
    }
}
