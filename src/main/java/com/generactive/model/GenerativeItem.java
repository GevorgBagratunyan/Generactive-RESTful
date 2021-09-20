package com.generactive.model;


import com.generactive.model.enums.Complexity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Entity(name = "generative_item")
public class GenerativeItem extends Item {

    @Column(name = "complexity")
    @Enumerated(EnumType.STRING)
    private Complexity complexity;

    public GenerativeItem() {
    }

    public GenerativeItem(Complexity complexity) {
        this.complexity = complexity;
    }

    public Complexity getComplexity() {
        return complexity;
    }

    public void setComplexity(Complexity complexity) {
        this.complexity = complexity;
    }

    @Override
    public double calculatePrice(Configuration configuration) {
        double price = this.getBasePrice();
        double resolutionCoefficient = configuration.getResolution().getResolutionCoefficient();
        double comp = complexity.getValue();
        return price * resolutionCoefficient * comp;
    }

    @Override
    public String toString() {
        return "Generative Item : " + super.toString().substring(5);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenerativeItem item = (GenerativeItem) o;
        return this.getId() == item.getId() &&
                Double.compare(item.getBasePrice(), this.getBasePrice()) == 0 &&
                Objects.equals(this.getName(), item.getName()) &&
                Objects.equals(this.getUrl(), item.getUrl()) &&
                Objects.equals(this.getGroup().getName(), item.getGroup().getName());
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(complexity);
    }
}
