package com.aurum.acs_services.user.infrastructure.controllers;

import com.aurum.acs_services.user.application.abstractions.IUserCreateUseCase;
import com.aurum.acs_services.user.infrastructure.dtos.CreateUserDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class CreateUserController {
    private final IUserCreateUseCase useCase;

    public CreateUserController(IUserCreateUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping({ "/v1/user", "/v1/user/" })
    public ResponseEntity<?> performV1(@Valid @RequestBody CreateUserDTO dto) {
        return useCase.run(dto);
    }
}
