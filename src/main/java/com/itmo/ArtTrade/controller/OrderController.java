package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.controller.payload.OrderCreatePayload;
import com.itmo.ArtTrade.controller.payload.OrderUpdatePayload;
import com.itmo.ArtTrade.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SecurityRequirement(name = "JWT")
@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
@Tag(name="Order controller", description="Заказы на создание предметов искусства")
public class OrderController {

    private OrderService orderService;

    @Operation(summary = "Получить заказ по id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @Operation(summary = "Получить заказы по различным параметрам")
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

    @Operation(summary = "Добавить заказ")
    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody @Valid OrderCreatePayload order) {
        return ResponseEntity.ok(orderService.save(order));
    }

    @Operation(summary = "Добавить заказ в избранное")
    @PostMapping(value = "favorite", params = {"userId", "orderId"})
    public ResponseEntity<?> addToFavorites(@RequestParam Long userId,
                                            @RequestParam Long orderId) {
        orderService.addToFavorites(userId, orderId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Редактировать заказ")
    @PutMapping
    public ResponseEntity<?> updateOrder(@RequestBody @Valid OrderUpdatePayload order) {
        return ResponseEntity.ok(orderService.update(order));
    }

    @Operation(summary = "Активировать объявление о заказе")
    @PutMapping("/{id}/activate")
    public ResponseEntity<?> activateOrder(@PathVariable Long id) {
        orderService.activateOrder(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Скрыть объявление о заказе")
    @PutMapping("/{id}/hide")
    public ResponseEntity<?> hideOrder(@PathVariable Long id) {
        orderService.hideOrder(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Закрыть объявление о заказе (можно указать исполнителя)")
    @PutMapping(value = "/{id}/complete", params = {"email"})
    public ResponseEntity<?> completeOrder(@PathVariable Long id, @RequestParam(required = false) String email) {
        orderService.completeOrder(id, email);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удалить заказ")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удалить заказ из избранного")
    @DeleteMapping(value = "favorite", params = {"userId", "orderId"})
    public ResponseEntity<?> deleteFromFavorites(@RequestParam Long userId,
                                                 @RequestParam Long orderId) {
        orderService.deleteFromFavorites(userId, orderId);
        return ResponseEntity.ok().build();
    }
}
