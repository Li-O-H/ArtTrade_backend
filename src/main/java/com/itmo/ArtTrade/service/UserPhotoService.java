package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.repository.UserPhotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserPhotoService {

    private UserPhotoRepository userPhotoRepository;
}
