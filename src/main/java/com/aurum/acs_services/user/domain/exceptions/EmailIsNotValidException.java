package com.aurum.acs_services.user.domain.exceptions;

import com.aurum.acs_services.shared.domain.contracts.BaseHttpException;

public class EmailIsNotValidException extends BaseHttpException {
    public EmailIsNotValidException() {
        super("email is not valid", 400);
    }
}
