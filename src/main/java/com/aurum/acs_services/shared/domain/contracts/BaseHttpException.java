package com.aurum.acs_services.shared.domain.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseHttpException extends RuntimeException {
    private Integer statusCode;

    public BaseHttpException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
