package com.itmo.ArtTrade.controller.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ItemPhotoCreatePayload {
    @NotNull
    private Long itemId;
    @NotEmpty
    private byte[] photo;
}
