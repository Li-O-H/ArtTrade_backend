package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.controller.payload.OrderPhotoCreatePayload;
import com.itmo.ArtTrade.service.OrderPhotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SecurityRequirement(name = "JWT")
@RestController
@RequestMapping("/api/orderPhoto")
@AllArgsConstructor
@Tag(name="Order photo controller", description="Фотографии заказов")
public class OrderPhotoController {

    private OrderPhotoService orderPhotoService;

    @Operation(summary = "Добавить фото к заказу")
    @PostMapping
    public ResponseEntity<?> addOrderPhoto(@RequestBody @Valid OrderPhotoCreatePayload orderPhoto) {
        return ResponseEntity.ok(orderPhotoService.save(orderPhoto));
    }

    @Operation(summary = "Удалить фото заказа")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderPhoto(@PathVariable Long id) {
        orderPhotoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
