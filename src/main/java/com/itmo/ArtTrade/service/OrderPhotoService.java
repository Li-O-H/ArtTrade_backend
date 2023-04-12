package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.entity.OrderPhoto;
import com.itmo.ArtTrade.repository.OrderPhotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderPhotoService {

    private OrderPhotoRepository orderPhotoRepository;

    public OrderPhoto save(OrderPhoto orderPhoto) {
        orderPhoto.setId(null);
        return orderPhotoRepository.save(orderPhoto);
    }

    public void deleteById(Long id) {
        orderPhotoRepository.deleteById(id);
    }
}
