package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.controller.payload.OrderFeedbackCreatePayload;
import com.itmo.ArtTrade.service.OrderFeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/orderFeedback")
@AllArgsConstructor
public class OrderFeedbackController {

    private OrderFeedbackService orderFeedbackService;

    @PostMapping
    public ResponseEntity<?> addOrderFeedback(@RequestBody @Valid OrderFeedbackCreatePayload orderFeedback) {
        return ResponseEntity.ok(orderFeedbackService.save(orderFeedback));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderFeedback(@PathVariable Long id) {
        orderFeedbackService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
