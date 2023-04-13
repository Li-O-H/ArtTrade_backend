package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.controller.payload.ItemPhotoCreatePayload;
import com.itmo.ArtTrade.entity.Item;
import com.itmo.ArtTrade.entity.ItemPhoto;
import com.itmo.ArtTrade.repository.ItemPhotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemPhotoService {

    private ItemPhotoRepository itemPhotoRepository;
    private ItemService itemService;

    public ItemPhoto save(ItemPhotoCreatePayload payload) {
        Item item = itemService.findById(payload.getItemId());
        ItemPhoto itemPhoto = new ItemPhoto()
                .setPhoto(payload.getPhoto())
                .setItem(item);
        return itemPhotoRepository.save(itemPhoto);
    }

    public void deleteById(Long id) {
        itemPhotoRepository.deleteById(id);
    }
}
