package com.aurum.acs_services.user.infrastructure.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LoginDTO(
        @NotNull(message = "Identifier (email or cpf) cant be null.")
        @NotEmpty(message = "Identifier (email or cpf) cant be empty.")
        String identifier,

        @NotNull(message = "Password cant be null.")
        @NotEmpty(message = "Password cant be empty.")
        String password
) {
}
