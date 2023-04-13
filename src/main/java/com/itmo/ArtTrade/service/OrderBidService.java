package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.controller.payload.OrderBidCreatePayload;
import com.itmo.ArtTrade.entity.Order;
import com.itmo.ArtTrade.entity.OrderBid;
import com.itmo.ArtTrade.entity.User;
import com.itmo.ArtTrade.repository.OrderBidRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderBidService {

    private OrderBidRepository orderBidRepository;
    private UserService userService;
    private OrderService orderService;

    public OrderBid save(OrderBidCreatePayload payload) {
        User user = userService.findById(payload.getUserId());
        Order order = orderService.findById(payload.getOrderId());
        OrderBid orderBid = new OrderBid()
                .setOrder(order)
                .setUser(user)
                .setPrice(payload.getPrice());
        return orderBidRepository.save(orderBid);
    }

    public void deleteById(Long id) {
        orderBidRepository.deleteById(id);
    }
}
