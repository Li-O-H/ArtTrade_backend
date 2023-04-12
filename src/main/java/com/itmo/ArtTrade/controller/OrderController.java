package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.entity.Order;
import com.itmo.ArtTrade.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> getActiveOrders(@RequestParam(required = false) Float min,
                                            @RequestParam(required = false) Float max,
                                            @RequestParam(required = false) Long categoryId) {
        return ResponseEntity.ok(orderService.findActiveOrders(min, max, categoryId));
    }

    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.save(order));
    }

    @PostMapping(params = {"userId", "orderId"})
    public ResponseEntity<?> addToFavorites(@RequestParam Long userId,
                                     @RequestParam Long orderId) {
        orderService.addToFavorites(userId, orderId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> updateOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.update(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(params = {"userId", "orderId"})
    public ResponseEntity<?> deleteFromFavorites(@RequestParam Long userId,
                                        @RequestParam Long orderId) {
        orderService.deleteFromFavorites(userId, orderId);
        return ResponseEntity.ok().build();
    }
}
