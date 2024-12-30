package com.aurum.acs_services.user.domain.aggregates;

import com.aurum.acs_services.user.domain.exceptions.CpfIsNotValidException;
import com.aurum.acs_services.user.domain.exceptions.EmailIsNotValidException;
import com.aurum.acs_services.user.domain.exceptions.NameIsNotValidException;
import com.aurum.acs_services.user.domain.exceptions.PasswordIsNotValidException;
import com.aurum.acs_services.user.domain.validators.CpfValidator;
import lombok.Getter;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private UserAggregate(
            Long id,
            String name,
            String email,
            String cpf,
            String password,
            LocalDate createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
        this.createdAt = createdAt;
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

    public static UserAggregate build(
            Long id,
            String name,
            String email,
            String cpf,
            String password,
            LocalDate createdAt
    ) {
        var aggregate = new UserAggregate(id, name, email, cpf, password, createdAt);

        aggregate.validateDomain();

        return aggregate;
    }

    private void validateDomain() {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        DomainValidator.throwsWhen(this.name.length() > 40, NameIsNotValidException.class);
        DomainValidator.throwsWhen(!CpfValidator.isValid(this.cpf), CpfIsNotValidException.class);
        DomainValidator.throwsWhen(this.password.length() < 12 || this.password.length() > 100, PasswordIsNotValidException.class);
        DomainValidator.throwsWhen(!matcher.matches(), EmailIsNotValidException.class);
    }
}
