package com.g09.webshopspringboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
public class User {

    private Role role;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<OrderPurchase> orders = new ArrayList<>();

    public User(String userName, String firstName, String lastName, String password, Role role) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFormattedTotalSpent() {
        int sum = getTotalSpent();
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("sv","SE"));
        String formmated = nf.format(sum);
        return formmated.substring(0,formmated.lastIndexOf(",")) + " SEK";
    }

    public int getTotalSpent() {
        return orders.parallelStream().mapToInt(order -> order.getOrderTotal()).sum();
    }

    public List<OrderPurchase> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderPurchase> orders) {
        this.orders = orders;
    }
}