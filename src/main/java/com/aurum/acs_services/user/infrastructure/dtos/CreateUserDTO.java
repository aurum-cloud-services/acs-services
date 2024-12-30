package com.aurum.acs_services.user.infrastructure.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateUserDTO(
        @NotNull
        @NotEmpty
        String name,

        @NotEmpty
        @NotNull
        String email,

        @NotEmpty
        @NotNull
        String cpf,

        @NotEmpty
        @NotNull
        String password
) {
}
