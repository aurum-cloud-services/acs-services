package com.aurum.acs_services.user.application.usecases;

import com.aurum.acs_services.shared.application.exceptions.ConflictException;
import com.aurum.acs_services.shared.domain.entities.StandardResponse;
import com.aurum.acs_services.user.application.abstractions.IUserCreateUseCase;
import com.aurum.acs_services.user.application.abstractions.IUserRepository;
import com.aurum.acs_services.user.domain.aggregates.UserAggregate;
import com.aurum.acs_services.user.domain.exceptions.PasswordIsNotValidException;
import com.aurum.acs_services.user.infrastructure.dtos.CreateUserDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserCreateUseCase implements IUserCreateUseCase {
    private final IUserRepository userRepository;

    public UserCreateUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<StandardResponse> run(CreateUserDTO user) {
        try {
            var aggregate = UserAggregate.build(
                    user.name(),
                    user.email(),
                    user.cpf(),
                    user.password());

            userRepository.save(aggregate);

            return ResponseEntity.status(201).body(
                    StandardResponse.builder()
                            .message("Created.")
                            .statusCode(201)
                            .build());
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(user.email());
        }
    }
}
