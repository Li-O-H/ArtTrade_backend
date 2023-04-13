package com.itmo.ArtTrade.service;


import com.itmo.ArtTrade.controller.payload.UserPhotoCreatePayload;
import com.itmo.ArtTrade.entity.User;
import com.itmo.ArtTrade.entity.UserPhoto;
import com.itmo.ArtTrade.repository.UserPhotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserPhotoService {

    private UserPhotoRepository userPhotoRepository;
    private UserService userService;

    public UserPhoto save(UserPhotoCreatePayload payload) {
        User user = userService.findById(payload.getUserId());
        UserPhoto userPhoto = new UserPhoto()
                .setPhoto(payload.getPhoto())
                .setUser(user);
        return userPhotoRepository.save(userPhoto);
    }

    public void deleteById(Long id) {
        userPhotoRepository.deleteById(id);
    }
}
