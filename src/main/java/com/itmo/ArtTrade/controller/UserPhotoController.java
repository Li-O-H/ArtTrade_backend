package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.controller.payload.UserPhotoCreatePayload;
import com.itmo.ArtTrade.service.UserPhotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/userPhoto")
@AllArgsConstructor
public class UserPhotoController {

    private UserPhotoService userPhotoService;

    @PostMapping
    public ResponseEntity<?> addUserPhoto(@RequestBody @Valid UserPhotoCreatePayload userPhoto) {
        return ResponseEntity.ok(userPhotoService.save(userPhoto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserPhoto(@PathVariable Long id) {
        userPhotoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
