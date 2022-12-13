package com.example.wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CostumeExceptionHandler {
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ExceptionData> badRequest(BadRequestException ex) {
        ExceptionData dataEx = new ExceptionData(ex.getMessage(), HttpStatus.BAD_REQUEST
                , ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(dataEx, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ExceptionData> notFoundException(NotFoundException ex) {
        ExceptionData dataEx = new ExceptionData(
                ex.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(dataEx, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = IdNullException.class)
    public ResponseEntity<ExceptionData> idNullEx(IdNullException ex){
        ExceptionData exData = new ExceptionData(
                ex.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("z"))
        );
        return new ResponseEntity<>(exData , HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = DataBaseException.class)
    public ResponseEntity<ExceptionData> dataBaseException(DataBaseException ex) {
        ExceptionData exData = new ExceptionData(
                ex.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("z"))
        );
        return new ResponseEntity<>(exData , HttpStatus.BAD_REQUEST);
    }
}
