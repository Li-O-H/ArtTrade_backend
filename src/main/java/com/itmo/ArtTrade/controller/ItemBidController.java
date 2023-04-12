package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.service.ItemBidService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/itemBid")
@AllArgsConstructor
public class ItemBidController {

    private ItemBidService itemBidService;

}
