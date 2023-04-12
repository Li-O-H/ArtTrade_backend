package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.entity.OrderFeedback;
import com.itmo.ArtTrade.service.OrderFeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderFeedback")
@AllArgsConstructor
public class OrderFeedbackController {

    private OrderFeedbackService orderFeedbackService;

    @PostMapping
    public ResponseEntity<?> addOrderFeedback(@RequestBody OrderFeedback orderFeedback) {
        return ResponseEntity.ok(orderFeedbackService.save(orderFeedback));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderFeedback(@PathVariable Long id) {
        orderFeedbackService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
