package com.itmo.ArtTrade.entity;

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

    @OneToMany(mappedBy = "category")
    private List<Item> items;

    @OneToMany(mappedBy = "category")
    private List<Order> orders;
}
