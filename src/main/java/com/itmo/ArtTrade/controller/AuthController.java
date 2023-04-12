package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.entity.User;
import com.itmo.ArtTrade.security.jwt.JwtProvider;
import com.itmo.ArtTrade.service.UserService;
import com.itmo.ArtTrade.security.payload.AuthRequest;
import com.itmo.ArtTrade.security.payload.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtProvider jwtProvider;


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationRequest request) {
        User user = new User()
                .setName(request.getName())
                .setPassword(request.getPassword())
                .setCity(request.getCity())
                .setEmail(request.getEmail())
                .setAboutCreator(request.getAboutCreator());
        userService.save(user);
        return authenticateUser(new AuthRequest(request.getEmail(), request.getPassword()));    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid AuthRequest request) {
        User user = userService.getByEmailAndPassword(request.getEmail(), request.getPassword());
        if (user != null) {
            String token = jwtProvider.generateToken(user.getEmail());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.notFound().build();
    }
}
