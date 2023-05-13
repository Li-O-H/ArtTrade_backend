package com.itmo.ArtTrade.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Accessors(chain = true)
@Table(name = "\"order\"")
public class Order extends BaseEntity {

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Column(name = "deadline")
    private Date deadline;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "done_by", foreignKey = @javax.persistence
            .ForeignKey(foreignKeyDefinition = """
                FOREIGN KEY (done_by)
                REFERENCES public."user" (id) MATCH SIMPLE
                ON UPDATE NO ACTION
                ON DELETE SET NULL;"""))
    private User doneBy;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderPhoto> photos;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderFeedback> feedbacks;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderBid> bids;

    @ManyToMany(mappedBy = "favoriteItems")
    private List<User> favoriteOf = Collections.emptyList();

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
}
