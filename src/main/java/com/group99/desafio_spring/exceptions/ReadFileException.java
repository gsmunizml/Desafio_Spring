package com.group99.desafio_spring.exceptions;

public class ReadFileException extends RuntimeException {
    public ReadFileException(String message) {
        super(message);
    }
}
