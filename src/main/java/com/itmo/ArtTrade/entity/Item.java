package com.itmo.ArtTrade.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Accessors(chain = true)
public class Item extends BaseEntity{

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemPhoto> photos;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemFeedback> feedbacks;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemBid> bids;

    @ManyToMany(mappedBy = "favoriteItems")
    private List<User> favoriteOf = Collections.emptyList();

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Status status;
}
