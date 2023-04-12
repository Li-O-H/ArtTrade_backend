package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.entity.OrderPhoto;
import com.itmo.ArtTrade.service.OrderPhotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderPhoto")
@AllArgsConstructor
public class OrderPhotoController {

    private OrderPhotoService orderPhotoService;

    @PostMapping
    public ResponseEntity<?> addOrderPhoto(@RequestBody OrderPhoto orderPhoto) {
        return ResponseEntity.ok(orderPhotoService.save(orderPhoto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderPhoto(@PathVariable Long id) {
        orderPhotoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
