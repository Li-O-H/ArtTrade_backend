package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.service.UserPhotoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userPhoto")
@AllArgsConstructor
public class UserPhotoController {

    private UserPhotoService userPhotoService;

}
