package com.itmo.ArtTrade.controller.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class OrderPhotoCreatePayload {
    @NotNull
    private Long orderId;
    @NotEmpty
    private byte[] photo;
}
