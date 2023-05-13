package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.controller.payload.OrderCreatePayload;
import com.itmo.ArtTrade.controller.payload.OrderUpdatePayload;
import com.itmo.ArtTrade.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<?> getOrders(@RequestParam(required = false) Float minPrice,
                                       @RequestParam(required = false) Float maxPrice,
                                       @RequestParam(required = false) Long categoryId,
                                       @RequestParam(required = false) Long userId,
                                       @RequestParam(required = false) Long favoriteByUserId,
                                       @RequestParam(required = false) Long doneByUserId) {
        if (userId != null) {
            return ResponseEntity.ok(orderService.findUserOrders(userId));
        }
        if (favoriteByUserId != null) {
            return ResponseEntity.ok(orderService.findFavoriteOrdersByUser(favoriteByUserId));
        }
        if (doneByUserId != null) {
            return ResponseEntity.ok(orderService.findDoneOrdersByUser(doneByUserId));
        }
        return ResponseEntity.ok(orderService.findActiveOrders(minPrice, maxPrice, categoryId));
    }

    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody @Valid OrderCreatePayload order) {
        return ResponseEntity.ok(orderService.save(order));
    }

    @PostMapping(params = {"userId", "orderId"})
    public ResponseEntity<?> addToFavorites(@RequestParam Long userId,
                                            @RequestParam Long orderId) {
        orderService.addToFavorites(userId, orderId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> updateOrder(@RequestBody @Valid OrderUpdatePayload order) {
        return ResponseEntity.ok(orderService.update(order));
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<?> activateOrder(@PathVariable Long id) {
        orderService.activateOrder(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/hide")
    public ResponseEntity<?> hideOrder(@PathVariable Long id) {
        orderService.hideOrder(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}/complete", params = {"email"})
    public ResponseEntity<?> completeOrder(@PathVariable Long id, @RequestParam(required = false) String email) {
        orderService.completeOrder(id, email);
        return ResponseEntity.ok().build();
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
