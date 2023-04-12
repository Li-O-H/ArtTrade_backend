package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.entity.UserPhoto;
import com.itmo.ArtTrade.repository.UserPhotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserPhotoService {

    private UserPhotoRepository userPhotoRepository;

    public UserPhoto save(UserPhoto userPhoto) {
        userPhoto.setId(null);
        return userPhotoRepository.save(userPhoto);
    }

    public void deleteById(Long id) {
        userPhotoRepository.deleteById(id);
    }
}
