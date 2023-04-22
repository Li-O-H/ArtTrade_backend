package com.itmo.ArtTrade.controller.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ItemCreatePayload {
    @NotEmpty
    @Size(max = 50)
    private String title;
    @NotEmpty
    @Size(max = 1000)
    private String description;
    @NotNull
    private Long userId;
    @NotNull
    private Long categoryId;
}
