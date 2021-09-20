package com.generactive.model;


public class BasketItem {
    private final Item item;
    private final Configuration configuration;

    public BasketItem(Item item, Configuration configuration) {
        this.item = item;
        this.configuration = configuration;
    }

    public double getPrice() {
            return item.calculatePrice(configuration);
    }

    public String name() {
        return this.item.getName();
    }
}
