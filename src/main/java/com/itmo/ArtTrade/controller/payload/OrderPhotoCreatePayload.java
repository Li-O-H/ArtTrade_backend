package com.itmo.ArtTrade.controller.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderPhotoCreatePayload {
    @NotEmpty
    private Long orderId;
    @NotEmpty
    private byte[] photo;
}
