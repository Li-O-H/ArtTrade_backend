package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.controller.payload.OrderBidCreatePayload;
import com.itmo.ArtTrade.service.OrderBidService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/orderBid")
@AllArgsConstructor
public class OrderBidController {

    private OrderBidService orderBidService;

    @GetMapping
    public ResponseEntity<?> getOrderBidsByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(orderBidService.findUserOrderBids(userId));
    }

    @PostMapping
    public ResponseEntity<?> addOrderBid(@RequestBody @Valid OrderBidCreatePayload orderBid) {
        return ResponseEntity.ok(orderBidService.save(orderBid));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderBid(@PathVariable Long id) {
        orderBidService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
