package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.entity.ItemPhoto;
import com.itmo.ArtTrade.repository.ItemPhotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemPhotoService {

    private ItemPhotoRepository itemPhotoRepository;

    public ItemPhoto save(ItemPhoto itemPhoto) {
        itemPhoto.setId(null);
        return itemPhotoRepository.save(itemPhoto);
    }

    public void deleteById(Long id) {
        itemPhotoRepository.deleteById(id);
    }
}
