package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.controller.payload.ItemPhotoCreatePayload;
import com.itmo.ArtTrade.service.ItemPhotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SecurityRequirement(name = "JWT")
@RestController
@RequestMapping("/api/itemPhoto")
@AllArgsConstructor
@Tag(name="Item photo controller", description="Фотографии предметов")
public class ItemPhotoController {

    private ItemPhotoService itemPhotoService;

    @Operation(summary = "Добавить фото к предмету")
    @PostMapping
    public ResponseEntity<?> addItemPhoto(@RequestBody @Valid ItemPhotoCreatePayload itemPhoto) {
        return ResponseEntity.ok(itemPhotoService.save(itemPhoto));
    }

    @Operation(summary = "Удалить фото предмета")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItemPhoto(@PathVariable Long id) {
        itemPhotoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
