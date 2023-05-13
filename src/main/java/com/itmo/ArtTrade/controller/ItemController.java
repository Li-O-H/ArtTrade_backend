package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.controller.payload.ItemCreatePayload;
import com.itmo.ArtTrade.controller.payload.ItemUpdatePayload;
import com.itmo.ArtTrade.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SecurityRequirement(name = "JWT")
@RestController
@RequestMapping("/api/item")
@AllArgsConstructor
@Tag(name="Item controller", description="Предметы искусства")
public class ItemController {

    private ItemService itemService;

    @Operation(summary = "Получить предмет по id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.findById(id));
    }

    @Operation(summary = "Получить предметы по различным параметрам")
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

    @Operation(summary = "Добавить предмет")
    @PostMapping
    public ResponseEntity<?> addItem(@RequestBody @Valid ItemCreatePayload item) {
        return ResponseEntity.ok(itemService.save(item));
    }

    @Operation(summary = "Добавить предмет в избранное")
    @PostMapping(value = "/favorite", params = {"userId", "itemId"})
    public ResponseEntity<?> addToFavorites(@RequestParam Long userId,
                                            @RequestParam Long itemId) {
        itemService.addToFavorites(userId, itemId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Редактировать предмет")
    @PutMapping
    public ResponseEntity<?> updateItem(@RequestBody @Valid ItemUpdatePayload item) {
        return ResponseEntity.ok(itemService.update(item));
    }


    @Operation(summary = "Активировать объявление о предмете")
    @PutMapping("/{id}/activate")
    public ResponseEntity<?> activateItem(@PathVariable Long id) {
        itemService.activateItem(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Скрыть объявление о предмете")
    @PutMapping("/{id}/hide")
    public ResponseEntity<?> hideItem(@PathVariable Long id) {
        itemService.hideItem(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Закрыть объявление о предмете")
    @PutMapping(value = "/{id}/complete")
    public ResponseEntity<?> completeItem(@PathVariable Long id) {
        itemService.completeItem(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удалить предмет")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        itemService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удалить предмет из избранного")
    @DeleteMapping(value = "/favorite", params = {"userId", "itemId"})
    public ResponseEntity<?> deleteFromFavorites(@RequestParam Long userId,
                                                 @RequestParam Long itemId) {
        itemService.deleteFromFavorites(userId, itemId);
        return ResponseEntity.ok().build();
    }
}
