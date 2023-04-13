package com.itmo.ArtTrade.controller.payload;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OrderFeedbackCreatePayload {
    @NotNull
    @Max(value = 5)
    @Min(value = 1)
    private int rating;
    @NotNull
    private Long userId;
    @NotNull
    private Long orderId;
    @NotNull
    private String text;
}
