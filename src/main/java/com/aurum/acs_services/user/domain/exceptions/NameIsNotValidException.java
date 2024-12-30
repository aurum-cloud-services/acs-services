package com.aurum.acs_services.user.domain.exceptions;

import com.aurum.acs_services.shared.domain.contracts.BaseHttpException;

public class NameIsNotValidException extends BaseHttpException {
    public NameIsNotValidException() {
        super("Name is not valid", 400);
    }
}
