package com.itmo.ArtTrade.controller.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserPhotoCreatePayload {
    @NotEmpty
    private Long userId;
    @NotEmpty
    private byte[] photo;
}
