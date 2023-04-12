package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.repository.OrderBidRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderBidService {

    private OrderBidRepository orderBidRepository;
}
