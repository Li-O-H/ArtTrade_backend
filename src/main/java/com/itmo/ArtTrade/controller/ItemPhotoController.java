package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.controller.payload.ItemPhotoCreatePayload;
import com.itmo.ArtTrade.service.ItemPhotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/itemPhoto")
@AllArgsConstructor
public class ItemPhotoController {

    private ItemPhotoService itemPhotoService;

    @PostMapping
    public ResponseEntity<?> addItemPhoto(@RequestBody @Valid ItemPhotoCreatePayload itemPhoto) {
        return ResponseEntity.ok(itemPhotoService.save(itemPhoto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItemPhoto(@PathVariable Long id) {
        itemPhotoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
