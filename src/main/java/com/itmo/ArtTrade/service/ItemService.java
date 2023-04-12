package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemService {

    private ItemRepository itemRepository;
}
