package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.controller.payload.ItemBidCreatePayload;
import com.itmo.ArtTrade.entity.Item;
import com.itmo.ArtTrade.entity.ItemBid;
import com.itmo.ArtTrade.entity.User;
import com.itmo.ArtTrade.repository.ItemBidRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemBidService {

    private ItemBidRepository itemBidRepository;
    private UserService userService;
    private ItemService itemService;

    public ItemBid save(ItemBidCreatePayload payload) {
        User user = userService.findById(payload.getUserId());
        Item item = itemService.findById(payload.getItemId());
        ItemBid itemBid = new ItemBid()
                .setItem(item)
                .setUser(user)
                .setPrice(payload.getPrice());
        return itemBidRepository.save(itemBid);
    }

    public void deleteById(Long id) {
        itemBidRepository.deleteById(id);
    }
}
