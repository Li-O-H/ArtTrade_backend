package com.itmo.ArtTrade.controller.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ItemCreatePayload {
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @NotNull
    private Long userId;
    @NotNull
    private Long categoryId;
}
