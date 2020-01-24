package com.scand.coffeeshop.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private static Long idCounter = 100L;

    private Long id;
    private List<OrderItem> items;
    private BigDecimal price;

    public Order() {
        this.id = idCounter++;
    }

    public Order(Long id) {

        this.id = id;
    }

    public Order(Long id, BigDecimal price) {

        this.id = id;
        this.price = price;
    }

    public Order(List<OrderItem> items, BigDecimal price) {

        this.id = idCounter++;
        this.items = items;
        this.price = price;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public List<OrderItem> getItems() {

        return items;
    }

    public void setItems(List<OrderItem> items) {

        this.items = items;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
    }
}