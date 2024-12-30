package com.aurum.acs_services.user.domain.exceptions;

import com.aurum.acs_services.shared.domain.contracts.BaseHttpException;

public class EmailIsNotValidException extends BaseHttpException {
    public EmailIsNotValidException() {
        super("E-mail is not valid", 400);
    }
}
