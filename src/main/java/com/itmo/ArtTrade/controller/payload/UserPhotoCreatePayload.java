package com.itmo.ArtTrade.controller.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserPhotoCreatePayload {
    @NotNull
    private Long userId;
    @NotEmpty
    private byte[] photo;
}
