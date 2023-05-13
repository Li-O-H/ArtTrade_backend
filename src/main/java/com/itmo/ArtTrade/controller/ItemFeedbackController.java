package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.controller.payload.ItemFeedbackCreatePayload;
import com.itmo.ArtTrade.service.ItemFeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SecurityRequirement(name = "JWT")
@RestController
@RequestMapping("/api/itemFeedback")
@AllArgsConstructor
@Tag(name="Item feedback controller", description="Отзывы о предметах")
public class ItemFeedbackController {

    private ItemFeedbackService itemFeedbackService;

    @Operation(summary = "Получить все отзывы, оставленные пользователем на предметы")
    @GetMapping
    public ResponseEntity<?> getItemFeedbacksByUser(@RequestParam(required = false) Long userId) {
        return ResponseEntity.ok(itemFeedbackService.findUserItemFeedbacks(userId));
    }

    @Operation(summary = "Добавить отзыв о предмете")
    @PostMapping
    public ResponseEntity<?> addItemFeedback(@RequestBody @Valid ItemFeedbackCreatePayload itemFeedback) {
        return ResponseEntity.ok(itemFeedbackService.save(itemFeedback));
    }

    @Operation(summary = "Удалить отзыв о предмете")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItemFeedback(@PathVariable Long id) {
        itemFeedbackService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
