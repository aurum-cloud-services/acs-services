package com.aurum.acs_services.shared.infrastructure.handlers;

import com.aurum.acs_services.shared.domain.contracts.BaseHttpException;
import com.aurum.acs_services.shared.domain.entities.StandardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseHttpException.class)
    public ResponseEntity<StandardResponse> handleAnyHttpException(BaseHttpException e) {
        var response = StandardResponse.builder()
                .message(e.getMessage())
                .statusCode(e.getStatusCode())
                .build();

        return ResponseEntity.status(e.getStatusCode()).body(response);
    }
}
