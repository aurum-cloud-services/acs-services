package com.aurum.acs_services.user.domain.exceptions;

public class CpfIsNotValidException extends RuntimeException {
    public CpfIsNotValidException() {
        super("Cpf is not valid");
    }
}
