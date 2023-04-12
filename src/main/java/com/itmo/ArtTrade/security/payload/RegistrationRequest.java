package com.itmo.ArtTrade.security.payload;

import com.itmo.ArtTrade.validation.PasswordMatching;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatching
public class RegistrationRequest {

    @NotEmpty(message = "Email должен быть задан")
    private String email;

    @NotEmpty(message = "Пароль должен быть задан")
    @Size(min = 8, message = "Пароль не может быть короче 8 символов")
    private String password;

    @NotEmpty(message = "Пароль должен быть задан")
    @Size(min = 8, message = "Пароль не может быть короче 8 символов")
    private String matchingPassword;

    @NotEmpty(message = "Имя должно быть задано")
    private String name;

    @NotEmpty(message = "Город должен быть задан")
    private String city;

    private String aboutCreator;
}
