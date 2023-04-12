package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.entity.OrderBid;
import com.itmo.ArtTrade.service.OrderBidService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderBid")
@AllArgsConstructor
public class OrderBidController {

    private OrderBidService orderBidService;

    @PostMapping
    public ResponseEntity<?> addOrderBid(@RequestBody OrderBid orderBid) {
        return ResponseEntity.ok(orderBidService.save(orderBid));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderBid(@PathVariable Long id) {
        orderBidService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
