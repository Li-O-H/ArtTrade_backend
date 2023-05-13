package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.controller.payload.UserPhotoCreatePayload;
import com.itmo.ArtTrade.service.UserPhotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SecurityRequirement(name = "JWT")
@RestController
@RequestMapping("/api/userPhoto")
@AllArgsConstructor
@Tag(name="User photo controller", description="Фотографии пользователей")
public class UserPhotoController {

    private UserPhotoService userPhotoService;

    @Operation(summary = "Получить фотографии профиля")
    @GetMapping
    public ResponseEntity<?> getUserPhotosByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(userPhotoService.findUserPhotosByUser(userId));
    }

    @Operation(summary = "Добавить фотографию профиля")
    @PostMapping
    public ResponseEntity<?> addUserPhoto(@RequestBody @Valid UserPhotoCreatePayload userPhoto) {
        return ResponseEntity.ok(userPhotoService.save(userPhoto));
    }

    @Operation(summary = "Удалить фотографию профиля")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserPhoto(@PathVariable Long id) {
        userPhotoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
