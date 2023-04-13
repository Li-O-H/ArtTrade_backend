package com.itmo.ArtTrade.controller.payload;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class OrderUpdatePayload {
    @NotEmpty
    private Long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @Future
    private Date deadline;
    @NotEmpty
    private Long categoryId;
}
