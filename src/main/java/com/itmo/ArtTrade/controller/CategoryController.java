package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "JWT")
@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
@Tag(name="Category controller", description="Категории предметов и заказов")
public class CategoryController {

    private CategoryService categoryService;

    @Operation(summary = "Получить все доступные категории")
    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }
}
