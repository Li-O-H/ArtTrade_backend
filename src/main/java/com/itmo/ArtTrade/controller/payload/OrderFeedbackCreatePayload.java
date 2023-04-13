package com.itmo.ArtTrade.controller.payload;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class OrderFeedbackCreatePayload {
    @NotEmpty
    @Max(value = 5)
    @Min(value = 1)
    private int rating;
    @NotEmpty
    private Long userId;
    @NotEmpty
    private Long orderId;
    @NotEmpty
    private String text;
}
