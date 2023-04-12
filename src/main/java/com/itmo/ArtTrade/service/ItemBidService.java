package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.entity.ItemBid;
import com.itmo.ArtTrade.repository.ItemBidRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemBidService {

    private ItemBidRepository itemBidRepository;

    public ItemBid save(ItemBid itemBid) {
        itemBid.setId(null);
        return itemBidRepository.save(itemBid);
    }

    public void deleteById(Long id) {
        itemBidRepository.deleteById(id);
    }
}
