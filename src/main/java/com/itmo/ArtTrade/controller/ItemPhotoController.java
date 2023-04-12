package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.service.ItemPhotoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/itemPhoto")
@AllArgsConstructor
public class ItemPhotoController {

    private ItemPhotoService itemPhotoService;

}
