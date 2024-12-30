package com.aurum.acs_services.shared.domain.validators;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DomainValidator {
    public static <T extends Exception> void throwsWhen(boolean condition, Class<T> exceptionClass) throws T {
        try {
            if (condition)
                throw exceptionClass.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("ERR: Create exception instance.", e);
        }
    }
}
