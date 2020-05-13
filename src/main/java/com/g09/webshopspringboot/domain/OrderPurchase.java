package com.g09.webshopspringboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
public class OrderPurchase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "order")
    private List<OrderInfo> items = new ArrayList<>();
    @ManyToOne()
    private User user;
    private int orderTotal;

    private Date date;

    public OrderPurchase(User user, List<OrderInfo> items, LocalDate date, int orderTotal) {
        this.date = Date.valueOf(date);
        this.user = user;
        this.items = items;
        this.orderTotal = orderTotal;
    }

    public OrderPurchase() {

    }


    public Long getId() {
        return id;
    }

    public List<OrderInfo> getItems() {
        return items;
    }

    public void setItems(List<OrderInfo> items) {
        this.items = items;
    }

    public void addNewProductToOrder(Record record, int quantity) {
        items.add(new OrderInfo(record, quantity));
    }


    /**
     * Metoden kalkylerar en beställnings total summa
     *
     * @return: summan för en beställning
     */
    public String formattedOrderTotal() {

        return formatNumbers(orderTotal) + " SEK";
    }

    /**
     * Metoden räknar ut antalet produkter i en order
     *
     * @return: antalet produkter i en order
     */
    public String calculateItemsQuantity() {
        int qty = items.parallelStream().mapToInt(item -> item.getQuantity()).sum();
        return formatNumbers(qty);
    }

    private String formatNumbers(int number) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("sv", "SE"));
        String formmated = nf.format(number);
        return formmated.substring(0, formmated.lastIndexOf(","));
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getDate() {
        return date.toString();
    }

    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }

    public int getOrderTotal() {
        return orderTotal;
    }
}