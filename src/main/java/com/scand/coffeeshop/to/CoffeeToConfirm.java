package com.scand.coffeeshop.to;

public class CoffeeToConfirm {

    private String coffeeId;

    private String coffeeName;

    private String coffeePrice;

    private String coffeeDescription;

    private String totalQuantity;

    public CoffeeToConfirm(String coffeeId, String coffeeName, String coffeePrice, String coffeeDescription, String totalQuantity) {

        this.coffeeId = coffeeId;
        this.coffeeName = coffeeName;
        this.coffeePrice = coffeePrice;
        this.coffeeDescription = coffeeDescription;
        this.totalQuantity = totalQuantity;
    }

    public String getCoffeeId() {

        return coffeeId;
    }

    public void setCoffeeId(String coffeeId) {

        this.coffeeId = coffeeId;
    }

    public String getCoffeeName() {

        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {

        this.coffeeName = coffeeName;
    }

    public String getCoffeePrice() {

        return coffeePrice;
    }

    public void setCoffeePrice(String coffeePrice) {

        this.coffeePrice = coffeePrice;
    }

    public String getCoffeeDescription() {

        return coffeeDescription;
    }

    public void setCoffeeDescription(String coffeeDescription) {

        this.coffeeDescription = coffeeDescription;
    }

    public String getTotalQuantity() {

        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {

        this.totalQuantity = totalQuantity;
    }
}
