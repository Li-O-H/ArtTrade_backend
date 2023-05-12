package com.itmo.ArtTrade.controller.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserUpdatePayload {
    @NotNull
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String city;
    private String aboutCreator;
}
