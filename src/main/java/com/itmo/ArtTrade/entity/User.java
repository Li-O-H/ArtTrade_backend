package com.itmo.ArtTrade.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Accessors(chain = true)
@Table(name = "\"user\"")
public class User extends BaseEntity{

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "about_creator", length = 1000)
    private String aboutCreator;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserPhoto> photos;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Item> items;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    //todo что при удалении делать
    @JsonIgnore
    @OneToMany(mappedBy = "doneBy")
    private List<Order> doneOrders;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ItemBid> itemBids;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OrderBid> orderBids;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ItemFeedback> itemFeedbacks;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OrderFeedback> orderFeedbacks;

    @JsonIgnore
    @ManyToMany(targetEntity = Item.class, cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"),
            name = "favorite_items")
    private List<Item> favoriteItems = Collections.emptyList();

    @JsonIgnore
    @ManyToMany(targetEntity = Order.class, cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            name = "favorite_orders")
    private List<Order> favoriteOrders = Collections.emptyList();
}
