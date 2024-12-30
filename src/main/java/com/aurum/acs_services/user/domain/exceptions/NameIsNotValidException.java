package com.aurum.acs_services.user.domain.exceptions;

public class NameIsNotValidException extends RuntimeException {
    public NameIsNotValidException() {
        super("Name is not valid");
    }
}
