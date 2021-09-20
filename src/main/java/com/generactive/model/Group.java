package com.generactive.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "groups")
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq")
    @SequenceGenerator(name = "group_seq", sequenceName = "group_sequence", allocationSize = 1)
    private long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "parent_group_id")
    private Group parent;


    @OneToMany(mappedBy = "parent")
    private List<Group> subGroups = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<Item> items = new ArrayList<>();

    public Group() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(Group parent) {
        this.parent = parent;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Group getParent() {
        return parent;
    }

    public void addSubGroup(Group group) {
        subGroups.add(group);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void printContent() {
        System.out.println("Group ID : " + id + "\nName : " + name);
        System.out.println("Items in this Group : ");

        if (!items.isEmpty()) {
            for (Item item : items) {
                item.printContent();
            }
        } else System.out.println();

        System.out.println();
        System.out.println("Subgroups in this Group : ");
        if (!subGroups.isEmpty()) {
            for (Group group : subGroups) {
                System.out.printf("  GROUP - id: {%d} Name: {%s}%n", group.getId(), group.getName());
                System.out.println("  Items in this group : ");
                group.printItems();
            }
        } else System.out.println();
        System.out.println("____________________");
    }

    public void printItems() {
        for (Item item : items) {
            item.printContent();
        }
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id == group.id && Objects.equals(name, group.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
