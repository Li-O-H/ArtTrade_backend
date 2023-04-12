package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.entity.Item;
import com.itmo.ArtTrade.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
@AllArgsConstructor
public class ItemController {

    private ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> getActiveItems(@RequestParam(required = false) Float min,
                                            @RequestParam(required = false) Float max,
                                            @RequestParam(required = false) Long categoryId) {
        return ResponseEntity.ok(itemService.findActiveItems(min, max, categoryId));
    }

    @PostMapping
    public ResponseEntity<?> addItem(@RequestBody Item item) {
        return ResponseEntity.ok(itemService.save(item));
    }

    @PostMapping(params = {"userId", "itemId"})
    public ResponseEntity<?> addToFavorites(@RequestParam Long userId,
                                     @RequestParam Long itemId) {
        itemService.addToFavorites(userId, itemId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> updateItem(@RequestBody Item item) {
        return ResponseEntity.ok(itemService.update(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        itemService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(params = {"userId", "itemId"})
    public ResponseEntity<?> deleteFromFavorites(@RequestParam Long userId,
                                     @RequestParam Long itemId) {
        itemService.deleteFromFavorites(userId, itemId);
        return ResponseEntity.ok().build();
    }
}
