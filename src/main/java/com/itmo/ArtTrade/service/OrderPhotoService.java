package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.controller.payload.OrderPhotoCreatePayload;
import com.itmo.ArtTrade.entity.Order;
import com.itmo.ArtTrade.entity.OrderPhoto;
import com.itmo.ArtTrade.exception.NoSuchDataException;
import com.itmo.ArtTrade.repository.OrderPhotoRepository;
import com.itmo.ArtTrade.security.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderPhotoService {

    private OrderPhotoRepository orderPhotoRepository;
    private OrderService orderService;
    private AuthorizationService authorizationService;

    public OrderPhoto findById(Long id) {
        Optional<OrderPhoto> orderPhoto = orderPhotoRepository.findById(id);
        if (orderPhoto.isEmpty()) {
            throw new NoSuchDataException();
        }
        return orderPhoto.get();
    }

    public OrderPhoto save(OrderPhotoCreatePayload payload) {
        Order order = orderService.findById(payload.getOrderId());
        authorizationService.invokerEqualsUserCheck(order.getUser().getId());
        OrderPhoto orderPhoto = new OrderPhoto()
                .setPhoto(payload.getPhoto())
                .setOrder(order);
        return orderPhotoRepository.save(orderPhoto);
    }

    public void deleteById(Long id) {
        authorizationService.invokerEqualsUserCheck(findById(id).getOrder().getUser().getId());
        orderPhotoRepository.deleteById(id);
    }
}
