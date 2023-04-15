package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.entity.User;
import com.itmo.ArtTrade.exception.NoSuchDataException;
import com.itmo.ArtTrade.exception.UserAlreadyExistsException;
import com.itmo.ArtTrade.repository.UserRepository;
import com.itmo.ArtTrade.security.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private AuthorizationService authorizationService;

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NoSuchDataException();
        }
        return user.get();
    }

    public void save(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistsException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User getByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteById(Long id) {
        authorizationService.invokerEqualsUserCheck(id);
        userRepository.deleteById(id);
    }
}
