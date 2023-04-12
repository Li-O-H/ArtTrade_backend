package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.entity.Category;
import com.itmo.ArtTrade.exception.NoSuchDataException;
import com.itmo.ArtTrade.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public Category findById(Long id) {
        Optional<Category> item = categoryRepository.findById(id);
        if (item.isEmpty()) {
            throw new NoSuchDataException();
        }
        return item.get();
    }

    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }
}
