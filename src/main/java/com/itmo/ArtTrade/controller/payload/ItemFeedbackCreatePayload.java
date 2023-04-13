package com.itmo.ArtTrade.controller.payload;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ItemFeedbackCreatePayload {
    @NotNull
    @Max(value = 5)
    @Min(value = 1)
    private int rating;
    @NotNull
    private Long userId;
    @NotNull
    private Long itemId;
    @NotEmpty
    private String text;
}
