package com.aurum.acs_services.shared.application.exceptions;

import com.aurum.acs_services.shared.domain.contracts.BaseHttpException;

public class ConflictException extends BaseHttpException {
    public ConflictException(String message) {

        super(String.format("%s already exists.", message), 409);
    }
}
