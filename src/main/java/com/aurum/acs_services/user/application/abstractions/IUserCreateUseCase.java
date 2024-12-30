package com.aurum.acs_services.user.application.abstractions;

import com.aurum.acs_services.shared.domain.entities.StandardResponse;
import com.aurum.acs_services.user.infrastructure.dtos.CreateUserDTO;
import org.springframework.http.ResponseEntity;

public interface IUserCreateUseCase {
    ResponseEntity<StandardResponse> run(CreateUserDTO user);
}
