package com.itmo.ArtTrade.controller.payload;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class OrderCreatePayload {
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @Future
    private Date deadline;
    @NotNull
    private Long userId;
    @NotNull
    private Long categoryId;
}
