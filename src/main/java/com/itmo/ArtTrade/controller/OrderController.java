package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @PostMapping
    public void addOrder() {
//        repository.save(new Order("rfr", "frferf", Collections.EMPTY_LIST, Collections.EMPTY_LIST));
    }

}
