package com.g09.webshopspringboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.NumberFormat;
import java.util.Locale;

@Entity
@Data
@NoArgsConstructor
public class OrderInfo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.MERGE)
    private Record record;
    private int quantity;
    @JsonIgnore
    @ManyToOne()
    private OrderPurchase order;

    public OrderInfo(Record record, int quantity) {
        this.record = record;
        this.quantity = quantity;
    }

    public OrderInfo(Record record, int quantity, OrderPurchase order) {
        this.record = record;
        this.quantity = quantity;
        this.order = order;
    }

    /**
     * Metoden räknar ut summan av antalet av en specifik produkt
     *
     * @return: summa
     */
    @JsonProperty
    public String getTotalPriceFormatted() {
        return formatNumbers(getTotalPrice()) + " SEK";
    }

    public int getTotalPrice() {
        return this.quantity * this.record.getPrice();
    }

    @JsonProperty
    public String formattedQuantity() {
        return formatNumbers(getQuantity());
    }

    private String formatNumbers(int number) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("sv", "SE"));
        String formmated = nf.format(number);
        return formmated.substring(0, formmated.lastIndexOf(","));
    }
}