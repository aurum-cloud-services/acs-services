package com.aurum.acs_services.user.domain.exceptions;

import com.aurum.acs_services.shared.domain.contracts.BaseHttpException;

public class PasswordIsNotValidException extends BaseHttpException {
    public PasswordIsNotValidException() {
        super("Password is not valid", 400);
    }
}
