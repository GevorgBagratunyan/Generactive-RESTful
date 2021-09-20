package com.generactive.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private final List<BasketItem> basketItems;

    public Basket() {
        basketItems = new ArrayList<>();
    }

    public double totalPrice() {
        double total = 0.0;
        for (BasketItem item : basketItems) {
            total += item.getPrice();
        }
        System.out.println(total);
        return total;
    }

    public void add(BasketItem item) {
        basketItems.add(item);
    }

    public boolean remove(BasketItem basketItem) {
        return basketItems.remove(basketItem);
    }

    public void printReport() {
        System.out.println("Items in basket with their prices : ");
        basketItems.stream().forEach(e -> System.out.println(e.name() + " : " + e.getPrice()));
        System.out.println("Total price of Items : " + totalPrice());
    }
}
