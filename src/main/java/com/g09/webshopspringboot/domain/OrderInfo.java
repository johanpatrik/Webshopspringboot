package com.g09.webshopspringboot.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

@Entity
public class OrderInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.MERGE)
    private Record record;
    private int quantity;
    @ManyToOne()
    private OrderPurchase order;

    public OrderInfo(Record record, int quantity) {
        this.record = record;
        this.quantity = quantity;
    }

    public OrderInfo() {

    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Metoden räknar ut summan av antalet av en specifik produkt
     *
     * @return: summa
     */
    public String getTotalPriceFormatted() {
        return formatNumbers(getTotalPrice()) + " SEK";
    }

    public int getTotalPrice() {
        return this.quantity * this.record.getPrice();
    }

    public String formattedQuantity() {
        return formatNumbers(getQuantity());
    }

    private String formatNumbers(int number) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("sv", "SE"));
        String formmated = nf.format(number);
        return formmated.substring(0, formmated.lastIndexOf(","));
    }

    public OrderPurchase getOrder() {
        return order;
    }

    public void setOrder(OrderPurchase order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

}