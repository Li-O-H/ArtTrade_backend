package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

}
