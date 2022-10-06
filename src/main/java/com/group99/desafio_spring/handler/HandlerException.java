package com.group99.desafio_spring.handler;

import com.group99.desafio_spring.exceptions.ExceptionDetails;
import com.group99.desafio_spring.exceptions.IdAlreadyRegisteredException;
import com.group99.desafio_spring.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> notFoundException(NotFoundException error) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("NOT FOUND")
                .message(error.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IdAlreadyRegisteredException.class)
    public ResponseEntity<ExceptionDetails> idAlreadyRegisteredException(IdAlreadyRegisteredException error) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("BAD REQUEST")
                .message(error.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
}
