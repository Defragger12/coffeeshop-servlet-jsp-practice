package com.scand.coffeeshop.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Coffee implements Serializable {

    private static final long serialVersionUID = 1L;

    private static Long idCounter = 100L;

    public Coffee(Long id, String name, String description, BigDecimal price) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Coffee() {

        this.id = idCounter++;
    }

    public Coffee(Long id) {

        this.id = id;
    }

    public Coffee(String name, String description, BigDecimal price) {

        this.id = idCounter++;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
    }
}