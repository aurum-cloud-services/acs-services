package com.aurum.acs_services.user.infrastructure.dtos;

public record LoginOutputDTO(
        String message,
        String token,
        Integer statusCode
) {
}
