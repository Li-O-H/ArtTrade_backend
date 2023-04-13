package com.itmo.ArtTrade.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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

    @OneToMany(mappedBy = "user")
    private List<UserPhoto> photos;

    @OneToMany(mappedBy = "user")
    private List<Item> items;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToMany(mappedBy = "doneBy")
    private List<Order> doneOrders;

    @OneToMany(mappedBy = "user")
    private List<ItemBid> itemBids;

    @OneToMany(mappedBy = "user")
    private List<OrderBid> orderBids;

    @OneToMany(mappedBy = "user")
    private List<ItemFeedback> itemFeedbacks;

    @OneToMany(mappedBy = "user")
    private List<OrderFeedback> orderFeedbacks;

    @ManyToMany(targetEntity = Item.class, cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"),
            name = "favorite_items")
    private List<Item> favoriteItems = Collections.emptyList();

    @ManyToMany(targetEntity = Order.class, cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            name = "favorite_orders")
    private List<Order> favoriteOrders = Collections.emptyList();
}
