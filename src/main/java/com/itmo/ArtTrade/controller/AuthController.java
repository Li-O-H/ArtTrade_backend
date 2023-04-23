package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.entity.User;
import com.itmo.ArtTrade.security.jwt.JwtProvider;
import com.itmo.ArtTrade.service.UserService;
import com.itmo.ArtTrade.controller.payload.AuthRequest;
import com.itmo.ArtTrade.controller.payload.RegistrationRequest;
import io.jsonwebtoken.MalformedJwtException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return loginUser(new AuthRequest().setEmail(request.getEmail()).setPassword(request.getPassword()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid AuthRequest request) {
        User user = userService.getByEmailAndPassword(request.getEmail(), request.getPassword());
        if (user != null) {
            String token = jwtProvider.generateToken(user.getEmail());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> getUserByToken(@RequestParam(required = false) String token) {
        if (token == null) {
            return ResponseEntity.ok(null);
        }
        String email;
        try {
            email = jwtProvider.getEmailFromToken(token);
        } catch (MalformedJwtException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userService.getByEmail(email));
    }
}
