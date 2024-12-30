package com.aurum.acs_services.user.domain.exceptions;

public class PasswordIsNotValidException extends RuntimeException {
    public PasswordIsNotValidException() {
        super("Password is not valid");
    }
}
