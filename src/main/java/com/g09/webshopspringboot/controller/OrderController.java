package com.g09.webshopspringboot.controller;

import com.g09.webshopspringboot.domain.OrderPurchase;
import com.g09.webshopspringboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "{userId}")
    public List<OrderPurchase> getOrdersById(@PathVariable Long userId) {
        return orderService.SelectOrdersByUserId(userId);
    }
}
