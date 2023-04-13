package com.itmo.ArtTrade.controller.payload;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class ItemPhotoCreatePayload {
    @NotEmpty
    private Long itemId;
    @NotEmpty
    private byte[] photo;
}
