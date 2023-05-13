package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.controller.payload.UserUpdatePayload;
import com.itmo.ArtTrade.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SecurityRequirement(name = "JWT")
@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@Tag(name="User controller", description="Профили пользователей")
public class UserController {

    private UserService userService;

    @Operation(summary = "Получить пользователя по id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Operation(summary = "Получить пользователя по email")
    @GetMapping
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.getByEmail(email));
    }

    @Operation(summary = "Редактировать профиль")
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserUpdatePayload user) {
        return ResponseEntity.ok(userService.update(user));
    }

    @Operation(summary = "Удалить профиль (со всеми данными)")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
