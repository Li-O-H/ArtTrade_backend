package com.itmo.ArtTrade.controller.payload;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ItemFeedbackCreatePayload {
    @NotEmpty
    @Max(value = 5)
    @Min(value = 1)
    private int rating;
    @NotEmpty
    private Long userId;
    @NotEmpty
    private Long itemId;
    @NotEmpty
    private String text;
}
