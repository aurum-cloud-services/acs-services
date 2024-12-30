package com.aurum.acs_services.user.domain.aggregates;

import com.aurum.acs_services.user.domain.exceptions.CpfIsNotValidException;
import com.aurum.acs_services.user.domain.exceptions.NameIsNotValidException;
import com.aurum.acs_services.user.domain.exceptions.PasswordIsNotValidException;
import com.aurum.acs_services.user.domain.validators.CpfValidator;
import lombok.Getter;

import java.time.LocalDate;
import com.aurum.acs_services.shared.domain.validators.DomainValidator;

@Getter
public class UserAggregate {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String password;
    private LocalDate createdAt;

    private UserAggregate(
            String name,
            String email,
            String cpf,
            String password) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
    }

    public static UserAggregate build(
            String name,
            String email,
            String cpf,
            String password
    ) {
        var aggregate = new UserAggregate(name, email, cpf, password);

        aggregate.validateDomain();

        return aggregate;
    }

    private void validateDomain() {
        DomainValidator.throwsWhen(this.name.length() > 40, NameIsNotValidException.class);
        DomainValidator.throwsWhen(!CpfValidator.isValid(this.cpf), CpfIsNotValidException.class);
        DomainValidator.throwsWhen(this.password.length() < 12 || this.password.length() > 100, PasswordIsNotValidException.class);
    }
}
