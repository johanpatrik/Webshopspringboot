package com.g09.webshopspringboot.service;

import com.g09.webshopspringboot.domain.OrderPurchase;
import com.g09.webshopspringboot.repository.OrderPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class OrderService {

    private OrderPurchaseRepository orderPurchaseRepository;

    @Autowired
    public OrderService(OrderPurchaseRepository orderPurchaseRepository) {
        this.orderPurchaseRepository = orderPurchaseRepository;
    }

    public List<OrderPurchase> SelectOrdersByUserId(@PathVariable Long userId) {
        return orderPurchaseRepository.findAllByUserId(userId);
    }


}
