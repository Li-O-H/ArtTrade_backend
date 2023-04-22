package com.itmo.ArtTrade.controller.payload;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(max = 1000)
    @NotNull
    private String text;
}
