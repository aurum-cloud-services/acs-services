package com.aurum.acs_services.user.application.abstractions;

import com.aurum.acs_services.user.domain.aggregates.UserAggregate;
import com.aurum.acs_services.user.infrastructure.entities.UserEntity;

import java.util.Optional;

public interface IUserMapper {
    UserEntity toPersistance(UserAggregate user);
    Optional<UserAggregate> toDomain(UserEntity user);
}
