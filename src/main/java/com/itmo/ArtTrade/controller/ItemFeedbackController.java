package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.entity.ItemFeedback;
import com.itmo.ArtTrade.service.ItemFeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/itemFeedback")
@AllArgsConstructor
public class ItemFeedbackController {

    private ItemFeedbackService itemFeedbackService;

    @PostMapping
    public ResponseEntity<?> addItemFeedback(@RequestBody ItemFeedback itemFeedback) {
        return ResponseEntity.ok(itemFeedbackService.save(itemFeedback));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItemFeedback(@PathVariable Long id) {
        itemFeedbackService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
