package com.itmo.ArtTrade.controller.payload;

import com.itmo.ArtTrade.validation.PasswordMatching;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatching
@NoArgsConstructor
public class RegistrationRequest {

    @NotEmpty(message = "Email должен быть задан")
    private String email;

    @NotEmpty(message = "Пароль должен быть задан")
    @Size(min = 8, message = "Пароль не может быть короче 8 символов")
    private String password;

    @NotEmpty(message = "Повторный пароль должен быть задан")
    @Size(min = 8, message = "Пароль не может быть короче 8 символов")
    private String matchingPassword;

    @NotEmpty(message = "Имя должно быть задано")
    private String name;

    @NotEmpty(message = "Город должен быть задан")
    private String city;

    private String aboutCreator;
}
