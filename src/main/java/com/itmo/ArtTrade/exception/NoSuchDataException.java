package com.itmo.ArtTrade.exception;

public class NoSuchDataException extends RuntimeException {

    public NoSuchDataException() {
        super("Искомый объект не найден");
    }
}
