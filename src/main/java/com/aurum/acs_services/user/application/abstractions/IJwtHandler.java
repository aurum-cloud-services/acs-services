package com.aurum.acs_services.user.application.abstractions;

import com.aurum.acs_services.user.domain.aggregates.UserAggregate;

public interface IJwtHandler {
    String encode(UserAggregate user);
    Boolean isValid(String token);
}
