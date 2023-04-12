package com.itmo.ArtTrade.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created", columnDefinition = "timestamp with time zone", nullable = false)
    private OffsetDateTime created;

    @PrePersist
    protected void onCreate() {
        created = OffsetDateTime.now();
    }
}