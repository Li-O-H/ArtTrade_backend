package com.itmo.ArtTrade.controller.payload;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
public class ItemBidCreatePayload {
    @NotEmpty
    @Positive
    @Digits(integer = 10, fraction = 2)
    private Float price;
    @NotEmpty
    private Long userId;
    @NotEmpty
    private Long itemId;
}
