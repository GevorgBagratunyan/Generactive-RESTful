package com.generactive.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq")
    @SequenceGenerator(name = "group_seq", sequenceName = "group_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToOne(targetEntity = Group.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Group parent;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "group_parent")
    private final List<Group> subGroups = new ArrayList<>();

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private final List<Item> items = new ArrayList<>();

    public Group() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(Group parent) {
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Group getParent() {
        return parent;
    }

    public List<Group> getSubGroups() {
        return subGroups;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addSubGroup(Group group) {
        subGroups.add(group);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void printContent() {
        System.out.println("Group id : " + id + "\nName : " + name);
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
                System.out.println("  Items in this groups : ");
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
        return Objects.equals(id, group.id) && Objects.equals(name, group.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
