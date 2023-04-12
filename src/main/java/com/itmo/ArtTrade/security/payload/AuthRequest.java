package com.itmo.ArtTrade.security.payload;

import lombok.Data;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class AuthRequest {

    @NotEmpty(message = "Email должен быть задан")
    private String email;

    @NotEmpty(message = "Пароль должен быть задан")
    @Size(min = 8, message = "Пароль не может быть короче 8 символов")
    private String password;
}
