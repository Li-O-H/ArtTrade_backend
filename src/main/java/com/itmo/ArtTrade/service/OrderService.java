package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
}
