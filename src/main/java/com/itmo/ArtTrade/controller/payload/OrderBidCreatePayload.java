package com.itmo.ArtTrade.controller.payload;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class OrderBidCreatePayload {
    @NotNull
    @Positive
    @Digits(integer = 10, fraction = 2)
    private Float price;
    @NotNull
    private Long userId;
    @NotNull
    private Long orderId;
}
