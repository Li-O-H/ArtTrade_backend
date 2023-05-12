package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.controller.payload.ItemCreatePayload;
import com.itmo.ArtTrade.controller.payload.ItemUpdatePayload;
import com.itmo.ArtTrade.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<?> getItems(@RequestParam(required = false) Float minPrice,
                                      @RequestParam(required = false) Float maxPrice,
                                      @RequestParam(required = false) Long categoryId,
                                      @RequestParam(required = false) Long userId,
                                      @RequestParam(required = false) Long favoriteByUserId) {
        if (userId != null) {
            return ResponseEntity.ok(itemService.findUserItems(userId));
        }
        if (favoriteByUserId != null) {
            return ResponseEntity.ok(itemService.findFavoriteItemsByUser(favoriteByUserId));
        }
        return ResponseEntity.ok(itemService.findActiveItems(minPrice, maxPrice, categoryId));
    }

    @PostMapping
    public ResponseEntity<?> addItem(@RequestBody @Valid ItemCreatePayload item) {
        return ResponseEntity.ok(itemService.save(item));
    }

    @PostMapping(params = {"userId", "itemId"})
    public ResponseEntity<?> addToFavorites(@RequestParam Long userId,
                                            @RequestParam Long itemId) {
        itemService.addToFavorites(userId, itemId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> updateItem(@RequestBody @Valid ItemUpdatePayload item) {
        return ResponseEntity.ok(itemService.update(item));
    }


    @PutMapping("/{id}/activate")
    public ResponseEntity<?> activateItem(@PathVariable Long id) {
        itemService.activateItem(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/hide")
    public ResponseEntity<?> hideItem(@PathVariable Long id) {
        itemService.hideItem(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}/complete")
    public ResponseEntity<?> completeItem(@PathVariable Long id) {
        itemService.completeItem(id);
        return ResponseEntity.ok().build();
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
