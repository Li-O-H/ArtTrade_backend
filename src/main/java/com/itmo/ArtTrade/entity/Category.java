package com.itmo.ArtTrade.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
public class Category extends BaseEntity {

    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Item> items;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Order> orders;
}
