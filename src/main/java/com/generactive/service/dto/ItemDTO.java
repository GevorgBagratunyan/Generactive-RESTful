package com.generactive.service.dto;


import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Objects;


public class ItemDTO implements DTO{

    @Positive
    private Long id;

    @Size(min = 3)
    private String name;

    @URL
    private String url;

    @Positive
    private Double basePrice;

    @Size(min = 3)
    private String groupName;

    private String createdBy;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDTO itemDTO = (ItemDTO) o;
        return Objects.equals(id, itemDTO.id) && Objects.equals(name, itemDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
