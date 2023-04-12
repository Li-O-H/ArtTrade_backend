package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.repository.ItemBidRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemBidService {

    private ItemBidRepository itemBidRepository;
}
