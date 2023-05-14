package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.controller.payload.ItemBidCreatePayload;
import com.itmo.ArtTrade.service.ItemBidService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SecurityRequirement(name = "JWT")
@RestController
@RequestMapping("/api/itemBid")
@AllArgsConstructor
@Tag(name="Item bid controller", description="Ставки на предметы")
public class ItemBidController {

    private ItemBidService itemBidService;

    @Operation(summary = "Получить все ставки пользователя на предметы")
    @GetMapping
    public ResponseEntity<?> getItemBidsByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(itemBidService.findUserItemBids(userId));
    }

    @Operation(summary = "Получить все ставки на предмет")
    @GetMapping(value = "/item")
    public ResponseEntity<?> getItemBidsByItem(@RequestParam Long itemId) {
        return ResponseEntity.ok(itemBidService.findItemItemBids(itemId));
    }

    @Operation(summary = "Добавить ставку на предмет")
    @PostMapping
    public ResponseEntity<?> addItemBid(@RequestBody @Valid ItemBidCreatePayload itemBid) {
        return ResponseEntity.ok(itemBidService.save(itemBid));
    }

    @Operation(summary = "Удалить ставку на предмет")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItemBid(@PathVariable Long id) {
        itemBidService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
