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
public class ItemPhoto extends BaseEntity{

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "photo", columnDefinition = "bytea", nullable = false)
    private byte[] photo;
}
