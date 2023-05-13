package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.controller.payload.OrderFeedbackCreatePayload;
import com.itmo.ArtTrade.service.OrderFeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SecurityRequirement(name = "JWT")
@RestController
@RequestMapping("/api/orderFeedback")
@AllArgsConstructor
@Tag(name="Order feedback controller", description="Отзывы о заказах")
public class OrderFeedbackController {

    private OrderFeedbackService orderFeedbackService;

    @Operation(summary = "Получить все отзывы, оставленные пользователем на заказы")
    @GetMapping
    public ResponseEntity<?> getOrderFeedbacksByUser(@RequestParam(required = false) Long userId) {
        return ResponseEntity.ok(orderFeedbackService.findUserOrderFeedbacks(userId));
    }

    @Operation(summary = "Добавить отзыв о заказе")
    @PostMapping
    public ResponseEntity<?> addOrderFeedback(@RequestBody @Valid OrderFeedbackCreatePayload orderFeedback) {
        return ResponseEntity.ok(orderFeedbackService.save(orderFeedback));
    }

    @Operation(summary = "Удалить отзыв о заказе")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderFeedback(@PathVariable Long id) {
        orderFeedbackService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
