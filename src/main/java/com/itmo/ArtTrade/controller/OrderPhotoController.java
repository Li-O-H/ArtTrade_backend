package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.controller.payload.OrderPhotoCreatePayload;
import com.itmo.ArtTrade.service.OrderPhotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/orderPhoto")
@AllArgsConstructor
public class OrderPhotoController {

    private OrderPhotoService orderPhotoService;

    @PostMapping
    public ResponseEntity<?> addOrderPhoto(@RequestBody @Valid OrderPhotoCreatePayload orderPhoto) {
        return ResponseEntity.ok(orderPhotoService.save(orderPhoto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderPhoto(@PathVariable Long id) {
        orderPhotoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
