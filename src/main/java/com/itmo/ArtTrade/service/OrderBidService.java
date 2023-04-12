package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.entity.OrderBid;
import com.itmo.ArtTrade.repository.OrderBidRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderBidService {

    private OrderBidRepository orderBidRepository;

    public OrderBid save(OrderBid orderBid) {
        orderBid.setId(null);
        return orderBidRepository.save(orderBid);
    }

    public void deleteById(Long id) {
        orderBidRepository.deleteById(id);
    }
}
