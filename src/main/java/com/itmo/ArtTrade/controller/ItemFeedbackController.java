package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.controller.payload.ItemFeedbackCreatePayload;
import com.itmo.ArtTrade.service.ItemFeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/itemFeedback")
@AllArgsConstructor
public class ItemFeedbackController {

    private ItemFeedbackService itemFeedbackService;

    @GetMapping
    public ResponseEntity<?> getItemFeedbacksByUser(@RequestParam(required = false) Long userId) {
        return ResponseEntity.ok(itemFeedbackService.findUserItemFeedbacks(userId));
    }

    @PostMapping
    public ResponseEntity<?> addItemFeedback(@RequestBody @Valid ItemFeedbackCreatePayload itemFeedback) {
        return ResponseEntity.ok(itemFeedbackService.save(itemFeedback));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItemFeedback(@PathVariable Long id) {
        itemFeedbackService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
