package com.group99.desafio_spring.exceptions;

public class NoProductsException extends RuntimeException{
    public NoProductsException(String message) {
        super(message);
    }
}
