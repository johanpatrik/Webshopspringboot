package com.g09.webshopspringboot.domain;

public class CartItem {

    private Record record;
    private int quantity;


    public CartItem(Record record, int quantity) {
        this.record = record;
        this.quantity = quantity;
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
}
