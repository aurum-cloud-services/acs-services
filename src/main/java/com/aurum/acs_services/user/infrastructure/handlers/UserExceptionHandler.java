package com.aurum.acs_services.user.infrastructure.handlers;

import com.aurum.acs_services.shared.domain.entities.StandardResponse;
import com.aurum.acs_services.user.domain.exceptions.CpfIsNotValidException;
import com.aurum.acs_services.user.domain.exceptions.NameIsNotValidException;
import com.aurum.acs_services.user.domain.exceptions.PasswordIsNotValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(PasswordIsNotValidException.class)
    public ResponseEntity<StandardResponse> handlePasswordIsNotValid(PasswordIsNotValidException e) {
        var response = StandardResponse.builder()
                .message(e.getMessage())
                .statusCode(400)
                .build();

        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler(CpfIsNotValidException.class)
    public ResponseEntity<StandardResponse> handleCpfIsNotValid(CpfIsNotValidException e) {
        var response = StandardResponse.builder()
                .message(e.getMessage())
                .statusCode(400)
                .build();

        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler(NameIsNotValidException.class)
    public ResponseEntity<StandardResponse> handleNameIsNotValid(NameIsNotValidException e) {
        var response = StandardResponse.builder()
                .message(e.getMessage())
                .statusCode(400)
                .build();

        return ResponseEntity.status(400).body(response);
    }
}
