package com.aurum.acs_services.user.domain.exceptions;

import com.aurum.acs_services.shared.domain.contracts.BaseHttpException;

public class CpfIsNotValidException extends BaseHttpException {
    public CpfIsNotValidException() {
        super("Cpf is not valid", 400);
    }
}
