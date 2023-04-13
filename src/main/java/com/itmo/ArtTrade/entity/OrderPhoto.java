package com.itmo.ArtTrade.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Accessors(chain = true)
public class OrderPhoto extends BaseEntity {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "photo", columnDefinition = "bytea", nullable = false)
    private byte[] photo;

}
