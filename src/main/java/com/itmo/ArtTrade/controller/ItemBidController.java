package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.controller.payload.ItemBidCreatePayload;
import com.itmo.ArtTrade.entity.ItemBid;
import com.itmo.ArtTrade.service.ItemBidService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/itemBid")
@AllArgsConstructor
public class ItemBidController {

    private ItemBidService itemBidService;

    @PostMapping
    public ResponseEntity<?> addItemBid(@RequestBody @Valid ItemBidCreatePayload itemBid) {
        return ResponseEntity.ok(itemBidService.save(itemBid));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItemBid(@PathVariable Long id) {
        itemBidService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
