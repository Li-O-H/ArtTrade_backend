package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.service.OrderBidService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orderBid")
@AllArgsConstructor
public class OrderBidController {

    private OrderBidService orderBidService;

}
