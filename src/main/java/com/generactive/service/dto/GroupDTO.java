package com.generactive.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class GroupDTO implements DTO{

    private Long id;

    @Size(min = 3)
    @NotBlank
    private String name;

    @Size(min = 3)
    private String parent;

    private List<GroupDTO> subGroups;

    private List<ItemDTO> items;

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

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<GroupDTO> getSubGroups() {
        return subGroups;
    }

    public void setSubGroups(List<GroupDTO> subGroups) {
        this.subGroups = subGroups;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDTO groupDTO = (GroupDTO) o;
        return Objects.equals(id, groupDTO.id) && Objects.equals(name, groupDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "GroupDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                ", subGroups=" + subGroups +
                ", items=" + items +
                '}';
    }
}
