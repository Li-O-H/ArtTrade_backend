package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.controller.payload.OrderPhotoCreatePayload;
import com.itmo.ArtTrade.entity.Order;
import com.itmo.ArtTrade.entity.OrderPhoto;
import com.itmo.ArtTrade.repository.OrderPhotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderPhotoService {

    private OrderPhotoRepository orderPhotoRepository;
    private OrderService orderService;

    public OrderPhoto save(OrderPhotoCreatePayload payload) {
        Order order = orderService.findById(payload.getOrderId());
        OrderPhoto orderPhoto = new OrderPhoto()
                .setPhoto(payload.getPhoto())
                .setOrder(order);
        return orderPhotoRepository.save(orderPhoto);
    }

    public void deleteById(Long id) {
        orderPhotoRepository.deleteById(id);
    }
}
