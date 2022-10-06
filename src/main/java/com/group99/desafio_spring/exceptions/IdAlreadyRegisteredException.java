package com.group99.desafio_spring.exceptions;

public class IdAlreadyRegisteredException extends RuntimeException {
    public IdAlreadyRegisteredException(String message) {
        super(message);
    }
}
