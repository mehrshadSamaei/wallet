package com.example.wallet.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
@RequiredArgsConstructor
@Getter
public class ExceptionData {
    private final String message;
    private final HttpStatus status;
    private final ZonedDateTime stamp;

}
