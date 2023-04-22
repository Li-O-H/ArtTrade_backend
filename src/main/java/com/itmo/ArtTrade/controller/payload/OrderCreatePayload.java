package com.itmo.ArtTrade.controller.payload;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class OrderCreatePayload {
    @NotEmpty
    @Size(max = 50)
    private String title;
    @NotEmpty
    @Size(max = 1000)
    private String description;
    @Future
    private Date deadline;
    @NotNull
    private Long userId;
    @NotNull
    private Long categoryId;
}
