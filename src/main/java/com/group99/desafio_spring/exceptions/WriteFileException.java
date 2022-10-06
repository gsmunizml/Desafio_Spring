package com.group99.desafio_spring.exceptions;

public class WriteFileException extends RuntimeException {
    public WriteFileException(String message) {
        super(message);
    }
}
