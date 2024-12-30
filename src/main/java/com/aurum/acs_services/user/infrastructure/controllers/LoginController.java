package com.aurum.acs_services.user.infrastructure.controllers;

import com.aurum.acs_services.user.application.abstractions.IUserLoginUseCase;
import com.aurum.acs_services.user.infrastructure.dtos.LoginDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class LoginController {
    private final IUserLoginUseCase useCase;

    public LoginController(IUserLoginUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/v1/login")
    public ResponseEntity<?> performV1(@Valid @RequestBody LoginDTO dto) {
        return this.useCase.run(dto);
    }
}
