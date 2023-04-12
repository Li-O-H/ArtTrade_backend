package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.repository.OrderPhotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderPhotoService {

    private OrderPhotoRepository orderPhotoRepository;
}
