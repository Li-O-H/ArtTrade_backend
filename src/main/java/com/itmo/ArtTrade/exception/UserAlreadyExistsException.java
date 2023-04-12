package com.itmo.ArtTrade.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException() {
        super("Такой пользователь уже существует");
    }
}
