package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.controller.payload.ItemPhotoCreatePayload;
import com.itmo.ArtTrade.entity.Item;
import com.itmo.ArtTrade.entity.ItemPhoto;
import com.itmo.ArtTrade.exception.NoSuchDataException;
import com.itmo.ArtTrade.repository.ItemPhotoRepository;
import com.itmo.ArtTrade.security.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemPhotoService {

    private ItemPhotoRepository itemPhotoRepository;
    private ItemService itemService;
    private AuthorizationService authorizationService;

    public ItemPhoto findById(Long id) {
        Optional<ItemPhoto> itemPhoto = itemPhotoRepository.findById(id);
        if (itemPhoto.isEmpty()) {
            throw new NoSuchDataException();
        }
        return itemPhoto.get();
    }

    public ItemPhoto save(ItemPhotoCreatePayload payload) {
        Item item = itemService.findById(payload.getItemId());
        authorizationService.invokerEqualsUserCheck(item.getUser().getId());
        ItemPhoto itemPhoto = new ItemPhoto()
                .setPhoto(payload.getPhoto())
                .setItem(item);
        return itemPhotoRepository.save(itemPhoto);
    }

    public void deleteById(Long id) {
        authorizationService.invokerEqualsUserCheck(findById(id).getItem().getUser().getId());
        itemPhotoRepository.deleteById(id);
    }
}
