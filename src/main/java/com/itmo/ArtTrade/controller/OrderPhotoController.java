package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.service.CategoryService;
import com.itmo.ArtTrade.service.OrderPhotoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orderPhoto")
@AllArgsConstructor
public class OrderPhotoController {

    private OrderPhotoService orderPhotoService;

}
