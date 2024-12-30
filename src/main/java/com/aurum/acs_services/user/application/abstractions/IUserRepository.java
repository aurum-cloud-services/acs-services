package com.aurum.acs_services.user.application.abstractions;

import com.aurum.acs_services.user.domain.aggregates.UserAggregate;

public interface IUserRepository {
    UserAggregate save(UserAggregate user);
}
