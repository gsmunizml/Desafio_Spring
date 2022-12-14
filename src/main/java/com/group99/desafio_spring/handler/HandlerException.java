package com.group99.desafio_spring.handler;

import com.group99.desafio_spring.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerException {

    private ExceptionDetails exceptionDetails;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> notFoundException(NotFoundException error) {
         exceptionDetails = ExceptionDetails.builder()
                .title("NOT FOUND")
                .message(error.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IdAlreadyRegisteredException.class)
    public ResponseEntity<ExceptionDetails> idAlreadyRegisteredException(IdAlreadyRegisteredException error) {
         exceptionDetails = ExceptionDetails.builder()
                .title("BAD REQUEST")
                .message(error.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ReadFileException.class)
    public ResponseEntity<ExceptionDetails> readFileException(ReadFileException error) {
         exceptionDetails = ExceptionDetails.builder()
                .title("BAD REQUEST READ FILE")
                .message(error.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(exceptionDetails, HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler(WriteFileException.class)
    public ResponseEntity<ExceptionDetails> writeFileException(WriteFileException error) {
        exceptionDetails = ExceptionDetails.builder()
                .title("BAD REQUEST WRITE FILE")
                .message(error.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(exceptionDetails, HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler(NoProductsException.class)
    public ResponseEntity<ExceptionDetails> noProductsException(NoProductsException error) {
        exceptionDetails = ExceptionDetails.builder()
                .title("BAD REQUEST")
                .message(error.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
}
