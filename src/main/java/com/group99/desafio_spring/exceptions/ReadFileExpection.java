package com.group99.desafio_spring.exceptions;

import java.io.IOException;

public class ReadFileExpection extends RuntimeException {
    public ReadFileExpection(String message) {
        super(message);
    }
}
