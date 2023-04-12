package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.repository.ItemPhotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemPhotoService {

    private ItemPhotoRepository itemPhotoRepository;
}
