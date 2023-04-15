package com.itmo.ArtTrade.service;


import com.itmo.ArtTrade.controller.payload.UserPhotoCreatePayload;
import com.itmo.ArtTrade.entity.User;
import com.itmo.ArtTrade.entity.UserPhoto;
import com.itmo.ArtTrade.exception.NoSuchDataException;
import com.itmo.ArtTrade.repository.UserPhotoRepository;
import com.itmo.ArtTrade.security.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserPhotoService {

    private UserPhotoRepository userPhotoRepository;
    private UserService userService;
    private AuthorizationService authorizationService;

    public UserPhoto findById(Long id) {
        Optional<UserPhoto> userPhoto = userPhotoRepository.findById(id);
        if (userPhoto.isEmpty()) {
            throw new NoSuchDataException();
        }
        return userPhoto.get();
    }

    public UserPhoto save(UserPhotoCreatePayload payload) {
        authorizationService.invokerEqualsUserCheck(payload.getUserId());
        User user = userService.findById(payload.getUserId());
        UserPhoto userPhoto = new UserPhoto()
                .setPhoto(payload.getPhoto())
                .setUser(user);
        return userPhotoRepository.save(userPhoto);
    }

    public void deleteById(Long id) {
        authorizationService.invokerEqualsUserCheck(findById(id).getUser().getId());
        userPhotoRepository.deleteById(id);
    }
}
