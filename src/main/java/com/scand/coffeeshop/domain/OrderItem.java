package com.scand.coffeeshop.domain;

import java.io.Serializable;

public class OrderItem implements Serializable {

    private static Long idCounter = 100L;

    private Long id;
    private Coffee coffee;
    private Order order;
    private Integer quantity;

    public OrderItem() {

        this.id = idCounter++;
    }

    public OrderItem(Coffee coffee, Order order, Integer quantity) {

        this.id = idCounter++;
        this.coffee = coffee;
        this.order = order;
        this.quantity = quantity;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Coffee getCoffee() {

        return coffee;
    }

    public void setCoffee(Coffee coffee) {

        this.coffee = coffee;
    }

    public Order getOrder() {

        return order;
    }

    public void setOrder(Order order) {

        this.order = order;
    }

    public Integer getQuantity() {

        return quantity;
    }

    public void setQuantity(Integer quantity) {

        this.quantity = quantity;
    }
}