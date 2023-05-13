package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.controller.payload.OrderBidCreatePayload;
import com.itmo.ArtTrade.service.OrderBidService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SecurityRequirement(name = "JWT")
@RestController
@RequestMapping("/api/orderBid")
@AllArgsConstructor
@Tag(name="Order bid controller", description="Ставки на заказы")
public class OrderBidController {

    private OrderBidService orderBidService;

    @Operation(summary = "Получить все ставки пользователя на заказы")
    @GetMapping
    public ResponseEntity<?> getOrderBidsByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(orderBidService.findUserOrderBids(userId));
    }

    @Operation(summary = "Добавить ставку на заказ")
    @PostMapping
    public ResponseEntity<?> addOrderBid(@RequestBody @Valid OrderBidCreatePayload orderBid) {
        return ResponseEntity.ok(orderBidService.save(orderBid));
    }

    @Operation(summary = "Удалить ставку на заказ")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderBid(@PathVariable Long id) {
        orderBidService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
