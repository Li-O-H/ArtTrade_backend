package com.itmo.ArtTrade.controller.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class ItemCreatePayload {
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @NotEmpty
    private Long userId;
    @NotEmpty
    private Long categoryId;
}
