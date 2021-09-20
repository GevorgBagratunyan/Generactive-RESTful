package com.generactive.model;


import javax.persistence.Entity;
import java.util.Objects;

@Entity(name = "stock_item")
public class StockItem extends Item {

    public StockItem() {
    }

    @Override
    public String toString() {
        return "Stock Item : " + super.toString().substring(5);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockItem item = (StockItem) o;
        return this.getId() == item.getId() &&
                Double.compare(item.getBasePrice(), this.getBasePrice()) == 0 &&
                Objects.equals(this.getName(), item.getName()) &&
                Objects.equals(this.getUrl(), item.getUrl()) &&
                Objects.equals(this.getGroup().getName(), item.getGroup().getName());
    }

}
