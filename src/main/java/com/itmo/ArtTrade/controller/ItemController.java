package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/item")
@AllArgsConstructor
public class ItemController {

    private ItemService itemService;

}
