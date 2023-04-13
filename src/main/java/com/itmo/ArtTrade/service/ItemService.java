package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.controller.payload.ItemCreatePayload;
import com.itmo.ArtTrade.controller.payload.ItemUpdatePayload;
import com.itmo.ArtTrade.entity.*;
import com.itmo.ArtTrade.exception.NoSuchDataException;
import com.itmo.ArtTrade.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemService {

    private ItemRepository itemRepository;
    private CategoryService categoryService;
    private UserService userService;

    public Item findById(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isEmpty()) {
            throw new NoSuchDataException();
        }
        return item.get();
    }

    public List<Item> findActiveItems(Float min, Float max, Long categoryId) {
        List<Item> items;
        if (categoryId != null) {
            Category category = categoryService.findById(categoryId);
            items = itemRepository.findAllByStatusAndCategory(Status.ACTIVE, category);
        } else {
            items = itemRepository.findAllByStatus(Status.ACTIVE);
        }
        if (min == null && max == null) {
            return items;
        }
        if (max != null) {
            items.removeIf(item -> {
                List<ItemBid> bids = item.getBids();
                bids.sort((i1, i2) -> Float.compare(i2.getPrice(), i1.getPrice()));
                return !bids.isEmpty() && bids.get(0).getPrice() > max;
            });
        }
        if (min != null) {
            items.removeIf(item -> {
                List<ItemBid> bids = item.getBids();
                bids.sort((i1, i2) -> Float.compare(i2.getPrice(), i1.getPrice()));
                return !bids.isEmpty() && bids.get(0).getPrice() < min;
            });
        }
        return items;
    }

    public Item save(ItemCreatePayload payload) {
        User user = userService.findById(payload.getUserId());
        Category category = categoryService.findById(payload.getCategoryId());
        Item item = new Item()
                .setTitle(payload.getTitle())
                .setDescription(payload.getDescription())
                .setStatus(Status.HIDDEN)
                .setCategory(category)
                .setUser(user);
        return itemRepository.save(item);
    }

    public void addToFavorites(Long userId, Long itemId) {
        User user = userService.findById(userId);
        Item item = findById(itemId);
        if (item.getFavoriteOf().contains(user)) {
            return;
        }
        item.getFavoriteOf()
                .addAll(Collections.singletonList(user)
                        .stream()
                        .map(u -> {
                            User uu = userService.findById(u.getId());
                            uu.getFavoriteItems().add(item);
                            return uu;
                        }).collect(Collectors.toList()));
        itemRepository.save(item);
    }

    public Item update(ItemUpdatePayload payload) {
        Item item = findById(payload.getId());
        Category category = categoryService.findById(payload.getCategoryId());
        if (!item.getStatus().equals(Status.COMPLETED)){
            item
                    .setTitle(payload.getTitle())
                    .setDescription(payload.getDescription())
                    .setCategory(category);
            return itemRepository.save(item);
        }
        return item;
    }

    public void activateItem(Long id) {
        Item item = findById(id);
        if (!item.getStatus().equals(Status.COMPLETED)){
            item.setStatus(Status.ACTIVE);
            itemRepository.save(item);
        }
    }

    public void hideItem(Long id) {
        Item item = findById(id);
        if (!item.getStatus().equals(Status.COMPLETED)){
            item.setStatus(Status.HIDDEN);
            itemRepository.save(item);
        }
    }

    public void completeItem(Long id) {
        Item item = findById(id);
        item.setStatus(Status.COMPLETED);
        itemRepository.save(item);
    }

    public void deleteById(Long id) {
        if (!findById(id).getStatus().equals(Status.COMPLETED)) {
            itemRepository.deleteById(id);
        }
    }

    public void deleteFromFavorites(Long userId, Long itemId) {
        User user = userService.findById(userId);
        Item item = findById(itemId);
        if (item.getFavoriteOf().contains(user)) {
            return;
        }
        item.getFavoriteOf()
                .removeAll(Collections.singletonList(user)
                        .stream()
                        .map(u -> {
                            User uu = userService.findById(u.getId());
                            uu.getFavoriteItems().remove(item);
                            return uu;
                        }).collect(Collectors.toList()));
        itemRepository.save(item);
    }
}
