package com.generactive.model;


import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Objects;

@Entity()
@Table(indexes = @Index(name = "item_multi_index", columnList = "name ASC, url"))
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    @SequenceGenerator(name = "item_seq", sequenceName = "item_sequence", allocationSize = 1)
    private long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "base_price")
    private double basePrice = 0.0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    public Item() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void printContent() {
        System.out.println("    Item group id: " + this.group.getId());
        System.out.println("    Item name : " + this.name);
        System.out.println("    Item price : " + this.basePrice);
        System.out.println("    Item ID : " + this.id);
    }

    public double calculatePrice(Configuration configuration) {
        double price = this.getBasePrice();
        double resolutionCoefficient = configuration.getResolution().getResolutionCoefficient();
        return price * resolutionCoefficient;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", basePrice=" + basePrice +
                ", group=" + group.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                Double.compare(item.basePrice, basePrice) == 0 &&
                Objects.equals(name, item.name) &&
                Objects.equals(url, item.url) &&
                Objects.equals(group.getName(), item.group.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url, basePrice, group.getName());
    }
}
