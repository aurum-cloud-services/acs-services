package com.aurum.acs_services.shared.domain.entities;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StandardResponse {
    private String message;
    private Integer statusCode;
}
