package com.ironhack.homework_2.classes;

import com.ironhack.homework_2.enums.*;

import java.util.UUID;


public class Opportunity {
    private UUID id;
    private Product product;
    private int quantity;
    private Contact decisionMaker;
    private Status status;

    public Opportunity(Product product, int quantity, Contact decisionMaker, Status status) {
        setProduct(product);
        setQuantity(quantity);
        setDecisionMaker(decisionMaker);
        setStatus(status);
    }

    public UUID getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Contact getDecisionMaker() {
        return decisionMaker;
    }

    public void setDecisionMaker(Contact decisionMaker) {
        this.decisionMaker = decisionMaker;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Id: " + id + ", Product: " + product + ", Quantity: " + quantity + ", Decision Maker: " + decisionMaker.getName() + ", Status: " + status;
    }
}
